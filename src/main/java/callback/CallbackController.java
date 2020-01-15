package callback;

import callback.beans.Job;
import callback.beans.JobCount;
import callback.beans.JobType;
import callback.beans.OnPremisesFailure;
import callback.beans.TranscriptCallback;
import callback.exception.JobNotFoundException;
import callback.repository.JobRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class CallbackController {

  private static final Logger LOG = LoggerFactory.getLogger(CallbackController.class);

  @Autowired JobRepository jobRepository;

  @GetMapping(
      value = "/jobs/count",
      produces = {"application/json"})
  public String getJobsCount() {
    JobCount jobCount = new JobCount(jobRepository.countJobs());
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
    LOG.info("Incoming request...");
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
        job.setJobType(JobType.INITIALIZATION.toString());
      } else {
        throw new RuntimeException("I don't know what this is?! [" + request + "].");
      }

      job.setHttpStatus(HttpStatus.OK.value());
      job.setRawData(request);
      jobRepository.save(job);
    } catch (IOException e) {
      e.printStackTrace();
    }
    LOG.info("Responding to request...");
    return ResponseEntity.status(HttpStatus.OK).body(null);
  }
}
