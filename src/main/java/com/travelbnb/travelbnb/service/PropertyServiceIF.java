package com.travelbnb.travelbnb.service;

import com.travelbnb.travelbnb.dto.AppUserDto;
import com.travelbnb.travelbnb.dto.PropertyDto;
import com.travelbnb.travelbnb.dto.ReviewsDto;
import com.travelbnb.travelbnb.entity.Property;

import java.util.List;

public interface PropertyServiceIF {


    PropertyDto addProperty(Property propertyEntity, long countryId, long locationId);

    void deleteProperty(long propertyId);

    List<PropertyDto> getProperty(int pageSize, int pageNo, String sortBy, String sortDir);

    Property getByProperty(long propertyId);

    PropertyDto updateProperty(long propertyId, long countryId, long locationId, PropertyDto propertyDto);


         List<Property> searchProperties(String name);


}
