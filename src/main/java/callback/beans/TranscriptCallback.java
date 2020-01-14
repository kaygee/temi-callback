package callback.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TranscriptCallback {

  @JsonProperty("transcript")
  Transcript transcript;

  @JsonProperty("metadata")
  String metadata;

  public String getMetadata() {
    return metadata;
  }

  public void setMetadata(String metadata) {
    this.metadata = metadata;
  }

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
