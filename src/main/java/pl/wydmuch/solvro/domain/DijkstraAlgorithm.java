package pl.wydmuch.solvro.domain;

import java.util.LinkedList;

public interface DijkstraAlgorithm {

    void execute(Vertex source);

    LinkedList<Vertex> getPath(Vertex target);

    int getDistance(Vertex target);
}
