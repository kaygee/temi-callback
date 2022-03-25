import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.Filter;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.config.RedirectConfig.redirectConfig;
import static org.assertj.core.api.Assertions.assertThat;

public class CookiesRepositoryTest {

  private static final Logger LOG = LoggerFactory.getLogger(CookiesRepositoryTest.class);

  private static final String LOCALHOST_URL = "http://localhost:7331/";
  private static final String COOKIES_PATH = "cookies/";
  private static final String ROLE_PATH = "{role}/";
  private static final String ENVIRONMENT_PATH = "{environment}";
  private static final String FIND_COOKIES_PATH = COOKIES_PATH + ROLE_PATH + ENVIRONMENT_PATH;

  @Test
  public void canReturnMethodNotAllowed() {
    Response response =
        given().spec(getRequestSpecification()).when().get(COOKIES_PATH).andReturn();
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_METHOD_NOT_ALLOWED);
  }

  @Test
  public void canReturnNotFound() {
    String path =
        FIND_COOKIES_PATH.replace("{role}", "role").replace("{environment}", "environment");
    Response response = given().spec(getRequestSpecification()).when().get(path).andReturn();
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_NOT_FOUND);
  }

  private URL getUrl() {
    try {
      return new URL(LOCALHOST_URL);
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  private RequestSpecification getRequestSpecification() {
    RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
    requestSpecBuilder.setBaseUri(
        getUrl().getProtocol() + "://" + getUrl().getHost() + ":" + getUrl().getPort());
    requestSpecBuilder.setConfig(
        RestAssured.config().redirect(redirectConfig().followRedirects(false)));
    requestSpecBuilder.addFilters(getFilters());
    requestSpecBuilder.setContentType(ContentType.JSON);
    return requestSpecBuilder.build();
  }

  public List<Filter> getFilters() {
    List<Filter> filters = new ArrayList<>();
    filters.add(new ErrorLoggingFilter());
    filters.add(new RequestLoggingFilter());
    filters.add(new ResponseLoggingFilter());
    return filters;
  }
}
