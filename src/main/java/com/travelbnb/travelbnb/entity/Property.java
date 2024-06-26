package com.travelbnb.travelbnb.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "property")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "no_bedroom", nullable = false)
    private String noBedroom;

    @Column(name = "no_bathroom", nullable = false)
    private String noBathroom;

    @Column(name = "no_guests", nullable = false)
    private String noGuests;

    @Column(name = "price", nullable = false)
    private String price;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @Column(name = "name", nullable = false)
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