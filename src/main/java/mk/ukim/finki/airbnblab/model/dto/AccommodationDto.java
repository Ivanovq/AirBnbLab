package mk.ukim.finki.airbnblab.model.dto;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import mk.ukim.finki.airbnblab.model.Enumerations.Category;
import mk.ukim.finki.airbnblab.model.Host;

@Data
@Getter
@Setter

public class AccommodationDto{

    String name;
    Category category;
    Long host;
    Integer numRooms;

    public AccommodationDto() {
    }

    public AccommodationDto(String name, Category category, Long host, Integer numRooms) {
        this.name = name;
        this.category = category;
        this.host = host;
        this.numRooms = numRooms;
    }



    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setHost(Long host) {
        this.host = host;
    }

    public void setNumRooms(Integer numRooms) {
        this.numRooms = numRooms;
    }


    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public Long getHost() {
        return host;
    }

    public Integer getNumRooms() {
        return numRooms;
    }
}
