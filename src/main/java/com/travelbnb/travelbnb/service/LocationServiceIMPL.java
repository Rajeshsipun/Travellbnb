package com.travelbnb.travelbnb.service;

import com.travelbnb.travelbnb.entity.Location;
import com.travelbnb.travelbnb.repository.LocationEntityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class LocationServiceIMPL implements LocationServiceIF{

    private LocationEntityRepository locationEntityRepository ;

    public LocationServiceIMPL(LocationEntityRepository locationEntityRepository) {
        this.locationEntityRepository = locationEntityRepository;
    }


    @Override
    public Location addLocation(Location locationEntity) {
        Location entity = new Location();
        entity.setName(locationEntity.getName());
        Location save = locationEntityRepository.save(entity);
        return save;
    }

    @Override
    public void deleteLocetion(long locationId) {
        locationEntityRepository.deleteById(locationId);
    }

    @Override
    public Location updateLocation(long locationId, Location location) {
        Optional<Location> byLocation = locationEntityRepository.findById(locationId);
        Location upLocation = byLocation.get();
        upLocation.setName(location.getName());
        return locationEntityRepository.save(upLocation);
    }
}
