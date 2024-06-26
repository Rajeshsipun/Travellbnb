package com.travelbnb.travelbnb.repository;

import com.travelbnb.travelbnb.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PropertyEntityRepository extends JpaRepository<Property, Long> {





    @Query("Select   p from Property p JOIN Location l on p.location=l.id JOIN Country c  on p. country = c.id where l.name=:locationName or c.name =:locationName")
    List<Property> searchProperty(@Param("locationName")String locationName);



}