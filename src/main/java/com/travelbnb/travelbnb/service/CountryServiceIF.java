package com.travelbnb.travelbnb.service;

import com.travelbnb.travelbnb.entity.Country;

public interface CountryServiceIF {

    Country addCountry(Country country);

    void deleteCountry(long countryId);

    Country updateCountry(Country country, long countryId);
}
