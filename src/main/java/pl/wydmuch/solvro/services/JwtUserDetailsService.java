package pl.wydmuch.solvro.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.wydmuch.solvro.exceptions.MyAuthenticationException;
import pl.wydmuch.solvro.exceptions.UserAlreadyExistsException;
import pl.wydmuch.solvro.dto.UserDto;
import pl.wydmuch.solvro.model.User;
import pl.wydmuch.solvro.repositories.UserRepository;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public JwtUserDetailsService(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder, @Lazy AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User userDB = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with given email not found"));
        return new org.springframework.security.core.userdetails.User(userDB.getEmail(), userDB.getPassword(),
                new ArrayList<>());
    }

    public User registerNewUser(UserDto userDto) throws UserAlreadyExistsException {
        if (!userRepository.existsByEmail(userDto.getEmail())) {
            User user = new User();
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.setEmail(userDto.getEmail());
            return userRepository.save(user);
        } else {
            throw new UserAlreadyExistsException("User having this email address already exists");
        }

    }


    public void authenticate(String username, String password) throws MyAuthenticationException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new MyAuthenticationException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new MyAuthenticationException("INVALID_CREDENTIALS", e);
        }
    }


}

