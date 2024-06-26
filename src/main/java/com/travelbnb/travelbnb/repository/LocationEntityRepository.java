package com.travelbnb.travelbnb.repository;

import com.travelbnb.travelbnb.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationEntityRepository extends JpaRepository<Location, Long> {
}