package callback.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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

  @NotBlank
  @Column(name = "id")
  @JsonProperty("id")
  private String id;

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

  @NotBlank
  @Column(name = "web_url")
  @JsonProperty("web_url")
  private String webUrl;

  @Column(name = "duration_seconds")
  @JsonProperty("duration_seconds")
  private Long durationSeconds;

  @Column(name = "name")
  @JsonProperty("name")
  private String name;

  @Column(name = "callback_url")
  @JsonProperty("callback_url")
  private String callbackUrl;

  @Column(name = "metadata",length = 2048)
  @JsonProperty("metadata")
  private String metadata;

  @Column(name = "created_on")
  @JsonProperty("created_on")
  private String createdOn;

  @Column(name = "last_modified_on")
  @JsonProperty("last_modified_on")
  private String lastModifiedOn;

  @Column(name = "received_at", nullable = false)
  @LastModifiedDate
  @Temporal(TemporalType.TIMESTAMP)
  private Date receivedAt;

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

  @Override
  public String toString() {
    return "Job{"
        + "id='"
        + id
        + '\''
        + ", status="
        + status
        + ", failure='"
        + failure
        + '\''
        + ", failureDetail='"
        + failureDetail
        + '\''
        + ", createdOn="
        + createdOn
        + ", webUrl='"
        + webUrl
        + '\''
        + ", durationSeconds="
        + durationSeconds
        + ", name='"
        + name
        + '\''
        + ", callbackUrl='"
        + callbackUrl
        + '\''
        + ", metadata='"
        + metadata
        + '\''
        + ", lastModifiedOn='"
        + lastModifiedOn
        + '\''
        + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Job job = (Job) o;
    return Objects.equals(id, job.id)
        && status == job.status
        && Objects.equals(failure, job.failure)
        && Objects.equals(failureDetail, job.failureDetail)
        && Objects.equals(createdOn, job.createdOn)
        && Objects.equals(webUrl, job.webUrl)
        && Objects.equals(durationSeconds, job.durationSeconds)
        && Objects.equals(name, job.name)
        && Objects.equals(callbackUrl, job.callbackUrl)
        && Objects.equals(metadata, job.metadata)
        && Objects.equals(lastModifiedOn, job.lastModifiedOn);
  }

  @Override
  public int hashCode() {

    return Objects.hash(
        id,
        status,
        failure,
        failureDetail,
        createdOn,
        webUrl,
        durationSeconds,
        name,
        callbackUrl,
        metadata,
        lastModifiedOn);
  }
}
