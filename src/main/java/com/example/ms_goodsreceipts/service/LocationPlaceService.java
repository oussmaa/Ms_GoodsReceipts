package com.example.ms_goodsreceipts.service;

import com.example.ms_goodsreceipts.Entity.LocationPlace;
import com.example.ms_goodsreceipts.Repository.LocationPlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationPlaceService {
    @Autowired
    private   LocationPlaceRepository locationPlaceRepository;




    public LocationPlace saveLocationPlace(LocationPlace locationPlace) {
        return locationPlaceRepository.save(locationPlace);
    }

    public LocationPlace getLocationPlaceById(Long id) {
        return locationPlaceRepository.findById(id).orElse(null);
    }

    public List<LocationPlace> getAllLocationPlaces() {
        return locationPlaceRepository.findAll();
    }

    public void deleteLocationPlace(Long id) {
        locationPlaceRepository.deleteById(id);
    }
}
