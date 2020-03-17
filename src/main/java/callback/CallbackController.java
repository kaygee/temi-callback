package callback;

import callback.beans.BillingRequest;
import callback.beans.BillingType;
import callback.beans.InitializationStatus;
import callback.beans.Job;
import callback.beans.JobCount;
import callback.beans.JobType;
import callback.beans.OnPremisesBilling;
import callback.beans.OnPremisesFailure;
import callback.beans.TranscriptCallback;
import callback.exception.JobNotFoundException;
import callback.logger.RequestResponseLoggingInterceptor;
import callback.repository.BillingRepository;
import callback.repository.JobRepository;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Collections;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class CallbackController {

  private static final Logger LOG = LoggerFactory.getLogger(CallbackController.class);

  @Autowired JobRepository jobRepository;
  @Autowired BillingRepository billingRepository;

  @GetMapping(
      value = "/health",
      produces = {"application/json"})
  public String healthCheck() {
    InitializationStatus status = new InitializationStatus();
    status.setSuccess(true);
    try {
      LOG.info("Returning health status.");
      return new ObjectMapper().writeValueAsString(status);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  @GetMapping(
      value = "/jobs/count",
      produces = {"application/json"})
  public String getJobsCount() {
    // Do a bunch of queries to get the totals.
    Integer overallCount = jobRepository.countJobs();
    Integer failedCount = jobRepository.countJobsWithType(JobType.FAILED.toString());
    Integer initializationCount =
        jobRepository.countJobsWithType(JobType.INITIALIZATION.toString());
    Integer transcribedCount = jobRepository.countJobsWithType(JobType.TRANSCRIPTION.toString());

    // Set the object to return.
    JobCount jobCount = new JobCount();
    jobCount.setFailedCount(failedCount);
    jobCount.setInitializationCount(initializationCount);
    jobCount.setOverallCount(overallCount);
    jobCount.setTranscribedCount(transcribedCount);

    try {
      LOG.info("Returning count of all jobs.");
      return new ObjectMapper().writeValueAsString(jobCount);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  @GetMapping(
      value = "/jobs/all",
      produces = {"application/json"})
  public List<Job> getJobs() {
    LOG.info("Returning all jobs.");
    return jobRepository.findAll();
  }

  @GetMapping(
      value = "/jobs/initialization",
      produces = {"application/json"})
  public List<Job> getInitializationJobs() {
    LOG.info("Returning all initialization requests.");
    return jobRepository.findByJobType(JobType.INITIALIZATION.toString());
  }

  @GetMapping(
      value = "/jobs/failed",
      produces = {"application/json"})
  public List<Job> getFailedJobs() {
    LOG.info("Returning all failed jobs.");
    return jobRepository.findByJobType(JobType.FAILED.toString());
  }

  @GetMapping(
      value = "/jobs/transcribed",
      produces = {"application/json"})
  public List<Job> getTranscribedJobs() {
    LOG.info("Returning all transciption jobs.");
    return jobRepository.findByJobType(JobType.TRANSCRIPTION.toString());
  }

  @GetMapping(
      value = "/jobs/{metadata}/metadata",
      produces = {"application/json"})
  public List<Job> getJobByMetadata(@PathVariable(value = "metadata") String metadata) {
    try {
      LOG.info("Returning jobs with metadata of [" + metadata + "].");
      return jobRepository.findByJobMetadata(metadata);
    } catch (IllegalArgumentException e) {
      throw new JobNotFoundException("Job Metadata", metadata);
    }
  }

  @GetMapping(
      value = "/jobs/{metadata}/count",
      produces = {"application/json"})
  public String getJobCountWithMetadata(@PathVariable(value = "metadata") String metadata) {
    JobCount jobCount = new JobCount(jobRepository.countJobsWithMetadata(metadata));
    LOG.info("Returning count of jobs with metadata of [" + metadata + "].");
    try {
      return new ObjectMapper().writeValueAsString(jobCount);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  @RequestMapping(
      value = "/jobs/successful",
      method = {GET, POST})
  @ResponseBody
  public ResponseEntity<Object> respondSuccessful(@RequestBody String request) {
    LOG.info("Incoming callback request... [" + request + "].");
    Job job = new Job();
    try {
      if (request.contains("failure") && request.contains("metadata")) {
        OnPremisesFailure onPremisesFailure =
            new ObjectMapper().readValue(request, OnPremisesFailure.class);
        job.setFailure(onPremisesFailure.getFailure());
        job.setFailureDetail(onPremisesFailure.getFailureDetail());
        job.setMetadata(onPremisesFailure.getMetadata());
        job.setJobType(JobType.FAILED.toString());
        LOG.info(
            "Received request with metadata ["
                + onPremisesFailure.getMetadata()
                + "] with type ["
                + JobType.FAILED.toString()
                + "].");
      } else if (request.contains("transcript") && request.contains("monologues")) {
        TranscriptCallback transcriptCallback =
            new ObjectMapper().readValue(request, TranscriptCallback.class);
        job.setMetadata(transcriptCallback.getMetadata());
        LOG.info(
            "Received request with metadata ["
                + transcriptCallback.getMetadata()
                + "] with type ["
                + JobType.TRANSCRIPTION.toString()
                + "].");
        job.setJobType(JobType.TRANSCRIPTION.toString());
      } else if (request.contains("success")) {
        LOG.info("Received request for initialization.");
        job.setJobType(JobType.INITIALIZATION.toString());
      } else {
        throw new RuntimeException("I don't know what this is?! [" + request + "].");
      }

      job.setHttpStatus(HttpStatus.OK.value());

      job.setRawData(getMinifiedJson(request));
      jobRepository.save(job);
    } catch (IOException e) {
      e.printStackTrace();
    }
    LOG.info("Responding to callback request...");
    return ResponseEntity.status(HttpStatus.OK).body(null);
  }

  @GetMapping(
      value = "/billing/all",
      produces = {"application/json"})
  public List<BillingRequest> getBillingRequests() {
    LOG.info("Returning all billing requests.");
    return billingRepository.findAll();
  }

  @RequestMapping(
      value = "/billing",
      method = {POST})
  @ResponseBody
  public ResponseEntity<Object> respondBillingSidecar(
      @RequestBody String request, @RequestHeader MultiValueMap<String, String> headers) {
    LOG.info("Incoming billing request... [" + request + "]");

    OnPremisesBilling onPremisesBilling = null;
    try {
      onPremisesBilling = new ObjectMapper().readValue(request, OnPremisesBilling.class);
    } catch (JsonProcessingException e) {
      // #nomnomnom
    }
    BillingRequest billingRequestReceived = new BillingRequest();
    billingRequestReceived.setBillingType(BillingType.BILLING_REQUEST_RECEIVED.toString());
    billingRequestReceived.setRawData(request);
    billingRequestReceived.setHeaders(headers.toSingleValueMap().toString());
    billingRepository.save(billingRequestReceived);

    ClientHttpRequestFactory factory =
        new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory());

    RestTemplate restTemplate = new RestTemplate(factory);
    restTemplate.setInterceptors(
        Collections.singletonList(new RequestResponseLoggingInterceptor()));

    //    BillingRequest billingRequestSent = new BillingRequest();
    //    billingRequestReceived.setBillingType(BillingType.BILLING_REQUEST_SENT.toString());
    //    billingRequestReceived.setRawData(request);
    //    billingRequestReceived.setHeaders(headers.toSingleValueMap().toString());
    //    billingRepository.save(billingRequestSent);

    ResponseEntity<String> responseEntity =
        post(onPremisesBilling.getRevAiEndpoint(), request, headers, restTemplate);

    //    BillingRequest billingResponseReceived = new BillingRequest();
    //    billingResponseReceived.setBillingType(BillingType.BILLING_RESPONSE_RECEIVED.toString());
    //    billingResponseReceived.setRawData(responseEntity.toString());
    //
    // billingResponseReceived.setHeaders(responseEntity.getHeaders().toSingleValueMap().toString());
    //    billingRepository.save(billingResponseReceived);

    LOG.info("Response to billing request... [" + responseEntity.toString() + "].");
    return ResponseEntity.status(responseEntity.getStatusCode())
        .headers(responseEntity.getHeaders())
        .body(responseEntity.getBody());
  }

  private ResponseEntity<String> post(
      String url, String json, MultiValueMap<String, String> headers, RestTemplate restTemplate) {
    HttpEntity<String> requestEntity = new HttpEntity<>(json, headers);
    ResponseEntity<String> responseEntity =
        restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
    return responseEntity;
  }

  private String getMinifiedJson(@RequestBody String request) throws IOException {
    StringWriter minifiedOutput = new StringWriter();

    JsonFactory factory = new JsonFactory();
    JsonParser parser = factory.createParser(request);
    try (JsonGenerator gen = factory.createGenerator(minifiedOutput)) {
      while (parser.nextToken() != null) {
        gen.copyCurrentEvent(parser);
      }
    }
    return minifiedOutput.getBuffer().toString();
  }
}
