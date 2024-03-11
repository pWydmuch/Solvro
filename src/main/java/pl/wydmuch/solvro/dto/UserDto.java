package pl.wydmuch.solvro.dto;

import pl.wydmuch.solvro.validators.ValidEmail;
import pl.wydmuch.solvro.validators.ValidPassword;

public class UserDto {

    @ValidEmail
    private String email;

    @ValidPassword
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
