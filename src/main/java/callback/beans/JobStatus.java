package callback.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.NonNull;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public enum JobStatus {

    // @formatter:off

    @JsonProperty("transcribed")
    TRANSCRIBED("transcribed"),

    @JsonProperty("inProgress")
    IN_PROGRESS("inProgress");

    // @formatter:on

    private final String val;

    JobStatus(String val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return val;
    }

}
