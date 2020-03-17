package callback.repository;

import callback.beans.BillingRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingRepository extends JpaRepository<BillingRequest, Long> {}
