package pl.wydmuch.solvro.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PathDto {
    @JsonProperty("stops")
    List<StopDto> stopDtos;
    int distance;

    public PathDto(List<StopDto> stopDtos, int distance) {
        this.stopDtos = stopDtos;
        this.distance = distance;
    }

    public List<StopDto> getStopDtos() {
        return stopDtos;
    }

    public void setStopDtos(List<StopDto> stopDtos) {
        this.stopDtos = stopDtos;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "PathDto{" +
                "stopDtos=" + stopDtos +
                ", distance=" + distance +
                '}';
    }
}
