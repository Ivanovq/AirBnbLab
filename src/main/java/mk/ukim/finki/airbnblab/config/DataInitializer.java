package mk.ukim.finki.airbnblab.config;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.airbnblab.model.Country;
import mk.ukim.finki.airbnblab.model.Enumerations.Role;
import mk.ukim.finki.airbnblab.model.Host;
import mk.ukim.finki.airbnblab.model.User;
import mk.ukim.finki.airbnblab.repository.CountryRepository;
import mk.ukim.finki.airbnblab.repository.HostRepository;
import mk.ukim.finki.airbnblab.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final HostRepository hostRepository;
    private final CountryRepository countryRepository;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(HostRepository hostRepository, CountryRepository countryRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.hostRepository = hostRepository;
        this.countryRepository = countryRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @PostConstruct
    public void init() {
        Country c=new Country("Macedonia", "Evropa");
        countryRepository.save(c);
        hostRepository.save(new Host("Katerina","Ivanova",c));

    userRepository.save(new User("ki",passwordEncoder.encode("ki"),"Katerina","Ivanova", Role.ROLE_ADMIN));

    }

}
