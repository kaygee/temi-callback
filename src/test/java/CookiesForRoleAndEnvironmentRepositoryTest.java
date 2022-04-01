import callback.beans.CookiesForRoleAndEnvironment;
import callback.beans.RevCookie;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.Filter;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.http.HttpStatus;
import org.junit.Test;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static io.restassured.RestAssured.given;
import static io.restassured.config.RedirectConfig.redirectConfig;
import static org.assertj.core.api.Assertions.assertThat;

public class CookiesForRoleAndEnvironmentRepositoryTest {

  private static final String LOCALHOST_URL = "http://localhost:7331/";
  private static final String COOKIES_PATH = "cookies/";
  private static final String DELETE_PATH = "delete/";
  private static final String GET_PATH = "get/";
  private static final String ROLE_PATH = "{role}/";
  private static final String ENVIRONMENT_PATH = "{environment}/";
  private static final String GET_COOKIES_PATH =
      COOKIES_PATH + ROLE_PATH + ENVIRONMENT_PATH + GET_PATH;
  private static final String DELETE_COOKIES_PATH =
      COOKIES_PATH + ROLE_PATH + ENVIRONMENT_PATH + DELETE_PATH;

  @Test
  public void canReturnMethodNotAllowed() {
    var response = given().spec(getRequestSpecification()).when().get(COOKIES_PATH).andReturn();
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_METHOD_NOT_ALLOWED);
  }

  @Test
  public void canReturnNotFound() {
    var path = GET_COOKIES_PATH.replace("{role}", "role").replace("{environment}", "environment");
    var response = given().spec(getRequestSpecification()).when().get(path).andReturn();
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_NOT_FOUND);
  }

  @Test
  public void canDeleteCookies() {
    var role = RandomStringUtils.randomAlphabetic(30);
    var environment = RandomStringUtils.randomAlphabetic(30);
    var username = RandomStringUtils.randomAlphabetic(30);
    var rememberMe = RandomUtils.nextBoolean();

    CookiesForRoleAndEnvironment postCookies = new CookiesForRoleAndEnvironment();
    postCookies.setEnvironment(environment);
    postCookies.setRole(role);
    postCookies.setUsername(username);

    Set<RevCookie> revCookies = new HashSet<>();
    var cookie = new RevCookie("name", "value", "domain", "path", new Date(), true, true, "same");
    revCookies.add(cookie);
    postCookies.setCookies(revCookies);
    postCookies.setRememberMe(rememberMe);

    var postResponse =
        given()
            .spec(getRequestSpecification())
            .when()
            .body(postCookies)
            .post(COOKIES_PATH)
            .andReturn();
    assertThat(postResponse.getStatusCode()).isEqualTo(HttpStatus.SC_CREATED);

    var deletePath =
        DELETE_COOKIES_PATH.replace("{role}", role).replace("{environment}", environment);
    var deleteResponse =
        given().spec(getRequestSpecification()).when().delete(deletePath).andReturn();
    assertThat(deleteResponse.getStatusCode()).isEqualTo(HttpStatus.SC_NO_CONTENT);
  }

  @Test
  public void canPostAndGetCookies() {
    var role = RandomStringUtils.randomAlphabetic(30);
    var environment = RandomStringUtils.randomAlphabetic(30);
    var username = RandomStringUtils.randomAlphabetic(30);
    var rememberMe = RandomUtils.nextBoolean();

    CookiesForRoleAndEnvironment postCookies = new CookiesForRoleAndEnvironment();
    postCookies.setEnvironment(environment);
    postCookies.setRole(role);
    postCookies.setUsername(username);
    postCookies.setRememberMe(rememberMe);

    Set<RevCookie> revCookies = new HashSet<>();
    var cookie = new RevCookie("name", "value", "domain", "path", new Date(), true, true, "same");
    revCookies.add(cookie);
    postCookies.setCookies(revCookies);

    var postResponse =
        given()
            .spec(getRequestSpecification())
            .when()
            .body(postCookies)
            .post(COOKIES_PATH)
            .andReturn();
    assertThat(postResponse.getStatusCode()).isEqualTo(HttpStatus.SC_CREATED);

    var getPath = GET_COOKIES_PATH.replace("{role}", role).replace("{environment}", environment);
    var getResponse = given().spec(getRequestSpecification()).when().get(getPath).andReturn();
    assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
    CookiesForRoleAndEnvironment getCookies = getResponse.as(CookiesForRoleAndEnvironment.class);
    assertThat(getCookies.getEnvironment()).isEqualTo(environment);
    assertThat(getCookies.getRole()).isEqualTo(role);
    assertThat(getCookies.getUsername()).isEqualTo(username);
    assertThat(getCookies.getCookies()).isEqualTo(revCookies);
    assertThat(getCookies.getRememberMe()).isEqualTo(rememberMe);
  }

  private URL getUrl() {
    try {
      return new URL(LOCALHOST_URL);
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  private RequestSpecification getRequestSpecification() {
    var requestSpecBuilder = new RequestSpecBuilder();
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
