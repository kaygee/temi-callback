package callback.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ElementType {
  @JsonProperty("punct")
  PUNCT("punct"),

  @JsonProperty("text")
  TEXT("text"),

  @JsonProperty("unknown")
  UNKNOWN("unknown");

  private final String val;

  ElementType(String val) {
    this.val = val;
  }

  @Override
  public String toString() {
    return val;
  }
}
