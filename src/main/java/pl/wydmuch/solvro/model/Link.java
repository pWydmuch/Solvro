package pl.wydmuch.solvro.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Link {

    @JsonProperty("source")
    private Long sourceId;
    @JsonProperty("target")
    private Long targetId;
    private int distance;

    public Long getSourceId() {
        return sourceId;
    }

    public Long getTargetId() {
        return targetId;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return "LinkDto{" +
                "sourceId=" + sourceId +
                ", targetId=" + targetId +
                ", distance=" + distance +
                '}';
    }
}
