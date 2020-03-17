package callback.beans;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "billing_transactions")
@EntityListeners(AuditingEntityListener.class)
public class BillingTransaction {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long databaseId;

  @Column(name = "request_headers")
  String requestHeaders;

  @Column(name = "request_body", columnDefinition = "CLOB NOT NULL")
  @Lob
  private String requestBody;

  @Column(name = "response_http_status")
  private Integer responseHttpStatus;

  @Column(name = "response_headers")
  String responseHeaders;

  @Column(name = "response_body", columnDefinition = "CLOB NOT NULL")
  @Lob
  private String responseBody;

  public BillingTransaction() {}

  public Long getDatabaseId() {
    return databaseId;
  }

  public void setDatabaseId(Long databaseId) {
    this.databaseId = databaseId;
  }

  public String getRequestBody() {
    return requestBody;
  }

  public void setRequestBody(String requestBody) {
    this.requestBody = requestBody;
  }

  public Integer getResponseHttpStatus() {
    return responseHttpStatus;
  }

  public void setResponseHttpStatus(Integer responseHttpStatus) {
    this.responseHttpStatus = responseHttpStatus;
  }

  public String getRequestHeaders() {
    return requestHeaders;
  }

  public void setRequestHeaders(String requestHeaders) {
    this.requestHeaders = requestHeaders;
  }

  public String getResponseHeaders() {
    return responseHeaders;
  }

  public void setResponseHeaders(String responseHeaders) {
    this.responseHeaders = responseHeaders;
  }

  public String getResponseBody() {
    return responseBody;
  }

  public void setResponseBody(String responseBody) {
    this.responseBody = responseBody;
  }
}
