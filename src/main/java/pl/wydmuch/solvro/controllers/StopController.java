package pl.wydmuch.solvro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.wydmuch.solvro.dto.StopDto;
import pl.wydmuch.solvro.services.StopService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class StopController {

    StopService stopService;

    @Autowired
    public StopController(StopService stopService) {
        this.stopService = stopService;
    }

    @GetMapping("/stops")
    public ResponseEntity<?> getStops(){
        List<StopDto> stops = null;
        try {
            stops = stopService.findAllStops();
            return new ResponseEntity<>(stops, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/path")
    public ResponseEntity<List<StopDto>> findPath(@RequestParam("source") String source,
                                                  @RequestParam("target") String target){
        List<StopDto> stops = new ArrayList<>();
        return new ResponseEntity<>(stops, HttpStatus.OK);
    }
}