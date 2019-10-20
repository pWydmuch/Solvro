package pl.wydmuch.solvro.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.wydmuch.solvro.exceptions.UserAlreadyExistsException;
import pl.wydmuch.solvro.dto.UserDto;
import pl.wydmuch.solvro.exceptions.UserNotFoundException;
import pl.wydmuch.solvro.model.User;
import pl.wydmuch.solvro.repositories.UserRepository;

@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    //TODO check if the email already exists
    public User registerNewUser(UserDto userDto) throws UserAlreadyExistsException {
        if(!userRepository.existsByEmail(userDto.getEmail())) {
            User user = new User();
//        user.setUsername(userDto.getUserName());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.setEmail(userDto.getEmail());
            return userRepository.save(user);
        }else{
            throw new UserAlreadyExistsException("User having this email address already exists");
        }

    }

    public String loginUser(UserDto user) throws UserNotFoundException {
        User userDB = userRepository.findByEmail(user.getEmail())
                .orElseThrow(()->new UserNotFoundException("User with given email not found"));
        boolean isPasswordCorrect = passwordEncoder.matches(user.getPassword(),userDB.getPassword());
        if(isPasswordCorrect) return "Logged";
        else return "Failed";
    }
}
