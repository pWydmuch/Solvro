package pl.wydmuch.solvro.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import pl.wydmuch.solvro.model.Stop;


public class StopDto {

//    @JsonProperty("stop_name")
    @ApiModelProperty("Name of a stop")
    private String name;

    public StopDto() {
    }

    public StopDto(Stop stop){
        this.name = stop.getName();
    }

    public StopDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "StopDto{" +
                "name='" + name + '\'' +
                '}';
    }
}
