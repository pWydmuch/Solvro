package pl.wydmuch.solvro.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel("Path showing shortest way from source to target")
public class PathDto {

    @JsonProperty("stops")
    @ApiModelProperty("Consecutive stops")
    List<StopDto> stopDtos;

    @ApiModelProperty("Shortest distance between source and target")
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
