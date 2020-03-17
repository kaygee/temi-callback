package callback.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TranscriptCallback {

  @JsonProperty("transcript")
  String transcript;

  @JsonProperty("metadata")
  String metadata;

  public String getMetadata() {
    return metadata;
  }

  public void setMetadata(String metadata) {
    this.metadata = metadata;
  }

  public String getTranscript() {
    return transcript;
  }

  public void setTranscript(String transcript) {
    this.transcript = transcript;
  }

  @Override
  public String toString() {
    return "TranscriptCallback{" + "transcript=" + transcript + '}';
  }
}
