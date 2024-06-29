package com.travelbnb.travelbnb.service;

import com.travelbnb.travelbnb.dto.FavouriteDto;
import com.travelbnb.travelbnb.entity.AppUser;
import com.travelbnb.travelbnb.entity.Favourite;
import com.travelbnb.travelbnb.entity.Property;
import com.travelbnb.travelbnb.repository.FavouriteRepository;
import com.travelbnb.travelbnb.repository.PropertyEntityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteServiceIMPL implements FavoriteService {


    private FavouriteRepository favouriteRepository ;
    private PropertyEntityRepository propertyEntityRepository ;


    public FavoriteServiceIMPL(FavouriteRepository favouriteRepository, PropertyEntityRepository propertyEntityRepository) {
        this.favouriteRepository = favouriteRepository;
        this.propertyEntityRepository = propertyEntityRepository;
    }


    @Override
    public Favourite addFavourite(AppUser user, long propertyId, Favourite  favourite) {
        Property property = propertyEntityRepository.findById(propertyId).get();
        favourite.setAppUser(user);
        favourite.setProperty(property);
        Favourite saveFavourite = favouriteRepository.save(favourite);
      return saveFavourite;

    }


    @Override
    public List<Favourite> getUserFavourite(AppUser user) {
        List<Favourite> LsUserFavourite = favouriteRepository.findByUserFavourite(user);
        return LsUserFavourite;
    }















//    public Favourite dtoToEntity(FavouriteDto favouriteDto ){
//        Favourite favourite = new Favourite();
//        favourite.setStatus(favouriteDto.getStatus());
//        favourite.setProperty(favouriteDto.getProperty());
//        favourite.setAppUser(favouriteDto.getAppUser());
//
//        return favourite;
//    }
//
//    public FavouriteDto entityToDto(Favourite favourite ){
//        FavouriteDto dto = new FavouriteDto();
//        dto.setId(favourite.getId());
//        dto.setStatus(favourite.getStatus());
//        dto.setProperty(favourite.getProperty());
//        dto.setAppUser(favourite.getAppUser());
//        return dto;
//    }
}
