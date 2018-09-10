package callback;

import callback.beans.Job;
import callback.beans.JobCallback;
import callback.beans.JobStatus;
import callback.exception.JobNotFoundException;
import callback.repository.JobRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class CallbackController {

  private static final Logger LOG = LoggerFactory.getLogger(CallbackController.class);

  @Autowired JobRepository jobRepository;

  @GetMapping("/jobs")
  public List<Job> getAllJobs() {
    return jobRepository.findAll();
  }

  @PostMapping("/jobs/job/{id}")
  public List<Job> getJobById(@PathVariable(value = "id") String id) {
    try {
      return jobRepository.findByJobId(id);
    } catch (IllegalArgumentException e) {
      throw new JobNotFoundException("Job ID", id);
    }
  }

  @PostMapping("/jobs/status/{status}")
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
  public ResponseEntity<Object> respondSuccessful(@Valid @RequestBody JobCallback jobCallback) {
    LOG.info(jobCallback.getJob().toString());
    jobRepository.save(jobCallback.getJob());
    return ResponseEntity.status(HttpStatus.OK).body(null);
  }

  @RequestMapping(
      value = "/bad-request",
      method = {GET, POST})
  @ResponseBody
  public ResponseEntity<Object> respondBadRequest(@Valid @RequestBody JobCallback jobCallback) {
    jobRepository.save(jobCallback.getJob());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
  }

  @RequestMapping(
      value = "/unauthorized",
      method = {GET, POST})
  @ResponseBody
  public ResponseEntity<Object> respondUnauthorized(@Valid @RequestBody JobCallback jobCallback) {
    jobRepository.save(jobCallback.getJob());
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
  }

  @RequestMapping(
      value = "/internal-server-error",
      method = {GET, POST})
  @ResponseBody
  public ResponseEntity<Object> respondInternalServerError(
      @Valid @RequestBody JobCallback jobCallback) {
    jobRepository.save(jobCallback.getJob());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
  }

  @RequestMapping(
      value = "/gone",
      method = {GET, POST})
  @ResponseBody
  public ResponseEntity<Object> respondGone(@Valid @RequestBody JobCallback jobCallback) {
    jobRepository.save(jobCallback.getJob());
    return ResponseEntity.status(HttpStatus.GONE).body(null);
  }
}
