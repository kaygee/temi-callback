package callback.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CookiesNotFoundException extends RuntimeException {

  private String role;
  private String environment;

  public CookiesNotFoundException(String role, String environment) {
    super(String.format("Cookie not found for %s in '%s'", role, environment));
    this.role = role;
    this.environment = environment;
  }

  public String getRole() {
    return role;
  }

  public String getEnvironment() {
    return environment;
  }
}
