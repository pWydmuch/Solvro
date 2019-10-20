package pl.wydmuch.solvro.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.wydmuch.solvro.dto.StopDto;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StopController {

    @GetMapping("/stops")
    public ResponseEntity<List<StopDto>> getStops(){
        List<StopDto> stops = new ArrayList<>();
        return new ResponseEntity<>(stops, HttpStatus.OK);
    }

    @GetMapping("/path")
    public ResponseEntity<List<StopDto>> findPath(@RequestParam("source") String source,
                                                  @RequestParam("target") String target){
        List<StopDto> stops = new ArrayList<>();
        return new ResponseEntity<>(stops, HttpStatus.OK);
    }
}