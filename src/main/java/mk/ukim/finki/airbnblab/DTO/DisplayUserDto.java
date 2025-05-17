package mk.ukim.finki.airbnblab.DTO;

import mk.ukim.finki.airbnblab.model.Enumerations.Role;
import mk.ukim.finki.airbnblab.model.User;

public record DisplayUserDto(String username, String name, String surname, Role role) {
    public static DisplayUserDto from(User user) {
        return new DisplayUserDto(
                user.getUsername(),
                user.getName(),
                user.getSurname(),
                user.getRole()
        );
    }

}