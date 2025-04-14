package mk.ukim.finki.airbnblab.DTO;

import jakarta.persistence.ManyToOne;
import mk.ukim.finki.airbnblab.model.Country;
import mk.ukim.finki.airbnblab.model.Host;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayHostDTO(Long id,String name,String surname,Country country) {

    public static DisplayHostDTO from(Host host)
    {
        return new DisplayHostDTO(
                host.getId(),
                host.getName(),
                host.getSurname(),
                host.getCountry()
        );
    }
    public static List<DisplayHostDTO> from(List<Host> hosts)
    {
        return hosts.stream().map(i->from(i)).collect(Collectors.toList());
    }
    public Host toHost()
    {
        return new Host(name,surname,country);
    }
}
