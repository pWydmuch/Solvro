package pl.wydmuch.solvro.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Link {

    @JsonProperty("source")
    Long sourceId;
    @JsonProperty("target")
    Long targetId;
    int distance;

    public Long getSourceId() {
        return sourceId;
    }

    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
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
