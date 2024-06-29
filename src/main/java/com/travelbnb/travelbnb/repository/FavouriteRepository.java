package com.travelbnb.travelbnb.repository;

import com.travelbnb.travelbnb.entity.AppUser;
import com.travelbnb.travelbnb.entity.Favourite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FavouriteRepository extends JpaRepository<Favourite, Long> {


    @Query("Select f from Favourite f where f.appUser=:user")
    List<Favourite> findByUserFavourite(@Param("user") AppUser user) ;

}