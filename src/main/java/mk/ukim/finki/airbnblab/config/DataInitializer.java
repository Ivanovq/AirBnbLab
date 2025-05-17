package mk.ukim.finki.airbnblab.config;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.airbnblab.model.Accommodation;
import mk.ukim.finki.airbnblab.model.Country;
import mk.ukim.finki.airbnblab.model.Enumerations.Category;
import mk.ukim.finki.airbnblab.model.Enumerations.Role;
import mk.ukim.finki.airbnblab.model.Host;
import mk.ukim.finki.airbnblab.model.User;
import mk.ukim.finki.airbnblab.repository.AccommodationRepository;
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
    private final AccommodationRepository accommodationRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(HostRepository hostRepository, CountryRepository countryRepository, UserRepository userRepository, AccommodationRepository accommodationRepository, PasswordEncoder passwordEncoder) {
        this.hostRepository = hostRepository;
        this.countryRepository = countryRepository;
        this.userRepository = userRepository;
        this.accommodationRepository = accommodationRepository;
        this.passwordEncoder = passwordEncoder;
    }

//    @PostConstruct
    public void init() {
        Country c = new Country("Macedonia", "Evropa");
        countryRepository.save(c);
        Host h = new Host("Katerina", "Ivanova", c);
        hostRepository.save(h);
        Accommodation a = new Accommodation("sirius", Category.HOTEL, h, 10);
        accommodationRepository.save(a);
        userRepository.save(new User("ki", passwordEncoder.encode("ki"), "Katerina", "Ivanova", Role.ROLE_ADMIN));

    }

}
