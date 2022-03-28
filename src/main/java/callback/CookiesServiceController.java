package callback;

import callback.beans.CookiesForRoleAndEnvironment;
import callback.beans.InitializationStatus;
import callback.exception.CookiesNotFoundException;
import callback.repository.CookiesRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class CookiesServiceController {

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
  public CookiesForRoleAndEnvironment getCookies(
      @PathVariable(value = "role") String role,
      @PathVariable(value = "environment") String environment) {
    Optional<CookiesForRoleAndEnvironment> cookies = cookieRepository.findCookies(role, environment);
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
    cookieRepository.save(cookies);
    return ResponseEntity.status(HttpStatus.CREATED).body(null);
  }
}
