package com.example.ms_goodsreceipts.Controller;

import com.example.ms_goodsreceipts.Entity.LocationBinStock;
import com.example.ms_goodsreceipts.service.LocationBinStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locationBinStocks")
public class LocationBinStockController {

    private final LocationBinStockService locationBinStockService;

    @Autowired
    public LocationBinStockController(LocationBinStockService locationBinStockService) {
        this.locationBinStockService = locationBinStockService;
    }

    @PostMapping
    public ResponseEntity<LocationBinStock> createLocationBinStock(@RequestBody LocationBinStock locationBinStock) {
        LocationBinStock createdLocationBinStock = locationBinStockService.saveLocationBinStock(locationBinStock);
        return new ResponseEntity<>(createdLocationBinStock, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocationBinStock> getLocationBinStockById(@PathVariable Long id) {
        LocationBinStock locationBinStock = locationBinStockService.getLocationBinStockById(id);
        if (locationBinStock != null) {
            return new ResponseEntity<>(locationBinStock, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<LocationBinStock>> getAllLocationBinStocks() {
        List<LocationBinStock> locationBinStocks = locationBinStockService.getAllLocationBinStocks();
        return new ResponseEntity<>(locationBinStocks, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocationBinStock(@PathVariable Long id) {
        locationBinStockService.deleteLocationBinStock(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}