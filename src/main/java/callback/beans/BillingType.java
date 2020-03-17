package callback.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum BillingType {
  @JsonProperty("billing_request_received")
  BILLING_REQUEST_RECEIVED("billing_request_received"),

  @JsonProperty("billing_request_sent")
  BILLING_REQUEST_SENT("billing_request_sent"),

  @JsonProperty("billing_response_received")
  BILLING_RESPONSE_RECEIVED("billing_response_received");

  private final String val;

  BillingType(String val) {
    this.val = val;
  }

  @Override
  public String toString() {
    return val;
  }
}
