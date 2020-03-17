package callback.beans;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TranscriptCallback {

  @JsonAlias({"transcript", "transcript_text", "captions_srt", "captions_vtt"})
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
