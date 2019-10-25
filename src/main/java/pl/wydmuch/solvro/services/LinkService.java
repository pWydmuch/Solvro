package pl.wydmuch.solvro.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wydmuch.solvro.dto.PathDto;
import pl.wydmuch.solvro.dto.StopDto;
import pl.wydmuch.solvro.model.Link;
import pl.wydmuch.solvro.model.Stop;
import pl.wydmuch.solvro.repositories.LinkRepository;

import java.io.IOException;
import java.util.List;

@Service
public class LinkService {

    LinkRepository linkRepository;

    StopService stopService;

    @Autowired
    public LinkService(LinkRepository linkRepository, StopService stopService) {
        this.linkRepository = linkRepository;
        this.stopService = stopService;
    }

    public PathDto getShortestPath(String sourceName, String targetName) throws IOException {
        List<Link> links = linkRepository.findAll();
        List<Stop> stops = stopService.findAllStops();
        Stop source = stopService.findStopByName(sourceName);//Drugi raz zaciagam to samo z bazy
        Stop target = stopService.findStopByName(targetName);

        return new PathDto();
    }

}
