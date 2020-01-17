package callback.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "jobs")
@EntityListeners(AuditingEntityListener.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(
    value = {"received_at"},
    allowGetters = true)
public class Job {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long databaseId;

  @Column(name = "raw_data", columnDefinition = "CLOB NOT NULL")
  @Lob
  private String rawData;

  @Column(name = "http_status")
  private Integer httpStatus;

  @Column(name = "order_number")
  @JsonProperty("order_number")
  private String orderNumber;

  @Column(name = "client_ref")
  @JsonProperty("client_ref")
  private String clientReference;

  @Column(name = "comment")
  @JsonProperty("comment")
  private String comment;

  @Column(name = "id")
  @JsonProperty("id")
  private String id;

  @Column(name = "job_type")
  private String jobType;

  @NonNull
  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  @JsonProperty("status")
  private JobStatus status;

  @Column(name = "failure")
  @JsonProperty("failure")
  private String failure;

  @Column(name = "failure_detail")
  @JsonProperty("failure_detail")
  private String failureDetail;

  @Column(name = "web_url")
  @JsonProperty("web_url")
  private String webUrl;

  @Column(name = "duration_seconds")
  @JsonProperty("duration_seconds")
  private Long durationSeconds;

  @Column(name = "media_url")
  @JsonProperty("media_url")
  private String mediaUrl;

  @Column(name = "name")
  @JsonProperty("name")
  private String name;

  @Column(name = "callback_url")
  @JsonProperty("callback_url")
  private String callbackUrl;

  @Column(name = "metadata", length = 4096)
  @JsonProperty("metadata")
  private String metadata;

  @Column(name = "created_on")
  @JsonProperty("created_on")
  private String createdOn;

  @Column(name = "last_modified_on")
  @JsonProperty("last_modified_on")
  private String lastModifiedOn;

  @JsonProperty("completed_on")
  private String completedOn;

  @Column(name = "received_at", nullable = false)
  @LastModifiedDate
  @Temporal(TemporalType.TIMESTAMP)
  private Date receivedAt;

  public Integer getHttpStatus() {
    return httpStatus;
  }

  public void setHttpStatus(Integer httpStatus) {
    this.httpStatus = httpStatus;
  }

  public String getRawData() {
    return rawData;
  }

  public void setRawData(String rawData) {
    this.rawData = rawData;
  }

  public String getMediaUrl() {
    return mediaUrl;
  }

  public void setMediaUrl(String mediaUrl) {
    this.mediaUrl = mediaUrl;
  }

  public Date getReceivedAt() {
    return receivedAt;
  }

  public void setReceivedAt(Date receivedAt) {
    this.receivedAt = receivedAt;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public JobStatus getStatus() {
    return status;
  }

  public void setStatus(JobStatus status) {
    this.status = status;
  }

  public String getFailure() {
    return failure;
  }

  public void setFailure(String failure) {
    this.failure = failure;
  }

  public String getFailureDetail() {
    return failureDetail;
  }

  public void setFailureDetail(String failureDetail) {
    this.failureDetail = failureDetail;
  }

  public String getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(String createdOn) {
    this.createdOn = createdOn;
  }

  public String getWebUrl() {
    return webUrl;
  }

  public void setWebUrl(String webUrl) {
    this.webUrl = webUrl;
  }

  public Long getDurationSeconds() {
    return durationSeconds;
  }

  public void setDurationSeconds(Long durationSeconds) {
    this.durationSeconds = durationSeconds;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCallbackUrl() {
    return callbackUrl;
  }

  public void setCallbackUrl(String callbackUrl) {
    this.callbackUrl = callbackUrl;
  }

  public String getMetadata() {
    return metadata;
  }

  public void setMetadata(String metadata) {
    this.metadata = metadata;
  }

  public String getLastModifiedOn() {
    return lastModifiedOn;
  }

  public void setLastModifiedOn(String lastModifiedOn) {
    this.lastModifiedOn = lastModifiedOn;
  }

  public Long getDatabaseId() {
    return databaseId;
  }

  public void setDatabaseId(Long databaseId) {
    this.databaseId = databaseId;
  }

  public String getOrderNumber() {
    return orderNumber;
  }

  public void setOrderNumber(String orderNumber) {
    this.orderNumber = orderNumber;
  }

  public String getClientReference() {
    return clientReference;
  }

  public void setClientReference(String clientReference) {
    this.clientReference = clientReference;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public String getCompletedOn() {
    return completedOn;
  }

  public void setCompletedOn(String completedOn) {
    this.completedOn = completedOn;
  }

  public String getJobType() {
    return jobType;
  }

  public void setJobType(String jobType) {
    this.jobType = jobType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Job job = (Job) o;
    return Objects.equals(databaseId, job.databaseId)
        && Objects.equals(rawData, job.rawData)
        && httpStatus == job.httpStatus
        && Objects.equals(orderNumber, job.orderNumber)
        && Objects.equals(clientReference, job.clientReference)
        && Objects.equals(comment, job.comment)
        && Objects.equals(id, job.id)
        && jobType == job.jobType
        && status == job.status
        && Objects.equals(failure, job.failure)
        && Objects.equals(failureDetail, job.failureDetail)
        && Objects.equals(webUrl, job.webUrl)
        && Objects.equals(durationSeconds, job.durationSeconds)
        && Objects.equals(mediaUrl, job.mediaUrl)
        && Objects.equals(name, job.name)
        && Objects.equals(callbackUrl, job.callbackUrl)
        && Objects.equals(metadata, job.metadata)
        && Objects.equals(createdOn, job.createdOn)
        && Objects.equals(lastModifiedOn, job.lastModifiedOn)
        && Objects.equals(completedOn, job.completedOn)
        && Objects.equals(receivedAt, job.receivedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        databaseId,
        rawData,
        httpStatus,
        orderNumber,
        clientReference,
        comment,
        id,
        jobType,
        status,
        failure,
        failureDetail,
        webUrl,
        durationSeconds,
        mediaUrl,
        name,
        callbackUrl,
        metadata,
        createdOn,
        lastModifiedOn,
        completedOn,
        receivedAt);
  }

  @Override
  public String toString() {
    try {
      return new ObjectMapper().writeValueAsString(this);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    throw new RuntimeException("Couldn't write to JSON string!");
  }
}
