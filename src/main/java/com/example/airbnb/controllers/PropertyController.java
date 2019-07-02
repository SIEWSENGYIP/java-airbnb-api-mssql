package com.example.airbnb.controllers;

import java.util.List;

import com.example.airbnb.entities.Property;
import com.example.airbnb.repositories.PropertyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path="/api")
public class PropertyController {
    
    @Autowired
    PropertyRepository propertyRepository;
	@GetMapping(path="/properties", produces="application/json")
    public List<Property> displayProperties() {
        return propertyRepository.getAllProperties();
    }

    @GetMapping(path="/properties_params", produces="application/json")
    public List<Property> displayPropertyByParams(@RequestParam String address, @RequestParam int numRooms, @RequestParam int price) {
        return propertyRepository.getPropertyByParams(address, numRooms, price);
    }

    @GetMapping(path="/bookedProperties", produces="application/json")
    public List<Property> displayBookedProperties() {
        return propertyRepository.getBookedProperties();
    }

    @GetMapping(path="/orderedProperties", produces="application/json")
    public List<Property> displayOrderedProperties() {
        return propertyRepository.getOrderedProperties();
    }

    @GetMapping(path="/create_property", produces="application/json")
    public void createProperty(
        @RequestParam("address") String address,
        @RequestParam("numRooms") int numRooms,
        @RequestParam("price") int price,
        @RequestParam("allowSmoking") boolean allowSmoking,
        @RequestParam("maxGuestNum") int maxGuestNum
    ){
        Property property = new Property();
        property.setAddress(address);
        property.setAllowSmoking(allowSmoking);
        property.setMaxGuestNum(maxGuestNum);
        property.setNumRooms(numRooms);
        property.setPrice(price);
        
        propertyRepository.createProperty(property);
    }

    @GetMapping(path="/update_property")
    public void updateUser(@RequestParam int id, @RequestParam int price, @RequestParam boolean allowSmoking){
        propertyRepository.updateProperty(id, price, allowSmoking);
    }

    @GetMapping(path="/delete_property")
    public void deleteProperty(@RequestParam int id){
        propertyRepository.deleteProperty(id);
    }
    
    @PostMapping(value="/properties", produces="application/json")
    public Property createProperty(@RequestBody Property property) {
        propertyRepository.createProperty(property);
        return property;
    }
}
