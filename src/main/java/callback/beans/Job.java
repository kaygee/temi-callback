package callback.beans;

import callback.deserializer.MultiDateDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Date;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Job {

    @JsonProperty("id")
    public String id;

    @JsonProperty("status")
    public JobStatus status;

    @JsonProperty("failure")
    public String failure;

    @JsonProperty("failure_detail")
    public String failureDetail;

    @JsonDeserialize(using = MultiDateDeserializer.class)
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

    @JsonProperty("last_modified_on")
    public String lastModifiedOn;

    @Override
    public String toString() {
        return "Job{" +
                "id='" + id + '\'' +
                ", status=" + status +
                ", failure='" + failure + '\'' +
                ", failureDetail='" + failureDetail + '\'' +
                ", createdOn=" + createdOn +
                ", webUrl='" + webUrl + '\'' +
                ", durationSeconds=" + durationSeconds +
                ", name='" + name + '\'' +
                ", callbackUrl='" + callbackUrl + '\'' +
                ", metadata='" + metadata + '\'' +
                ", lastModifiedOn='" + lastModifiedOn + '\'' +
                '}';
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
