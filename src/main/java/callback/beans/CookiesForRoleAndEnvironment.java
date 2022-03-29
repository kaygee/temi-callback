package callback.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "cookies")
@EntityListeners(AuditingEntityListener.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CookiesForRoleAndEnvironment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonIgnore
  private Long databaseId;

  @ElementCollection
  @Column(length = 10000)
  @JsonProperty("cookies")
  private Set<RevCookie> revCookies;

  @Column(name = "created_on", nullable = false)
  @LastModifiedDate
  @Temporal(TemporalType.TIMESTAMP)
  @JsonIgnore
  private Date createdOn;

  @Column(name = "remember_me", nullable = false)
  @JsonProperty("remember_me")
  private boolean rememberMe;

  @Column(name = "role")
  @JsonProperty("role")
  private String role;

  @Column(name = "user_name")
  @JsonProperty("user_name")
  private String username;

  @Column(name = "environment")
  @JsonProperty("environment")
  private String environment;

  public boolean getRememberMe() {
    return rememberMe;
  }

  public void setRememberMe(boolean rememberMe) {
    this.rememberMe = rememberMe;
  }

  public Set<RevCookie> getCookies() {
    return revCookies;
  }

  public void setCookies(Set<RevCookie> revCookies) {
    this.revCookies = revCookies;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public Long getDatabaseId() {
    return databaseId;
  }

  public void setDatabaseId(Long databaseId) {
    this.databaseId = databaseId;
  }

  public Date getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(Date createdOn) {
    this.createdOn = createdOn;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEnvironment() {
    return environment;
  }

  public void setEnvironment(String environment) {
    this.environment = environment;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CookiesForRoleAndEnvironment that = (CookiesForRoleAndEnvironment) o;
    return Objects.equals(getDatabaseId(), that.getDatabaseId())
        && Objects.equals(revCookies, that.revCookies)
        && Objects.equals(getCreatedOn(), that.getCreatedOn())
        && Objects.equals(getRememberMe(), that.getRememberMe())
        && Objects.equals(getRole(), that.getRole())
        && Objects.equals(getUsername(), that.getUsername())
        && Objects.equals(getEnvironment(), that.getEnvironment());
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        getDatabaseId(),
        revCookies,
        getCreatedOn(),
        getRememberMe(),
        getRole(),
        getUsername(),
        getEnvironment());
  }
}
