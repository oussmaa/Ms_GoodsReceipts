package com.example.ms_goodsreceipts.service;

import com.example.ms_goodsreceipts.Entity.LocationAreaStock;
import com.example.ms_goodsreceipts.Entity.LocationBinStock;
import com.example.ms_goodsreceipts.Repository.LocationAreaStockRepository;
import com.example.ms_goodsreceipts.Repository.LocationBinStockRepository;
import com.example.ms_goodsreceipts.Request.LocationBinRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationBinStockService {
    @Autowired
    private   LocationBinStockRepository locationBinStockRepository;

    @Autowired
    private LocationAreaStockRepository locationAreaStockRepository;
    public String saveLocationBinStock(Long id, LocationBinRequest locationBinRequest) {
        LocationBinStock loc = locationBinStockRepository.findLocationBinStockByArea(locationBinRequest.getLocation_bin());
        Optional<LocationAreaStock> locationAreaStock = locationAreaStockRepository.findById(id);
        if (locationAreaStock.isPresent()) {
            if (loc == null) {
                LocationBinStock locationBinStock = new LocationBinStock();
                locationBinStock.setBin(locationBinRequest.getLocation_bin());
                locationBinStock.setLocationAreaStock(locationAreaStock.orElseThrow());
                locationBinStockRepository.save(locationBinStock);
                return "Save Successfully";

            } else {
                return "This Location is aleardy exist";
            }
        }
        else {
            return "The Id of Location Area doesn't Exist";
        }
    }

    public List<LocationBinStock> getLocationBinStockById(Long id) {
        return locationBinStockRepository.findLocationBinStockBy(id);
    }
    public LocationBinStock getLocationBinStockBidBy(Long id) {
        return locationBinStockRepository.findById(id).orElse(null);
    }
    public List<LocationBinStock> getAllLocationBinStocks() {
        return locationBinStockRepository.findAll();
    }

    public void deleteLocationBinStock(Long id) {
        locationBinStockRepository.deleteById(id);
    }
}