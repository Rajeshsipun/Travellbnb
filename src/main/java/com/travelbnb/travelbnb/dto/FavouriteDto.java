package com.travelbnb.travelbnb.dto;

import com.travelbnb.travelbnb.entity.AppUser;
import com.travelbnb.travelbnb.entity.Property;

public class FavouriteDto {
    private Long id;
    private Property property;
    private AppUser appUser;
    private Boolean status;




    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
