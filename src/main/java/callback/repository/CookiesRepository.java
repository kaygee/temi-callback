package callback.repository;

import callback.beans.Cookies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CookiesRepository extends JpaRepository<Cookies, Long> {

  // This is HQL NOT SQL.
  @Query("FROM Cookies WHERE role = :role AND environment = :environment")
  Optional<Cookies> findCookies(String role, String environment);
}
