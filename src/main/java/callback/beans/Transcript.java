package callback.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Transcript {

  @JsonProperty("monologues")
  private Monologue[] monologues;

  @JsonProperty("metadata")
  private String metadata;

  public Monologue[] getMonologues() {
    return monologues;
  }

  public void setMonologues(Monologue[] monologues) {
    this.monologues = monologues;
  }

  public String getMetadata() {
    return metadata;
  }

  public void setMetadata(String metadata) {
    this.metadata = metadata;
  }
}
