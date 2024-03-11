package pl.wydmuch.solvro.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wydmuch.solvro.domain.*;
import pl.wydmuch.solvro.dto.PathDto;
import pl.wydmuch.solvro.dto.StopDto;
import pl.wydmuch.solvro.model.Link;
import pl.wydmuch.solvro.model.Stop;
import pl.wydmuch.solvro.repositories.LinkRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LinkService {

    private final LinkRepository linkRepository;

    private final StopService stopService;

    public LinkService(LinkRepository linkRepository, StopService stopService) {
        this.linkRepository = linkRepository;
        this.stopService = stopService;
    }

    public PathDto getShortestPath(String sourceName, String targetName) throws IOException {

        List<Link> links = linkRepository.findAll();
        List<Stop> stops = stopService.findAllStops();


        List<Vertex> vertices = stops.stream()
                .map(LinkService::changeStopToVertex)
                .collect(Collectors.toList());

        List<Edge> edges = new ArrayList<>();
        for (Link link : links) {
            Vertex linkSource = vertices.stream().filter(vertex -> vertex.id().equals(String.valueOf(link.getSourceId()))).findFirst().get();
            Vertex linkTarget = vertices.stream().filter(vertex -> vertex.id().equals(String.valueOf(link.getTargetId()))).findFirst().get();
            int distance = link.getDistance();
            edges.add(new Edge(linkSource, linkTarget, distance));
        }

        Vertex source = vertices.stream()
                .filter(vertex -> vertex.name().equals(sourceName))
                .findFirst()
                .get();//Drugi raz zaciagam to samo z bazy

        Vertex target = vertices.stream()
                .filter(vertex -> vertex.name().equals(targetName))
                .findFirst()
                .get();
        Graph graph = new Graph(vertices, edges);
        DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithmImpl(graph);
        dijkstraAlgorithm.execute(source);
        List<Vertex> vertexPath = dijkstraAlgorithm.getPath(target);
        List<StopDto> stopDtos = vertexPath.stream()
                .map(Vertex::name)
                .map(StopDto::new)
                .collect(Collectors.toList());
        int distance = dijkstraAlgorithm.getDistance(target);

        return new PathDto(stopDtos, distance);
    }


    private static Vertex changeStopToVertex(Stop stop) {
        return new Vertex(String.valueOf(stop.getId()), stop.getName());
    }


}
