package com.travelbnb.travelbnb.service;


import com.travelbnb.travelbnb.entity.Country;
import com.travelbnb.travelbnb.repository.CountryEntityRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CountryServiceIMPL implements CountryServiceIF{

    private CountryEntityRepository countryEntityRepository ;


    public CountryServiceIMPL(CountryEntityRepository countryEntityRepository) {
        this.countryEntityRepository = countryEntityRepository;
    }


    @Override
    public Country addCountry(Country country) {
        Country countryEntity = new Country();
        countryEntity.setName(country.getName());
        Country save = countryEntityRepository.save(countryEntity);
        return save;
    }

    @Override
    public void deleteCountry(long countryId) {
        countryEntityRepository.deleteById(countryId);
    }

    @Override
    public Country updateCountry(Country country, long countryId) {
        Optional<Country> byCountry = countryEntityRepository.findById(countryId);
        Country getcountry = byCountry.get();
        getcountry.setName(country.getName());
        return countryEntityRepository.save(getcountry);
    }
}
