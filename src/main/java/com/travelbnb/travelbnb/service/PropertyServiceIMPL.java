package com.travelbnb.travelbnb.service;


import com.travelbnb.travelbnb.dto.AppUserDto;
import com.travelbnb.travelbnb.dto.PropertyDto;
import com.travelbnb.travelbnb.dto.ReviewsDto;
import com.travelbnb.travelbnb.entity.Country;
import com.travelbnb.travelbnb.entity.Location;
import com.travelbnb.travelbnb.entity.Property;
import com.travelbnb.travelbnb.exception.ResourceNotException;
import com.travelbnb.travelbnb.repository.CountryEntityRepository;
import com.travelbnb.travelbnb.repository.LocationEntityRepository;
import com.travelbnb.travelbnb.repository.PropertyEntityRepository;
import com.travelbnb.travelbnb.repository.ReviewsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PropertyServiceIMPL implements PropertyServiceIF {

    private PropertyEntityRepository propertyEntityRepository;
    private CountryEntityRepository countryEntityRepository;
    private LocationEntityRepository locationEntityRepository;
    private ReviewsRepository reviewsRepository ;

    public PropertyServiceIMPL(PropertyEntityRepository propertyEntityRepository,
                               CountryEntityRepository countryEntityRepository,
                               LocationEntityRepository locationEntityRepository, ReviewsRepository reviewsRepository) {
        this.propertyEntityRepository = propertyEntityRepository;
        this.countryEntityRepository = countryEntityRepository;
        this.locationEntityRepository = locationEntityRepository;
        this.reviewsRepository = reviewsRepository;
    }


    @Override
    public PropertyDto addProperty(Property propertyEntity, long countryId, long locationId) {
        Country countryEntity = countryEntityRepository.findById(countryId).get();
        propertyEntity.setCountry(countryEntity);

        Location locationEntity = locationEntityRepository.findById(locationId).get();
        propertyEntity.setLocation(locationEntity);

        Property save = propertyEntityRepository.save(propertyEntity);
        PropertyDto propertyDto = entityToDto(save);
        return propertyDto;
    }


    public Property dtoToEntity(PropertyDto propertyDto) {
        Property propertyEntity = new Property();

        propertyEntity.setName(propertyDto.getName());
        propertyEntity.setPrice(propertyDto.getPrice());
        propertyEntity.setNoGuests(propertyDto.getNoGuests());
        propertyEntity.setNoBedroom(propertyDto.getNoBedroom());
        propertyEntity.setNoBathroom(propertyDto.getNoBathroom());
        propertyEntity.setLocation(propertyDto.getLocation());
        propertyEntity.setCountry(propertyDto.getCountry());

        return propertyEntity;
    }

    public PropertyDto entityToDto(Property propertyEntity) {
        PropertyDto dto = new PropertyDto();

        dto.setId(propertyEntity.getId());
        dto.setName(propertyEntity.getName());

        dto.setNoGuests(propertyEntity.getNoGuests());
        dto.setNoBedroom(propertyEntity.getNoBedroom());
        dto.setNoBathroom(propertyEntity.getNoBathroom());
           dto.setPrice(propertyEntity.getPrice());
           dto.setCountry(propertyEntity.getCountry());
           dto.setLocation(propertyEntity.getLocation());
        return dto;
    }


    @Override
    public void deleteProperty(long propertyId) {
        propertyEntityRepository.deleteById(propertyId);
    }


    @Override
    public List<PropertyDto> getProperty(int pageSize, int pageNo, String sortBy, String sortDir) {
        Pageable pageable =null;
        if (sortDir.equalsIgnoreCase("asc")){
             pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).ascending());
        }
        else if (sortDir.equalsIgnoreCase("desc")){
            pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).ascending().descending());
        }


        Page<Property> allProperty = propertyEntityRepository.findAll(pageable);
        List<Property> content = allProperty.getContent();
        List<PropertyDto> collect =
                content.stream().map(p -> entityToDto(p)).collect(Collectors.toList());

        return collect;
    }

    @Override
    public Property getByProperty(long propertyId) {
        Property property = propertyEntityRepository.findById(propertyId).orElseThrow(
                ()-> new ResourceNotException("User Not Found With Id :"+propertyId)
        );

        return property;
    }

    @Override
    public PropertyDto updateProperty
            ( long propertyId,long countryId, long locationId, PropertyDto propertyDto) {

        Optional<Property> byProperty = propertyEntityRepository.findById(propertyId);
        Property property = null;
        property = null;
        if (byProperty.isPresent()){
            property = byProperty.get();
            property.setName(propertyDto.getName());
            property.setNoBedroom(propertyDto.getNoBedroom());
            property.setNoBathroom(propertyDto.getNoBathroom());
            property.setNoGuests(propertyDto.getNoGuests());
            property.setPrice(propertyDto.getPrice());
            property.setLocation(propertyDto.getLocation());
            property.setCountry(propertyDto.getCountry());

            Country country = countryEntityRepository.findById(countryId).get();
            property.setCountry(country);


            Location location1 = locationEntityRepository.findById(locationId).get();
            property.setLocation(location1);

            Property saveProperty = propertyEntityRepository.save(property);
            PropertyDto propertyDto1 = entityToDto(saveProperty);
                    return propertyDto1;
        }else {
            throw new ResourceNotException("Property Id Not Found"+propertyId);
        }

    }


    @Override
    public List<Property> searchProperties(String name) {
        List<Property> properties = propertyEntityRepository.searchProperty(name);
        return properties;

}


}



