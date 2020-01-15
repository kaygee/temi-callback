package callback.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Transcript {

  @JsonProperty("monologues")
  private Monologue[] monologues;

  public Monologue[] getMonologues() {
    return monologues;
  }

  public void setMonologues(Monologue[] monologues) {
    this.monologues = monologues;
  }
}
