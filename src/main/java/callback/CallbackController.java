package callback;

import callback.beans.Job;
import callback.beans.JobCallback;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class CallbackController {

  private static final Logger LOG = LoggerFactory.getLogger(CallbackController.class);

  @RequestMapping(
      value = "/successful",
      method = {GET, POST})
  @ResponseBody
  public ResponseEntity<Object> respondSuccessful(@RequestBody String requestBody) {
    LOG.info("Received HTTP 200 callback for job [" + requestBody + "].");
    try {
      JobCallback jobCallback = new ObjectMapper().readValue(requestBody, JobCallback.class);
      LOG.info("deserialized job.");
      LOG.info(jobCallback.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return ResponseEntity.status(HttpStatus.OK).body(null);
  }

  @RequestMapping(
      value = "/bad-request",
      method = {GET, POST})
  @ResponseBody
  public ResponseEntity<Object> respondBadRequest(@RequestBody String requestBody) {
    LOG.info("Received 400 callback for job [" + requestBody + "].");
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
  }

  @RequestMapping(
      value = "/unauthorized",
      method = {GET, POST})
  @ResponseBody
  public ResponseEntity<Object> respondUnauthorized(@RequestBody String requestBody) {
    LOG.info("Received 401 callback for job [" + requestBody + "].");
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
  }

  @RequestMapping(
      value = "/internal-server-error",
      method = {GET, POST})
  @ResponseBody
  public ResponseEntity<Object> respondInternalServerError() {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
  }

  @RequestMapping(
      value = "/gone",
      method = {GET, POST})
  @ResponseBody
  public ResponseEntity<Object> responsdGone() {
    return ResponseEntity.status(HttpStatus.GONE).body(null);
  }
}
