package callback.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TranscriptCallback {

  @JsonProperty("transcript")
  Transcript transcript;

  public Transcript getTranscript() {
    return transcript;
  }

  public void setTranscript(Transcript transcript) {
    this.transcript = transcript;
  }

  @Override
  public String toString() {
    return "TranscriptCallback{" + "transcript=" + transcript + '}';
  }
}
