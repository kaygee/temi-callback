package callback.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum JobStatus {
  // Rev API
  @JsonProperty("Cancelled")
  CANCELLED("Cancelled"),

  // Rev API
  @JsonProperty("Complete")
  COMPLETE("Complete"),

  // Temi API and Rev.ai
  @JsonProperty("failed")
  FAILED("failed"),

  // Temi API and Rev.ai
  @JsonProperty("transcribed")
  TRANSCRIBED("transcribed"),

  // Temi API and Rev.ai
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
