package pl.wydmuch.solvro.domain;

import java.util.*;


public class DijkstraAlgorithmImpl implements DijkstraAlgorithm {

    private final List<Vertex> nodes;
    private final List<Edge> edges;
    private Set<Vertex> settledNodes;
    private Set<Vertex> unSettledNodes;
    private Map<Vertex, Vertex> predecessors;
    private Map<Vertex, Integer> distance;

    public DijkstraAlgorithmImpl(Graph graph) {
        this.nodes = new ArrayList<>(graph.vertexes());
        this.edges = new ArrayList<>(graph.edges());
    }

    @Override
    public LinkedList<Vertex> getPath(Vertex target) {
        LinkedList<Vertex> path = new LinkedList<>();
        System.out.println(distance.get(target));
        Vertex step = target;
        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        Collections.reverse(path);
        return path;
    }

    @Override
    public int getDistance(Vertex target) {
        return distance.get(target);
    }

    @Override
    public void execute(Vertex source) {
        settledNodes = new HashSet<>();
        unSettledNodes = new HashSet<>();
        distance = new HashMap<>();
        predecessors = new HashMap<>();
        distance.put(source, 0);
        unSettledNodes.add(source);
        while (!unSettledNodes.isEmpty()) {
            Vertex node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinimalDistances(node);
        }
    }

    private Vertex getMinimum(Set<Vertex> vertexes) {
        Vertex minimum = null;
        for (Vertex vertex : vertexes) {
            if (minimum == null) {
                minimum = vertex;
            } else {
                if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
                    minimum = vertex;
                }
            }
        }
        return minimum;
    }

    private int getShortestDistance(Vertex destination) {
        Integer d = distance.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

    private void findMinimalDistances(Vertex node) {
        List<Vertex> adjacentNodes = getNeighbors(node);
        for (Vertex target : adjacentNodes) {
            if (getShortestDistance(target) > getShortestDistance(node)
                    + getDistance(node, target)) {
                distance.put(target, getShortestDistance(node)
                        + getDistance(node, target));
                predecessors.put(target, node);
                unSettledNodes.add(target);
            }
        }

    }

    private List<Vertex> getNeighbors(Vertex node) {
        List<Vertex> neighbors = new ArrayList<>();
        for (Edge edge : edges) {
            Vertex first = edge.firstVertex();
            Vertex second = edge.secondVertex();
            if (first.equals(node)
                    && isNotSettled(second)) {
                neighbors.add(second);
            }
            if (second.equals(node)
                    && isNotSettled(first)) {
                neighbors.add(first);
            }
        }
        return neighbors;
    }

    private int getDistance(Vertex node, Vertex target) {
        for (Edge edge : edges) {
            if (edge.firstVertex().equals(node)
                    && edge.secondVertex().equals(target)) {
                return edge.weight();
            }
            if (edge.secondVertex().equals(node)
                    && edge.firstVertex().equals(target)) {
                return edge.weight();
            }
        }
        throw new RuntimeException("Should not happen");
    }


    private boolean isNotSettled(Vertex vertex) {
        return !settledNodes.contains(vertex);
    }


}
