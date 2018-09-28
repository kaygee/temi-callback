package callback;

import callback.beans.Job;
import callback.beans.JobCallback;
import callback.beans.JobStatus;
import callback.beans.JobType;
import callback.beans.OrderStatusInfo;
import callback.exception.JobNotFoundException;
import callback.repository.JobRepository;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class CallbackController {

  private static final Logger LOG = LoggerFactory.getLogger(CallbackController.class);

  @Autowired JobRepository jobRepository;

  @GetMapping("/jobs/count")
  public Integer getJobsCount() {
    return jobRepository.countJobs();
  }

  @GetMapping("/jobs")
  public List<Job> getJobs(
      @RequestParam(value = "type", required = false) String jobType,
      @RequestParam(value = "status", required = false) String jobStatus) {
    if (jobType != null && jobStatus != null) {
      return jobRepository.findByJobTypeAndStatus(
          jobType, JobStatus.valueOf(jobStatus.toUpperCase()));
    } else if (jobType == null && jobStatus != null) {
      return jobRepository.findByJobStatus(JobStatus.valueOf(jobStatus.toUpperCase()));
    } else if (jobType != null && jobStatus == null) {
      return jobRepository.findByJobType(JobType.valueOf(jobType.toUpperCase()));
    } else {
      return jobRepository.findAll();
    }
  }

  @GetMapping("/jobs/{id}/job-id")
  public List<Job> getJobById(@PathVariable(value = "id") String id) {
    try {
      return jobRepository.findByJobId(id);
    } catch (IllegalArgumentException e) {
      throw new JobNotFoundException("Job ID", id);
    }
  }

  @GetMapping("/jobs/{orderNumber}/order-number")
  public List<Job> getJobByOrderNumber(@PathVariable(value = "orderNumber") String orderNumber) {
    try {
      return jobRepository.findByOrderNumber(orderNumber);
    } catch (IllegalArgumentException e) {
      throw new JobNotFoundException("Job ID", orderNumber);
    }
  }

  @GetMapping("/jobs/{status}/status")
  public List<Job> getJobByStatus(@PathVariable(value = "status") String jobStatus) {
    try {
      return jobRepository.findByJobStatus(JobStatus.valueOf(jobStatus.toUpperCase()));
    } catch (IllegalArgumentException e) {
      throw new JobNotFoundException("Job Status", jobStatus);
    }
  }

  @RequestMapping(
      value = "/successful",
      method = {GET, POST})
  @ResponseBody
  public ResponseEntity<Object> respondSuccessful(@RequestBody String request) {
    LOG.info(request);
    try {
      JobCallback job = new ObjectMapper().readValue(request, JobCallback.class);
      job.getJob().setHttpStatus(HttpStatus.OK);
      job.getJob().setRawData(request);
      jobRepository.save(job.getJob());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return ResponseEntity.status(HttpStatus.OK).body(null);
  }

  @RequestMapping(
      value = "/successful-rev-ai",
      method = {GET, POST})
  @ResponseBody
  public ResponseEntity<Object> respondOkRevAi(@RequestBody String request) {
    LOG.info(request);
    try {
      JobCallback job = new ObjectMapper().readValue(request, JobCallback.class);
      job.getJob().setRawData(request);
      job.getJob().setHttpStatus(HttpStatus.OK);
      job.getJob().setJobType(JobType.REVAI);
      jobRepository.save(job.getJob());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return ResponseEntity.status(HttpStatus.OK).body(null);
  }

  @RequestMapping(
      value = "/successful-rev-api",
      method = {GET, POST})
  @ResponseBody
  public ResponseEntity<Object> respondOkRevApi(@RequestBody String request) {
    LOG.info(request);
    try {
      OrderStatusInfo job = new ObjectMapper().readValue(request, OrderStatusInfo.class);
      job.getJob().setRawData(request);
      job.getJob().setHttpStatus(HttpStatus.OK);
      job.getJob().setJobType(JobType.REVAPI);
      jobRepository.save(job.getJob());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return ResponseEntity.status(HttpStatus.OK).body(null);
  }

  @RequestMapping(
      value = "/bad-request",
      method = {GET, POST})
  @ResponseBody
  public ResponseEntity<Object> respondBadRequest(@RequestBody String request) {
    LOG.info(request);
    try {
      JobCallback job = new ObjectMapper().readValue(request, JobCallback.class);
      job.getJob().setRawData(request);
      job.getJob().setHttpStatus(HttpStatus.BAD_REQUEST);
      jobRepository.save(job.getJob());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
  }

  @RequestMapping(
      value = "/unauthorized",
      method = {GET, POST})
  @ResponseBody
  public ResponseEntity<Object> respondUnauthorized(@RequestBody String request) {
    LOG.info(request);
    try {
      JobCallback job = new ObjectMapper().readValue(request, JobCallback.class);
      job.getJob().setRawData(request);
      job.getJob().setHttpStatus(HttpStatus.UNAUTHORIZED);
      jobRepository.save(job.getJob());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
  }

  @RequestMapping(
      value = "/internal-server-error",
      method = {GET, POST})
  @ResponseBody
  public ResponseEntity<Object> respondInternalServerError(@Valid @RequestBody String request) {
    LOG.info(request);
    try {
      JobCallback job = new ObjectMapper().readValue(request, JobCallback.class);
      job.getJob().setRawData(request);
      job.getJob().setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
      jobRepository.save(job.getJob());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
  }

  @RequestMapping(
      value = "/gone",
      method = {GET, POST})
  @ResponseBody
  public ResponseEntity<Object> respondGone(@RequestBody String request) {
    LOG.info(request);
    try {
      JobCallback job = new ObjectMapper().readValue(request, JobCallback.class);
      job.getJob().setRawData(request);
      job.getJob().setHttpStatus(HttpStatus.GONE);
      jobRepository.save(job.getJob());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return ResponseEntity.status(HttpStatus.GONE).body(null);
  }
}
