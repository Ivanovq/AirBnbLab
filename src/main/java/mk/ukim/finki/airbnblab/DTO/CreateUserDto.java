package mk.ukim.finki.airbnblab.DTO;

import mk.ukim.finki.airbnblab.model.Enumerations.Role;
import mk.ukim.finki.airbnblab.model.User;

public record CreateUserDto(String username, String password, String repeatPassword, String name, String surname, Role role) {
    public User toUser() {
        return new User(username, password, name, surname, role);
    }
}