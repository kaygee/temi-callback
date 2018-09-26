package callback.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum JobStatus {
  @JsonProperty("cancelled")
  CANCELLED("cancelled"),

  @JsonProperty("delivered")
  DELIVERED("delivered"),

  @JsonProperty("failed")
  FAILED("failed"),

  @JsonProperty("transcribed")
  TRANSCRIBED("transcribed"),

  @JsonProperty("inProgress")
  INPROGRESS("inProgress");

  private final String val;

  JobStatus(String val) {
    this.val = val;
  }

  @Override
  public String toString() {
    return val;
  }
}
