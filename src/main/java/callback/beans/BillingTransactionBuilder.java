package callback.beans;

public class BillingTransactionBuilder {

  public static class Builder {

    private String requestHeaders;
    private String requestBody;
    private Integer responseHttpStatus;
    private String responseHeaders;
    private String responseBody;

    public BillingTransaction build() {
      BillingTransaction billingTransaction = new BillingTransaction();
      billingTransaction.setRequestHeaders(this.requestHeaders);
      billingTransaction.setRequestBody(this.requestBody);
      billingTransaction.setResponseHttpStatus(this.responseHttpStatus);
      billingTransaction.setResponseHeaders(this.responseHeaders);
      billingTransaction.setResponseBody(this.responseBody);
      return billingTransaction;
    }

    public Builder setRequestBody(String requestBody) {
      this.requestBody = requestBody;
      return this;
    }

    public Builder setResponseHttpStatus(Integer responseHttpStatus) {
      this.responseHttpStatus = responseHttpStatus;
      return this;
    }

    public Builder setRequestHeaders(String requestHeaders) {
      this.requestHeaders = requestHeaders;
      return this;
    }

    public Builder setResponseHeaders(String responseHeaders) {
      this.responseHeaders = responseHeaders;
      return this;
    }

    public Builder setResponseBody(String responseBody) {
      this.responseBody = responseBody;
      return this;
    }
  }
}
