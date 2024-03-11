package pl.wydmuch.solvro.domain;


public record Vertex(String id, String name) {

    @Override
    public String toString() {
        return name;
    }
}
