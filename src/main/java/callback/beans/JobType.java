package callback.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum JobType {
  @JsonProperty("revai")
  REVAI("revai"),

  @JsonProperty("temi")
  TEMI("temi");

  private final String val;

  JobType(String val) {
    this.val = val;
  }

  @Override
  public String toString() {
    return val;
  }
}
