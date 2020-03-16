package callback.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OnPremisesBilling {

  @JsonProperty("revaiapi_endpoint")
  String revAiApiEndpoint;

  @JsonProperty("duration")
  String duration;

  @JsonProperty("billing_idguid")
  String billingIdGuid;

  @JsonProperty("user_token")
  String userToken;

  public OnPremisesBilling() {}

  public String getRevAiApiEndpoint() {
    return revAiApiEndpoint;
  }

  public void setRevAiApiEndpoint(String revAiApiEndpoint) {
    this.revAiApiEndpoint = revAiApiEndpoint;
  }

  public String getDuration() {
    return duration;
  }

  public void setDuration(String duration) {
    this.duration = duration;
  }

  public String getBillingIdGuid() {
    return billingIdGuid;
  }

  public void setBillingIdGuid(String billingIdGuid) {
    this.billingIdGuid = billingIdGuid;
  }

  public String getUserToken() {
    return userToken;
  }

  public void setUserToken(String userToken) {
    this.userToken = userToken;
  }

  @Override
  public String toString() {
    return "OnPremisesBilling{"
        + "revAiApiEndpoint='"
        + revAiApiEndpoint
        + '\''
        + ", duration='"
        + duration
        + '\''
        + ", billingIdGuid='"
        + billingIdGuid
        + '\''
        + ", userToken='"
        + userToken
        + '\''
        + '}';
  }
}
