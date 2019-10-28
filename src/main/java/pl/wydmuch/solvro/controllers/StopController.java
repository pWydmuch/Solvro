package pl.wydmuch.solvro.controllers;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.wydmuch.solvro.dto.PathDto;
import pl.wydmuch.solvro.dto.StopDto;
import pl.wydmuch.solvro.services.LinkService;
import pl.wydmuch.solvro.services.StopService;
import java.io.IOException;
import java.util.List;

@CrossOrigin("${allowedOrigin:*}")
@RestController
public class StopController {

    StopService stopService;

    LinkService linkService;

    @Autowired
    public StopController(StopService stopService, LinkService linkService) {
        this.stopService = stopService;
        this.linkService = linkService;
    }

    @ApiOperation(value = "Get all stops available",
                 response = StopDto.class,
                 responseContainer = "List" )
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header",  example = "Bearer access_token")
    @GetMapping("/stops")
    public ResponseEntity<?> getStops() throws IOException {

        List<StopDto> stops = stopService.findAllStopsDto();
        return new ResponseEntity<>(stops, HttpStatus.OK);

    }
    @ApiOperation(value = "Find shortest path",
                  response = PathDto.class )
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header",  example = "Bearer access_token")
    @GetMapping("/path")
    public ResponseEntity<?> findPath(@RequestParam("source") @ApiParam(defaultValue = "Przystanek Solvrowy student PWr") String source,
                                      @RequestParam("target") @ApiParam(defaultValue = "Przystanek Dziki javascriptowiec") String target) throws IOException {

        PathDto shortestPath = linkService.getShortestPath(source, target);
        return new ResponseEntity<>(shortestPath, HttpStatus.OK);
    }
}