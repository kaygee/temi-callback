package callback.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.Objects;

public class Monologue {

  @JsonProperty("id")
  private String id;

  @JsonProperty("speaker")
  private Integer speaker;

  @JsonProperty("elements")
  private Element[] elements;

  public void setId(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public Integer getSpeaker() {
    return speaker;
  }

  public void setSpeaker(Integer speaker) {
    this.speaker = speaker;
  }

  public Element[] getElements() {
    return elements;
  }

  public void setElements(Element[] elements) {
    this.elements = elements;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Monologue monologue = (Monologue) o;
    return Objects.equals(id, monologue.id)
        && Objects.equals(speaker, monologue.speaker)
        && Arrays.equals(elements, monologue.elements);
  }

  @Override
  public int hashCode() {

    int result = Objects.hash(id, speaker);
    result = 31 * result + Arrays.hashCode(elements);
    return result;
  }

  @Override
  public String toString() {
    return "Monologue{"
        + "id='"
        + id
        + '\''
        + ", speaker="
        + speaker
        + ", elements="
        + Arrays.toString(elements)
        + '}';
  }
}
