package callback.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.NonNull;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public enum JobStatus {
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
