package callback.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OnPremisesFailure {

  @JsonProperty("failure")
  String failure;

  @JsonProperty("metadata")
  String metadata;

  @JsonProperty("failure_detail")
  String failureDetail;

  public OnPremisesFailure() {}

  public String getFailure() {
    return failure;
  }

  public void setFailure(String failure) {
    this.failure = failure;
  }

  public String getMetadata() {
    return metadata;
  }

  public void setMetadata(String metadata) {
    this.metadata = metadata;
  }

  public String getFailureDetail() {
    return failureDetail;
  }

  public void setFailureDetail(String failureDetail) {
    this.failureDetail = failureDetail;
  }

  @Override
  public String toString() {
    return "OnPremisesFailure{"
        + "failure='"
        + failure
        + '\''
        + ", metadata='"
        + metadata
        + '\''
        + ", failureDetail='"
        + failureDetail
        + '\''
        + '}';
  }
}
