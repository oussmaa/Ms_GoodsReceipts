package com.example.ms_goodsreceipts.service;

import com.example.ms_goodsreceipts.Entity.LocationAreaStock;
import com.example.ms_goodsreceipts.Entity.LocationBinStock;
import com.example.ms_goodsreceipts.Entity.LocationPlace;
import com.example.ms_goodsreceipts.Repository.LocationBinStockRepository;
import com.example.ms_goodsreceipts.Repository.LocationPlaceRepository;
import com.example.ms_goodsreceipts.Request.LocationPlaceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationPlaceService {

    @Autowired
    private   LocationPlaceRepository locationPlaceRepository;

    @Autowired
    private LocationBinStockRepository locationBinStockRepository;


    public String saveLocationPlace(Long id , LocationPlaceRequest locationPlaceRequest) {

            LocationPlace loc = locationPlaceRepository.findLocationBinStockByPlace(locationPlaceRequest.getLocation_Place());
            Optional<LocationBinStock> locationBinStock = locationBinStockRepository.findById(id);

        if (locationBinStock.isPresent()) {
            if (loc == null) {
                LocationPlace locationPlace = new LocationPlace();
                locationPlace.setPlace(locationPlaceRequest.getLocation_Place());
                locationPlace.setLocationBinStock(locationBinStock.orElseThrow());
                locationPlaceRepository.save(locationPlace);

                return "Save Successfully";

            } else {
                return "This Location is aleardy exist";
            }
        }
        else {
            return "The Id of Location Bin doesn't Exist";
        }

    }

    public List<LocationPlace> getLocationPlaceById(Long id) {
        return locationPlaceRepository.findLocationplaceStockById(id);
    }

    public List<LocationPlace> getAllLocationPlaces() {
        return locationPlaceRepository.findAll();
    }

    public void deleteLocationPlace(Long id) {
        locationPlaceRepository.deleteById(id);
    }
}
