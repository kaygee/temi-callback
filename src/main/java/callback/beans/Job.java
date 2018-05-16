package callback.beans;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Job {

    @JsonProperty("id")
    public String id;

    @JsonProperty("status")
    public JobStatus status;

    @JsonProperty("failure_reason")
    public String failureReason;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    @JsonProperty("created_on")
    public Date createdOn;

    @JsonProperty("web_url")
    public String webUrl;

    @JsonProperty("duration_seconds")
    public Long durationSeconds;

    @JsonProperty("name")
    public String name;

    @JsonProperty("callback_url")
    public String callbackUrl;

    @JsonProperty("metadata")
    public String metadata;

    @JsonProperty("last_modified")
    public String lastModified;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Job job = (Job) o;
        return Objects.equals(id, job.id) && Objects.equals(status, job.status) && Objects.equals(failureReason, job
                .failureReason) && Objects.equals(createdOn, job.createdOn) && Objects.equals(webUrl, job.webUrl) &&
                Objects.equals(durationSeconds, job.durationSeconds) && Objects.equals(name, job.name) && Objects
                .equals(callbackUrl, job.callbackUrl) && Objects.equals(metadata, job.metadata) && Objects.equals
                (lastModified, job.lastModified);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, failureReason, createdOn, webUrl, durationSeconds, name, callbackUrl,
                metadata, lastModified);
    }

    @Override
    public String toString() {
        return "Job{" + "id='" + id + '\'' + ", status=" + status + ", failureReason='" + failureReason + '\'' + ", " +
                "createdOn=" + createdOn + ", webUrl='" + webUrl + '\'' + ", durationSeconds=" + durationSeconds + "," +
                " name='" + name + '\'' + ", callbackUrl='" + callbackUrl + '\'' + ", metadata='" + metadata + '\'' +
                ", lastModified='" + lastModified + '\'' + '}';
    }
}
