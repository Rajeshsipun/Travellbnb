package com.travelbnb.travelbnb.controller;


import com.travelbnb.travelbnb.entity.Location;
import com.travelbnb.travelbnb.service.LocationServiceIF;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/Location")
public class LocationController {


    private LocationServiceIF locationServiceIF ;

    public LocationController(LocationServiceIF locationServiceIF) {
        this.locationServiceIF = locationServiceIF;
    }

//http://localhost:8080/api/v1/Location/addLocation
    @PostMapping("/addLocation")
    public ResponseEntity<Location>addLocation(@RequestBody Location locationEntity ){
        Location addLocation  =locationServiceIF.addLocation(locationEntity);
        return  new ResponseEntity<>(addLocation, HttpStatus.OK);
    }

    //http://localhost:8080/api/v1/Location?locationId=14
    @DeleteMapping
    public ResponseEntity<String>deleteLocation(@RequestParam long  locationId){
        locationServiceIF.deleteLocetion(locationId);
        return new ResponseEntity<>("Delete Location Successful",HttpStatus.OK);
    }

    //Update
    //http://localhost:8080/api/v1/Location/2
    @PutMapping("/{locationId}")
    public ResponseEntity<Location>UpdateLocation(
            @PathVariable long locationId,
            @RequestBody Location location ){

      Location upLocation = locationServiceIF.updateLocation(locationId,location);
            return new ResponseEntity<>(upLocation,HttpStatus.OK);
        }
    }

