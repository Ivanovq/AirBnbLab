package mk.ukim.finki.airbnblab.DTO;

import mk.ukim.finki.airbnblab.model.Country;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayCountryDTO(Long id, String name, String continent
) {

    public static DisplayCountryDTO from (Country country)
    {
        return new DisplayCountryDTO(
                country.getId(),
                country.getName(),
                country.getContinent()
        );
    }
    public static List<DisplayCountryDTO> from (List<Country> countries)
    {
        return countries.stream().map(i->from(i)).collect(Collectors.toList());
    }
    public Country toCountry()
    {
        return  new Country(name,continent);
    }
}
