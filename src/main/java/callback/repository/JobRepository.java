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

  @Query("FROM Job WHERE order_number = :orderNumber")
  List<Job> findByOrderNumber(String orderNumber);

  @Query("FROM Job WHERE status = :status")
  List<Job> findByJobStatus(JobStatus status);

  @Query("SELECT COUNT(jobType) FROM Job")
  Integer countJobs();

  @Query("FROM Job WHERE job_type = :jobType")
  List<Job> findByJobType(String jobType);

  @Query("FROM Job WHERE job_type = :jobType AND status = :status")
  List<Job> findByJobTypeAndStatus(String jobType, JobStatus status);
}
