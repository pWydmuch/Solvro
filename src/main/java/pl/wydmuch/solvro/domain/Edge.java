package pl.wydmuch.solvro.domain;


/**
 * @param firstVertex private final String id;
 */
public record Edge(Vertex firstVertex, Vertex secondVertex, int weight) {
//        this.id = id;

    @Override
    public String toString() {
        return firstVertex + " " + secondVertex;
    }


}


