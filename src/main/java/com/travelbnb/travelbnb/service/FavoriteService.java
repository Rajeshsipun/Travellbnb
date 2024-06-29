package com.travelbnb.travelbnb.service;

import com.travelbnb.travelbnb.dto.FavouriteDto;
import com.travelbnb.travelbnb.entity.AppUser;
import com.travelbnb.travelbnb.entity.Favourite;

import java.util.List;

public interface FavoriteService {
    Favourite addFavourite(AppUser user, long propertyId, Favourite  favourite);

    List<Favourite> getUserFavourite(AppUser user);
}
