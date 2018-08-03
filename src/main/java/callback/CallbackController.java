package callback;

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
  public ResponseEntity<Object> postCallack(@RequestBody String job) {
    LOG.info("Received HTTP 200 callback for job [" + job + "].");
    return ResponseEntity.status(HttpStatus.OK).body(null);
  }

  @RequestMapping(value = "/callback-bad-request", method = POST)
  @ResponseBody
  public ResponseEntity<Object> postCallbackBadRequest(@RequestBody String job) {
    LOG.info("Received 400 callback for job [" + job + "].");
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
  }

  @RequestMapping(value = "/callback-unauthorized", method = POST)
  @ResponseBody
  public ResponseEntity<Object> postCallbackUnauthorized(@RequestBody String job) {
    LOG.info("Received 401 callback for job [" + job + "].");
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
  }

  @RequestMapping(
      value = "/internal-server-error",
      method = {GET, POST})
  @ResponseBody
  public ResponseEntity<Object> getInternalServerError() {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
  }

  @RequestMapping(value = "/gone")
  @ResponseBody
  public ResponseEntity<Object> respondWithGone() {
    return ResponseEntity.status(HttpStatus.GONE).body(null);
  }
}
