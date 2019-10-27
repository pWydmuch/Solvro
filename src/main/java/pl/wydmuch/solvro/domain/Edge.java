package pl.wydmuch.solvro.domain;



public class Edge  {
//    private final String id;
    private final Vertex firstVertex;
    private final Vertex secondVertex;
    private final int weight;

    public Edge(Vertex firstVertex, Vertex secondVertex, int weight) {
//        this.id = id;
        this.firstVertex = firstVertex;
        this.secondVertex = secondVertex;
        this.weight = weight;
    }

//    public String getId() {
//        return id;
//    }
    public Vertex getSecondVertex() {
        return secondVertex;
    }

    public Vertex getFirstVertex() {
        return firstVertex;
    }
    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return firstVertex + " " + secondVertex;
    }


}


