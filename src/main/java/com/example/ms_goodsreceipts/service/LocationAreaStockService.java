package com.example.ms_goodsreceipts.service;

import com.example.ms_goodsreceipts.Entity.LocationAreaStock;
import com.example.ms_goodsreceipts.Repository.LocationAreaStockRepository;
import com.example.ms_goodsreceipts.Request.LocationAreaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LocationAreaStockService {

    @Autowired
    private  LocationAreaStockRepository locationAreaStockRepository;


    public  String saveLocationAreaStock(LocationAreaRequest locationAreaRequest) {
        LocationAreaStock loc = locationAreaStockRepository.findLocationAreaStockByArea(locationAreaRequest.getLocation_area());
     if (loc==null)
     {
         LocationAreaStock locationAreaStock = new LocationAreaStock();
         locationAreaStock.setArea(locationAreaRequest.getLocation_area());
         locationAreaStockRepository.save(locationAreaStock);
         return "Save Succefuly";
     }
     else
     {
         return "This Location is aleardy exist";

     }




    }

    public LocationAreaStock getLocationAreaStockById(Long id) {
        return locationAreaStockRepository.findById(id).orElse(null);
    }

    public List<LocationAreaStock> getAllLocationAreaStocks() {
        return locationAreaStockRepository.findAll();
    }

    public void deleteLocationAreaStock(Long id) {
        locationAreaStockRepository.deleteById(id);
    }
}