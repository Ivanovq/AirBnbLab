package mk.ukim.finki.airbnblab.model.dto;

import jakarta.persistence.ManyToOne;
import mk.ukim.finki.airbnblab.model.Country;

public class HostDto {

    String name;
    String surname;

    Long country;


    public HostDto() {
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setCountry(Long country) {
        this.country = country;
    }


    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Long getCountry() {
        return country;
    }

    public HostDto(String name, String surname, Long country) {
        this.name = name;
        this.surname = surname;
        this.country = country;
    }
}
