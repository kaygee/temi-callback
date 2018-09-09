package callback.repository;

import callback.beans.Job;
import callback.beans.JobStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

  // This is HQL NOT SQL.
  @Query("FROM Job WHERE id = :jobId")
  List<Job> findByJobId(String jobId);

  // This is HQL NOT SQL.
  @Query("FROM Job WHERE status = :status")
  List<Job> findByJobStatus(JobStatus status);
}
