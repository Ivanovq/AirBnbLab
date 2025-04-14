package mk.ukim.finki.airbnblab.service.domain;

import mk.ukim.finki.airbnblab.model.Enumerations.Role;
import mk.ukim.finki.airbnblab.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User register(String username, String password, String repeatPassword, String name, String surname, Role userRole);

    User login(String username, String password);

    User findByUsername(String username);

}
