package mk.ukim.finki.airbnblab.config;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.airbnblab.model.Country;
import mk.ukim.finki.airbnblab.model.Host;
import mk.ukim.finki.airbnblab.repository.CountryRepository;
import mk.ukim.finki.airbnblab.repository.HostRepository;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final HostRepository hostRepository;
    private final CountryRepository countryRepository;

    public DataInitializer(HostRepository hostRepository, CountryRepository countryRepository) {
        this.hostRepository = hostRepository;
        this.countryRepository = countryRepository;
    }
    @PostConstruct
    public void init() {
        Country c=new Country("Macedonia", "Evropa");
        countryRepository.save(c);
        hostRepository.save(new Host("Katerina","Ivanova",c));



    }

}
