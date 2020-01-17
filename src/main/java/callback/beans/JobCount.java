package callback.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JobCount {

  @JsonProperty("overall-count")
  private Integer overallCount;

  @JsonProperty("initialization-count")
  private Integer initializationCount;

  @JsonProperty("failed-count")
  private Integer failedCount;

  @JsonProperty("transcribed-count")
  private Integer transcribedCount;

  public JobCount() {}

  public JobCount(Integer count) {
    this.overallCount = count;
  }

  public Integer getOverallCount() {
    return overallCount;
  }

  public void setOverallCount(Integer overallCount) {
    this.overallCount = overallCount;
  }

  public Integer getInitializationCount() {
    return initializationCount;
  }

  public void setInitializationCount(Integer initializationCount) {
    this.initializationCount = initializationCount;
  }

  public Integer getFailedCount() {
    return failedCount;
  }

  public void setFailedCount(Integer failedCount) {
    this.failedCount = failedCount;
  }

  public Integer getTranscribedCount() {
    return transcribedCount;
  }

  public void setTranscribedCount(Integer transcribedCount) {
    this.transcribedCount = transcribedCount;
  }

  @Override
  public String toString() {
    return "JobCount{"
        + "overallCount="
        + overallCount
        + ", initializationCount="
        + initializationCount
        + ", failedCount="
        + failedCount
        + ", transcribedCount="
        + transcribedCount
        + '}';
  }
}
