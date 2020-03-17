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
@Table(name = "billing_requests")
@EntityListeners(AuditingEntityListener.class)
public class BillingRequest {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long databaseId;

  @Column(name = "raw_data", columnDefinition = "CLOB NOT NULL")
  @Lob
  private String rawData;

  @Column(name = "http_status")
  private Integer httpStatus;

  @Column(name = "headers")
  String headers;

  @Column(name = "billing_type")
  private String billingType;

  public BillingRequest() {}

  public Long getDatabaseId() {
    return databaseId;
  }

  public void setDatabaseId(Long databaseId) {
    this.databaseId = databaseId;
  }

  public String getRawData() {
    return rawData;
  }

  public void setRawData(String rawData) {
    this.rawData = rawData;
  }

  public Integer getHttpStatus() {
    return httpStatus;
  }

  public void setHttpStatus(Integer httpStatus) {
    this.httpStatus = httpStatus;
  }

  public String getHeaders() {
    return headers;
  }

  public void setHeaders(String headers) {
    this.headers = headers;
  }

  public String getBillingType() {
    return billingType;
  }

  public void setBillingType(String billingType) {
    this.billingType = billingType;
  }
}
