package callback.beans;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Cookie implements Serializable {

  // TODO: Generate a real one.
  private static final long serialVersionUID = 1L;

  @JsonProperty("name")
  private final String name;

  @JsonProperty("value")
  private final String value;

  @JsonProperty("path")
  private final String path;

  @JsonProperty("domain")
  private final String domain;

  @JsonProperty("expiry")
  private final Date expiry;

  @JsonProperty("isSecure")
  private final boolean isSecure;

  @JsonProperty("isHttpOnly")
  private final boolean isHttpOnly;

  @JsonProperty("sameSite")
  private final String sameSite;

  /**
   * Creates a cookie.
   *
   * @param name The name of the cookie; may not be null or an empty string.
   * @param value The cookie value; may not be null.
   * @param domain The domain the cookie is visible to.
   * @param path The path the cookie is visible to. If left blank or set to null, will be set to
   *     "/".
   * @param expiry The cookie's expiration date; may be null.
   * @param isSecure Whether this cookie requires a secure connection.
   * @param isHttpOnly Whether this cookie is a httpOnly cookie.
   * @param sameSite The samesite attribute of this cookie; e.g. None, Lax, Strict.
   */
  @JsonCreator
  public Cookie(
      @JsonProperty("name") String name,
      @JsonProperty("value") String value,
      @JsonProperty("domain") String domain,
      @JsonProperty("path") String path,
      @JsonProperty("expiry") Date expiry,
      @JsonProperty("isSecure") boolean isSecure,
      @JsonProperty("isHttpOnly") boolean isHttpOnly,
      @JsonProperty("sameSite") String sameSite) {
    this.name = name;
    this.value = value;
    this.path = path == null || "".equals(path) ? "/" : path;

    this.domain = stripPort(domain);
    this.isSecure = isSecure;
    this.isHttpOnly = isHttpOnly;

    if (expiry != null) {
      // Expiration date is specified in seconds since (UTC) epoch time, so truncate the date.
      this.expiry = new Date(expiry.getTime() / 1000 * 1000);
    } else {
      this.expiry = null;
    }

    this.sameSite = sameSite;
  }

  private static String stripPort(String domain) {
    return (domain == null) ? null : domain.split(":")[0];
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Cookie cookie = (Cookie) o;
    return isSecure == cookie.isSecure
        && isHttpOnly == cookie.isHttpOnly
        && Objects.equals(name, cookie.name)
        && Objects.equals(value, cookie.value)
        && Objects.equals(path, cookie.path)
        && Objects.equals(domain, cookie.domain)
        && Objects.equals(expiry, cookie.expiry)
        && Objects.equals(sameSite, cookie.sameSite);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, value, path, domain, expiry, isSecure, isHttpOnly, sameSite);
  }
}
