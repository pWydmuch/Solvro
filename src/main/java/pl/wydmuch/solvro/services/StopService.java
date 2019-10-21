package pl.wydmuch.solvro.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.stereotype.Service;
import pl.wydmuch.solvro.dto.StopDto;
import pl.wydmuch.solvro.repositories.StopRepository;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StopService {

    StopRepository stopRepository;

    @Autowired
    public StopService(StopRepository stopRepository) {
        this.stopRepository = stopRepository;
    }

    public List<StopDto> findAllStops() throws IOException {
        return stopRepository.findAll().stream()
                .map(StopDto::new)
                .collect(Collectors.toList());
    }
}
