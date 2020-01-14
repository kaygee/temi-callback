package callback.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InitializationStatus {

  @JsonProperty("success")
  private Boolean success;

  public InitializationStatus() {}

  public Boolean getSuccess() {
    return success;
  }

  public void setSuccess(Boolean success) {
    this.success = success;
  }

  @Override
  public String toString() {
    return "InitializationStatus{" + "success=" + success + '}';
  }
}
