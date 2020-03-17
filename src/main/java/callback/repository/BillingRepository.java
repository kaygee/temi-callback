package callback.repository;

import callback.beans.BillingTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingRepository extends JpaRepository<BillingTransaction, Long> {}
