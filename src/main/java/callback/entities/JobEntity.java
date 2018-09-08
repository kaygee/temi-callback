package callback.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "job_callbacks", schema = "callback")
public class JobEntity {

  @Id
  @GeneratedValue
  @Column(name = "id")
  private Long id;

  @Column(name = "jobId")
  private Long jobId;

  public JobEntity() {}

  public JobEntity(Long id, Long jobId) {
    super();
    this.id = id;
    this.jobId = jobId;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getJobId() {
    return jobId;
  }

  public void setJobId(Long jobId) {
    this.jobId = jobId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    JobEntity that = (JobEntity) o;
    return Objects.equals(id, that.id) && Objects.equals(jobId, that.jobId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, jobId);
  }
}
