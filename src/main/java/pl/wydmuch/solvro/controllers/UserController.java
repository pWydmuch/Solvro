package pl.wydmuch.solvro.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.wydmuch.solvro.model.User;

@RestController
public class UserController {

    @Value("${spring.message}")
    private String message;

    @GetMapping("/dupa")
    public String getMessage(){
        return message;
    }

    @PostMapping("/registration")
    public User register(@RequestBody User user){
        return new User();
    }

    @PostMapping("/login")
    public User login(@RequestBody User user){
        return new User();
    }
}
