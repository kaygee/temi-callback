package callback;

import callback.beans.CookiesForRoleAndEnvironment;
import callback.beans.InitializationStatus;
import callback.exception.CookiesNotFoundException;
import callback.repository.CookiesRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class RestServiceController {

  private static final Logger LOG = LoggerFactory.getLogger(RestServiceController.class);

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

  @DeleteMapping(value = "/cookies/{role}/{environment}/delete")
  public ResponseEntity<?> deleteCookies(
      @PathVariable(value = "role") String role,
      @PathVariable(value = "environment") String environment) {
    LOG.info("Delete request for role [" + role + "] with environment [" + environment + "].");
    Optional<CookiesForRoleAndEnvironment> cookies =
        cookieRepository.findCookies(role, environment);
    if (cookies.isPresent()) {
      cookieRepository.deleteById(cookies.get().getDatabaseId());
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
    throw new CookiesNotFoundException(role, environment);
  }

  @GetMapping(
      value = "/cookies/{role}/{environment}/exists",
      produces = {"application/json"})
  public ResponseEntity<?> hasCookies(
      @PathVariable(value = "role") String role,
      @PathVariable(value = "environment") String environment) {
    LOG.info("Exists request for  role [" + role + "] with environment [" + environment + "].");
    Optional<CookiesForRoleAndEnvironment> cookies =
        cookieRepository.findCookies(role, environment);
    if (cookies.isPresent()) {
      return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    throw new CookiesNotFoundException(role, environment);
  }

  @GetMapping(
      value = "/cookies/{role}/{environment}",
      produces = {"application/json"})
  public CookiesForRoleAndEnvironment getCookies(
      @PathVariable(value = "role") String role,
      @PathVariable(value = "environment") String environment) {
    LOG.info("Get request for role [" + role + "] with environment [" + environment + "].");
    Optional<CookiesForRoleAndEnvironment> cookies =
        cookieRepository.findCookies(role, environment);
    if (cookies.isPresent()) {
      return cookies.get();
    }
    throw new CookiesNotFoundException(role, environment);
  }

  @RequestMapping(
      value = "/cookies",
      method = {POST})
  @ResponseBody
  public ResponseEntity<Object> postCookies(@RequestBody String request) {
    CookiesForRoleAndEnvironment cookies;
    try {
      cookies = new ObjectMapper().readValue(request, CookiesForRoleAndEnvironment.class);
    } catch (JsonProcessingException e) {
      throw new IllegalArgumentException(e.getMessage());
    }
    LOG.info(
        "Save request for role ["
            + cookies.getRole()
            + "] with environment ["
            + cookies.getEnvironment()
            + "].");
    cookieRepository.save(cookies);
    return ResponseEntity.status(HttpStatus.CREATED).body(null);
  }
}
