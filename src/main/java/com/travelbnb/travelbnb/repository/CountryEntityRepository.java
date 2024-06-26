package com.travelbnb.travelbnb.repository;

import com.travelbnb.travelbnb.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryEntityRepository extends JpaRepository<Country, Long> {
}