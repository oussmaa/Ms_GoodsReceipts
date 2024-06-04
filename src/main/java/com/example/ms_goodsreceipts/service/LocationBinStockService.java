package com.example.ms_goodsreceipts.service;

import com.example.ms_goodsreceipts.Entity.LocationBinStock;
import com.example.ms_goodsreceipts.Repository.LocationBinStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationBinStockService {
    @Autowired
    private   LocationBinStockRepository locationBinStockRepository;

    public LocationBinStock saveLocationBinStock(LocationBinStock locationBinStock) {
        return locationBinStockRepository.save(locationBinStock);
    }

    public LocationBinStock getLocationBinStockById(Long id) {
        return locationBinStockRepository.findById(id).orElse(null);
    }

    public List<LocationBinStock> getAllLocationBinStocks() {
        return locationBinStockRepository.findAll();
    }

    public void deleteLocationBinStock(Long id) {
        locationBinStockRepository.deleteById(id);
    }
}