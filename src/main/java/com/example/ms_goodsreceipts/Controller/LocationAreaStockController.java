package com.example.ms_goodsreceipts.Controller;

import com.example.ms_goodsreceipts.Entity.LocationAreaStock;
import com.example.ms_goodsreceipts.service.LocationAreaStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locationAreaStocks")
public class LocationAreaStockController {

    @Autowired
    private  LocationAreaStockService locationAreaStockService;

    @PostMapping
    public ResponseEntity<LocationAreaStock> createLocationAreaStock(@RequestBody LocationAreaStock locationAreaStock) {
        LocationAreaStock createdLocationAreaStock = locationAreaStockService.saveLocationAreaStock(locationAreaStock);
        return new ResponseEntity<>(createdLocationAreaStock, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocationAreaStock> getLocationAreaStockById(@PathVariable Long id) {
        LocationAreaStock locationAreaStock = locationAreaStockService.getLocationAreaStockById(id);
        if (locationAreaStock != null) {
            return new ResponseEntity<>(locationAreaStock, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<LocationAreaStock>> getAllLocationAreaStocks() {
        List<LocationAreaStock> locationAreaStocks = locationAreaStockService.getAllLocationAreaStocks();
        return new ResponseEntity<>(locationAreaStocks, HttpStatus.OK);
    }
}