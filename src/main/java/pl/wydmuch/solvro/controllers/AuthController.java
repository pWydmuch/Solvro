package pl.wydmuch.solvro.controllers;

import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.wydmuch.solvro.configuration.JwtTokenUtil;
import pl.wydmuch.solvro.exceptions.UserAlreadyExistsException;
import pl.wydmuch.solvro.dto.UserDto;
import pl.wydmuch.solvro.exceptions.UserNotFoundException;
import pl.wydmuch.solvro.services.UserService;

import javax.validation.Valid;
import javax.xml.ws.Response;

//TODO Security z podziałem na role


@RestController
public class AuthController {

    private UserService userService;
    private AuthenticationManager authenticationManager;
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    public AuthController(UserService userService, AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/registration")
    public ResponseEntity<?> register(@RequestBody
                                            @ApiParam(required = true, value = "JSON with username password and email")
                                            @Valid UserDto user){
        System.out.println(user);
        try {
            userService.registerNewUser(user);
        } catch (UserAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto user){
        try {
            return new ResponseEntity<>(userService.loginUser(user),HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.UNAUTHORIZED);
        }
    }
}
