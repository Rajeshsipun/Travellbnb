package com.travelbnb.travelbnb.repository;

import com.travelbnb.travelbnb.entity.Favourite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavouriteRepository extends JpaRepository<Favourite, Long> {
}