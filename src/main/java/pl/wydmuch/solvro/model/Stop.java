package pl.wydmuch.solvro.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Stop {

    Long id;

    @JsonProperty("stop_name")
    String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Stop{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
