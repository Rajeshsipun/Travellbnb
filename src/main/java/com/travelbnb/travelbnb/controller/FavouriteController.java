package com.travelbnb.travelbnb.controller;


import com.travelbnb.travelbnb.dto.FavouriteDto;
import com.travelbnb.travelbnb.entity.AppUser;
import com.travelbnb.travelbnb.entity.Favourite;
import com.travelbnb.travelbnb.repository.FavouriteRepository;
import com.travelbnb.travelbnb.service.AppUserService;
import com.travelbnb.travelbnb.service.FavoriteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/favourites")
public class FavouriteController {

    private FavoriteService favoriteService ;

    public FavouriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    //http://localhost:8080/api/v1/favourites/addFavourite?propertyId=1

    @PostMapping("/addFavourite")
    public ResponseEntity<Favourite>addFavourite(
            @AuthenticationPrincipal AppUser user ,
            @RequestParam long propertyId,
            @RequestBody Favourite favourite
            ){
        Favourite favourite1 = favoriteService.addFavourite(user, propertyId, favourite);
        return new ResponseEntity<>(favourite1, HttpStatus.CREATED);

    }

//http://localhost:8080/api/v1/favourites/getUserFavourite

    @GetMapping("/getUserFavourite")
    public ResponseEntity<List<Favourite>>getUserFavourite(
            @AuthenticationPrincipal AppUser user
    ){
        List<Favourite>favourite =favoriteService.getUserFavourite(user);
        return new ResponseEntity<>(favourite,HttpStatus.CREATED);
    }
}
