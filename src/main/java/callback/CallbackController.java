package callback;

import callback.beans.Job;
import callback.beans.JobStatus;
import callback.beans.JobType;
import callback.beans.OnPremisesFailure;
import callback.beans.TranscriptCallback;
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

  @GetMapping("/jobs/all")
  public List<Job> getJobs() {
    return jobRepository.findAll();
  }

  @GetMapping("/jobs/failed")
  public List<Job> getFailedJobs() {
    return jobRepository.findByJobType(JobType.FAILED.toString());
  }

  @GetMapping("/jobs/transcribed")
  public List<Job> getTranscribedJobs() {
    return jobRepository.findByJobType(JobType.TRANSCRIPTION.toString());
  }

  //  @GetMapping("/jobs/{id}/job-id")
  //  public List<Job> getJobById(@PathVariable(value = "id") String id) {
  //    try {
  //      return jobRepository.findByJobId(id);
  //    } catch (IllegalArgumentException e) {
  //      throw new JobNotFoundException("Job ID", id);
  //    }
  //  }

  //  @GetMapping("/jobs/{orderNumber}/order-number")
  //  public List<Job> getJobByOrderNumber(@PathVariable(value = "orderNumber") String orderNumber)
  // {
  //    try {
  //      return jobRepository.findByOrderNumber(orderNumber);
  //    } catch (IllegalArgumentException e) {
  //      throw new JobNotFoundException("Job ID", orderNumber);
  //    }
  //  }

  // @GetMapping("/jobs/{status}/status")
  // public List<Job> getJobByStatus(@PathVariable(value = "status") String jobStatus) {
  //  try {
  //    return jobRepository.findByJobStatus(JobStatus.valueOf(jobStatus.toUpperCase()));
  //  } catch (IllegalArgumentException e) {
  //    throw new JobNotFoundException("Job Status", jobStatus);
  //  }
  // }

  @RequestMapping(
      value = "/successful",
      method = {GET, POST})
  @ResponseBody
  public ResponseEntity<Object> respondSuccessful(@RequestBody String request) {
    LOG.info("Incoming request [" + request + "].");
    Job job = new Job();
    try {
      if (request.contains("failure") && request.contains("metadata")) {
        OnPremisesFailure onPremisesFailure =
            new ObjectMapper().readValue(request, OnPremisesFailure.class);
        job.setFailure(onPremisesFailure.getFailure());
        job.setFailureDetail(onPremisesFailure.getFailureDetail());
        job.setMetadata(onPremisesFailure.getMetadata());
        job.setJobType(JobType.FAILED.toString());
      } else if (request.contains("transcript") && request.contains("monologues")) {
        TranscriptCallback transcriptCallback =
            new ObjectMapper().readValue(request, TranscriptCallback.class);
        job.setMetadata(transcriptCallback.getTranscript().getMetadata());
        job.setJobType(JobType.TRANSCRIPTION.toString());
      } else {
        throw new RuntimeException("I don't know what this is?! [" + request + "].");
      }

      job.setHttpStatus(HttpStatus.OK);
      job.setRawData(request);
      jobRepository.save(job);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return ResponseEntity.status(HttpStatus.OK).body(null);
  }
}
