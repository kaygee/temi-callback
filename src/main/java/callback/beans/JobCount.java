package callback.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JobCount {

  @JsonProperty("count")
  private Integer count;

  public JobCount(Integer count) {
    this.count = count;
  }

  public JobCount() {}

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }
}
