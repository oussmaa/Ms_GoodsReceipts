package com.example.ms_goodsreceipts.service;

import com.example.ms_goodsreceipts.Entity.LocationAreaStock;
import com.example.ms_goodsreceipts.Repository.LocationAreaStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationAreaStockService {

    @Autowired
    private   LocationAreaStockRepository locationAreaStockRepository;


    public LocationAreaStock saveLocationAreaStock(LocationAreaStock locationAreaStock) {
        return locationAreaStockRepository.save(locationAreaStock);
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