package callback.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OnPremisesBilling {

  @JsonProperty("duration")
  String duration;

  @JsonProperty("billing_id")
  String billId;

  @JsonProperty("user_token")
  String userToken;

  @JsonProperty("request_sent_on")
  String requestSentOn;

  @JsonProperty("billing_idguid")
  String billingIdGuid;

  @JsonProperty("revaiapi_endpoint")
  String revAiEndpoint;

  @JsonProperty("metadata")
  String metadata;

  public OnPremisesBilling() {}

  public String getRevAiEndpoint() {
    return revAiEndpoint;
  }

  public void setRevAiEndpoint(String revAiEndpoint) {
    this.revAiEndpoint = revAiEndpoint;
  }

  public String getBillId() {
    return billId;
  }

  public void setBillId(String billId) {
    this.billId = billId;
  }

  public String getRequestSentOn() {
    return requestSentOn;
  }

  public void setRequestSentOn(String requestSentOn) {
    this.requestSentOn = requestSentOn;
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

  public String getMetadata() {
    return metadata;
  }

  public void setMetadata(String metadata) {
    this.metadata = metadata;
  }

  @Override
  public String toString() {
    return "OnPremisesBilling{" +
            "duration='" + duration + '\'' +
            ", billId='" + billId + '\'' +
            ", userToken='" + userToken + '\'' +
            ", requestSentOn='" + requestSentOn + '\'' +
            ", billingIdGuid='" + billingIdGuid + '\'' +
            ", revAiEndpoint='" + revAiEndpoint + '\'' +
            ", metadata='" + metadata + '\'' +
            '}';
  }
}
