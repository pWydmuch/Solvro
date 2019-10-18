package pl.wydmuch.solvro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.wydmuch.solvro.dto.UserDto;
import pl.wydmuch.solvro.model.User;
import pl.wydmuch.solvro.services.UserService;

import javax.validation.Valid;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public ResponseEntity<UserDto> register(@RequestBody @Valid UserDto user){
        System.out.println(user);
        userService.registerNewUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

//    @PostMapping("/login2")
//    public UserDto login(@RequestBody UserDto user){
//
//        return new User();
//    }
}
