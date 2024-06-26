package com.travelbnb.travelbnb.controller;


import com.travelbnb.travelbnb.entity.Country;
import com.travelbnb.travelbnb.service.CountryServiceIF;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/Country")
public class CountryController {

    private CountryServiceIF countryServiceIF ;

    public CountryController(CountryServiceIF countryServiceIF) {
        this.countryServiceIF = countryServiceIF;
    }

    //http://localhost:8080/api/v1/Country/addCountry

    @PostMapping("/addCountry")
    public ResponseEntity<Country>addCountry(@Valid @RequestBody Country country ){
        Country countryEntity = countryServiceIF.addCountry(country);
        return new ResponseEntity<>(countryEntity, HttpStatus.OK);
    }

    //http://localhost:8080/api/v1/Country?countryId=1

    @DeleteMapping
    public ResponseEntity<String>deleteCountry(@RequestParam long countryId){
        countryServiceIF.deleteCountry(countryId);
        return new ResponseEntity<>("Delete Country Successful",HttpStatus.OK);
    }


    //UpdateCountry

    //http://localhost:8080/api/v1/Country/2
    @PutMapping("/{countryId}")
    public ResponseEntity<Country>UpdateCountry(
            @Valid
            @RequestBody Country country ,
            @PathVariable long countryId){
        Country updateCountry =   countryServiceIF.updateCountry(country,countryId);
            return new ResponseEntity<>(updateCountry,HttpStatus.OK);
        }
    }


