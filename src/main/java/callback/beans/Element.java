package callback.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Element {

  @JsonProperty("type")
  private ElementType type;

  @JsonProperty("value")
  private String value;

  @JsonProperty("ts")
  private Double ts;

  @JsonProperty("end_ts")
  private Double endTs;

  @JsonProperty("confidence")
  private Double confidence;

  public ElementType getType() {
    return type;
  }

  public void setType(ElementType type) {
    this.type = type;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public Double getTs() {
    return ts;
  }

  public void setTs(Double ts) {
    this.ts = ts;
  }

  public Double getEndTs() {
    return endTs;
  }

  public void setEndTs(Double endTs) {
    this.endTs = endTs;
  }

  public Double getConfidence() {
    return confidence;
  }

  public void setConfidence(Double confidence) {
    this.confidence = confidence;
  }

  @Override
  public String toString() {
    return "Element{"
        + "type="
        + type
        + ", value='"
        + value
        + '\''
        + ", ts="
        + ts
        + ", endTs="
        + endTs
        + ", confidence="
        + confidence
        + '}';
  }
}
