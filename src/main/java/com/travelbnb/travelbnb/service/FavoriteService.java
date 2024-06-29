package com.travelbnb.travelbnb.service;

import com.travelbnb.travelbnb.dto.FavouriteDto;
import com.travelbnb.travelbnb.entity.AppUser;
import com.travelbnb.travelbnb.entity.Favourite;

public interface FavoriteService {
    Favourite addFavourite(AppUser user, long propertyId, Favourite  favourite);
}
