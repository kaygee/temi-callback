package callback;

import callback.beans.Cookies;
import callback.beans.InitializationStatus;
import callback.exception.CookiesNotFoundException;
import callback.repository.CookiesRepository;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
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
import java.io.StringWriter;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class CallbackController {

  private static final Logger LOG = LoggerFactory.getLogger(CallbackController.class);

  @Autowired CookiesRepository cookieRepository;

  @GetMapping(
      value = "/health",
      produces = {"application/json"})
  public String healthCheck() {
    InitializationStatus status = new InitializationStatus();
    status.setSuccess(true);
    try {
      return new ObjectMapper().writeValueAsString(status);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  @GetMapping(
      value = "/cookies/{role}/{environment}",
      produces = {"application/json"})
  public Cookies getCookies(
      @PathVariable(value = "role") String role,
      @PathVariable(value = "environment") String environment) {
    Optional<Cookies> cookies = cookieRepository.findCookies(role, environment);
    if (cookies.isPresent()) {
      return cookies.get();
    }
    throw new CookiesNotFoundException(role, environment);
  }

  @RequestMapping(
      value = "/cookies",
      method = {POST})
  @ResponseBody
  public ResponseEntity<Object> saveCookies(@RequestBody String request) {
    LOG.debug("Save cookies request received... [" + request + "].");
    Cookies cookies = new Cookies();
    cookieRepository.save(cookies);
    LOG.info("Responding to save cookies request...");
    return ResponseEntity.status(HttpStatus.OK).body(null);
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
