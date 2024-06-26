package com.travelbnb.travelbnb.dto;

import com.travelbnb.travelbnb.entity.Country;
import com.travelbnb.travelbnb.entity.Location;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

public class PropertyDto {

    private Long id;
    private String noBedroom;
    private String noBathroom;
    private String noGuests;
    private String price;
    private Country country;
    private Location location;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNoGuests() {
        return noGuests;
    }

    public void setNoGuests(String noGuests) {
        this.noGuests = noGuests;
    }

    public String getNoBathroom() {
        return noBathroom;
    }

    public void setNoBathroom(String noBathroom) {
        this.noBathroom = noBathroom;
    }

    public String getNoBedroom() {
        return noBedroom;
    }

    public void setNoBedroom(String noBedroom) {
        this.noBedroom = noBedroom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
