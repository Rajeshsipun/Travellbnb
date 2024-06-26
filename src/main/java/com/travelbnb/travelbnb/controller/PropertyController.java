package com.travelbnb.travelbnb.controller;


import com.travelbnb.travelbnb.dto.PropertyDto;
import com.travelbnb.travelbnb.entity.Property;
import com.travelbnb.travelbnb.repository.PropertyEntityRepository;
import com.travelbnb.travelbnb.service.PropertyServiceIF;
import com.travelbnb.travelbnb.service.PropertyServiceIMPL;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Property")
public class PropertyController {

    private PropertyServiceIF propertyServiceIF ;

    public PropertyController(PropertyServiceIF propertyServiceIF,
                              PropertyEntityRepository propertyEntityRepository, PropertyServiceIMPL propertyServiceIMPL) {
        this.propertyServiceIF = propertyServiceIF;
    }
//http://localhost:8080/api/v1/Property/addProperty

    @PostMapping("/addProperty")
    public ResponseEntity<?>addProperty(
            @Valid
            @RequestBody Property propertyEntity,
            long countryId,
            long locationId,
            BindingResult result
            ){
        if (result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.OK);
        }

      PropertyDto propertyDto = propertyServiceIF.addProperty(propertyEntity,countryId,locationId);
      return new ResponseEntity<>(propertyDto,HttpStatus.OK);

    }

    //http://localhost:8080/api/v1/Property?propertyId=3

    @DeleteMapping
    public ResponseEntity<String>deleteProperty(@RequestParam long propertyId){
        propertyServiceIF.deleteProperty(propertyId);
        return new ResponseEntity<>("Delete Property Successful",HttpStatus.OK);
    }

//Update
    //Http://localhost:8080/api/v1/Property/10
    @PutMapping("/{propertyId}/{countryId}/{locationId}")
    public ResponseEntity<PropertyDto>UpdateProperty(
            @PathVariable long propertyId,@PathVariable long countryId,
            @PathVariable long locationId, @RequestBody PropertyDto propertyDto
            ){
        PropertyDto propertyDt =propertyServiceIF.updateProperty(
                propertyId, countryId,locationId,propertyDto);

        return new ResponseEntity<>(propertyDt,HttpStatus.OK);
    }


    //getProperty
    //http://localhost:8080/api/v1/Property?pageNo=3&pageSize=0
    @GetMapping
    public ResponseEntity<List<PropertyDto>>getProperty(
            @RequestParam(name= "pageSize",defaultValue = "3",required = false)int pageSize,
            @RequestParam(name = "pageNo",defaultValue = "0",required = false)int pageNo,
            @RequestParam(name = "sortBy",defaultValue = "Id",required = false) String sortBy,
            @RequestParam(name= "sortDir",defaultValue = "Id",required = false)String sortDir
    ){
        List<PropertyDto> property = propertyServiceIF.getProperty(pageSize,pageNo,sortBy,sortDir);
        return new ResponseEntity<>(property,HttpStatus.OK);
    }


     //getByProperty
    // http://localhost:8080/api/v1/Property/GetByProperty?PropertyId=5
    @GetMapping("/GetByProperty")
    public ResponseEntity<Property>getByProperty(@RequestParam long PropertyId){
        Property property =  propertyServiceIF.getByProperty(PropertyId);
        return new ResponseEntity<>(property,HttpStatus.OK);

    }

//SearchProperty
 //http://localhost:8080/api/v1/Property/search/getLocationName?Name=goa

    @GetMapping("/search/getLocationName")
    public ResponseEntity<List<Property>>searchProperty(@RequestParam String name ){
        List<Property> properties = propertyServiceIF.searchProperties(name);
        return new ResponseEntity<>(properties,HttpStatus.OK);
    }
}
