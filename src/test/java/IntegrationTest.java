import callback.beans.Element;
import callback.beans.ElementType;
import callback.beans.Job;
import callback.beans.Monologue;
import callback.beans.OnPremisesFailure;
import callback.beans.Transcript;
import callback.beans.TranscriptCallback;
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

public class IntegrationTest {

  private static final Logger LOG = LoggerFactory.getLogger(IntegrationTest.class);

  private static final String LOCALHOST_URL = "http://localhost:8080/";
  private static final String JOBS_PATH = "jobs" + "/all";
  private static final String JOBS_COUNT_PATH = JOBS_PATH + "/count";
  private static final String JOBS_FAILED_PATH = JOBS_PATH + "/failed";
  private static final String JOBS_TRANSCIBED_PATH = JOBS_PATH + "/transcribed";
  private static final String SUCCESSFUL_REQUEST_PATH = "successful";

  @Test
  public void canGetJobCount() {
    Integer amount =
        given()
            .spec(getRequestSpecification())
            .contentType(ContentType.TEXT)
            .when()
            .get(JOBS_COUNT_PATH)
            .as(Integer.class);
    assertThat(amount).isGreaterThanOrEqualTo(0);
    LOG.info("Job count [" + amount + "].");
  }

  @Test
  public void canGetJobByFailedType() {
    Job[] jobs =
        given().spec(getRequestSpecification()).with().when().get(JOBS_FAILED_PATH).as(Job[].class);
    LOG.info("Failed jobs...");
    for (Job job : jobs) {
      LOG.info(job.toString());
    }
  }

  @Test
  public void canGetJobByTranscribedType() {
    Job[] jobs =
        given()
            .spec(getRequestSpecification())
            .with()
            .when()
            .get(JOBS_TRANSCIBED_PATH)
            .as(Job[].class);
    LOG.info("Transcribed jobs...");
    for (Job job : jobs) {
      LOG.info(job.toString());
    }
  }

  @Test
  public void canGetJobs() {
    Job[] jobs = given().spec(getRequestSpecification()).when().get(JOBS_PATH).as(Job[].class);
    for (Job job : jobs) {
      LOG.info(job.toString());
    }
  }

  @Test
  public void canRespondWithHttpSuccessCodeToFailure() {
    OnPremisesFailure onPremisesFailure = new OnPremisesFailure();
    onPremisesFailure.setFailure("transcoding");
    onPremisesFailure.setFailureDetail("Transcoding failed. Please check logs for more details");
    onPremisesFailure.setMetadata("Should fail txt format");

    Response response =
        given()
            .spec(getRequestSpecification())
            .contentType(ContentType.JSON)
            .when()
            .body(onPremisesFailure)
            .post(SUCCESSFUL_REQUEST_PATH);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
  }

  @Test
  public void canRespondWithHttpSuccesCodeToTranscript() {
    List<Element> elements = new ArrayList<>();
    elements.add(getElement(ElementType.TEXT, "so", 16.39, 16.96, 0.99));
    elements.add(getElement(ElementType.PUNCT, " ", null, null, null));
    elements.add(getElement(ElementType.TEXT, "two", 20.86, 21.07, 0.97));

    Monologue monologue = new Monologue();
    monologue.setSpeaker(1);
    monologue.setElements(elements.toArray(new Element[elements.size()]));

    List<Monologue> monologues = new ArrayList<>();
    monologues.add(monologue);

    Transcript transcript = new Transcript();
    transcript.setMetadata("Let it go!");
    transcript.setMonologues(monologues.toArray(new Monologue[monologues.size()]));

    TranscriptCallback transcriptCallback = new TranscriptCallback();
    transcriptCallback.setTranscript(transcript);

    Response response =
        given()
            .spec(getRequestSpecification())
            .contentType(ContentType.JSON)
            .when()
            .body(transcriptCallback)
            .post(SUCCESSFUL_REQUEST_PATH);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
  }

  private Element getElement(
      ElementType type, String value, Double ts, Double endTs, Double confidence) {
    Element element = new Element();
    element.setType(type);
    element.setValue(value);
    element.setTs(ts);
    element.setEndTs(endTs);
    element.setConfidence(confidence);
    return element;
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
