package callback;

import callback.beans.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class CallbackController {

  private static final Logger LOG = LoggerFactory.getLogger(CallbackController.class);

  @RequestMapping(value = "/callback", method = POST)
  @ResponseBody
  public void postCallack(@RequestBody String job) {
    LOG.info("Received callback for job [" + job + "].");
  }

  @RequestMapping(value = "/error", method = GET)
  @ResponseBody
  public String error(@RequestBody Job job) {
    return "I can haz err";
  }

  @RequestMapping(value = "/callback-too-many-requests", method = POST)
  @ResponseBody
  public ResponseEntity<Object> postCallbackError(@RequestBody String job) {
    return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(null);
  }
}
