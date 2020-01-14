package callback.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum JobType {
  @JsonProperty("failed")
  FAILED("failed"),

  @JsonProperty("transcription")
  TRANSCRIPTION("transcription"),

  @JsonProperty("initializataion")
  INITIALIZATION("initializataion");

  private final String val;

  JobType(String val) {
    this.val = val;
  }

  @Override
  public String toString() {
    return val;
  }
}
