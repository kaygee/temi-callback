package callback.repository;

import callback.beans.CookiesForRoleAndEnvironment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CookiesRepository extends JpaRepository<CookiesForRoleAndEnvironment, Long> {

  // This is HQL NOT SQL.
  @Query("FROM CookiesForRoleAndEnvironment WHERE role = :role AND environment = :environment")
  Optional<CookiesForRoleAndEnvironment> findCookies(String role, String environment);

  @Query("FROM CookiesForRoleAndEnvironment WHERE current_date() > expires_on")
  List<CookiesForRoleAndEnvironment> findStaleCookies();
}
