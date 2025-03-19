package mk.ukim.finki.airbnblab.model.dto;

public class CountryDto {
    String name;
    String continent;


    public String getName() {
        return name;
    }

    public String getContinent() {
        return continent;
    }

    public CountryDto() {
    }

    public CountryDto(String name, String continent) {
        this.name = name;
        this.continent = continent;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }
}
