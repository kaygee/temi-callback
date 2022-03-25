package callback.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "cookies")
@EntityListeners(AuditingEntityListener.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(
    value = {"received_at"},
    allowGetters = true)
public class Cookies {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long databaseId;

  @Column(name = "raw_data", columnDefinition = "CLOB NOT NULL")
  @Lob
  private String rawData;

  @Column(name = "created_on", nullable = false)
  @LastModifiedDate
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdOn;

  @Column(name = "role")
  @JsonProperty("role")
  private String role;

  @Column(name = "user_name")
  @JsonProperty("user_name")
  private String username;

  @Column(name = "environment")
  @JsonProperty("environment")
  private String environment;

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

  public String getRawData() {
    return rawData;
  }

  public void setRawData(String rawData) {
    this.rawData = rawData;
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
    Cookies cookies = (Cookies) o;
    return Objects.equals(getDatabaseId(), cookies.getDatabaseId())
        && Objects.equals(getRawData(), cookies.getRawData())
        && Objects.equals(getCreatedOn(), cookies.getCreatedOn())
        && Objects.equals(getRole(), cookies.getRole())
        && Objects.equals(getUsername(), cookies.getUsername())
        && Objects.equals(getEnvironment(), cookies.getEnvironment());
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        getDatabaseId(), getRawData(), getCreatedOn(), getRole(), getUsername(), getEnvironment());
  }

  @Override
  public String toString() {
    return "Cookies{"
        + "databaseId="
        + databaseId
        + ", rawData='"
        + rawData
        + '\''
        + ", createdOn="
        + createdOn
        + ", role='"
        + role
        + '\''
        + ", username='"
        + username
        + '\''
        + ", environment='"
        + environment
        + '\''
        + '}';
  }
}
