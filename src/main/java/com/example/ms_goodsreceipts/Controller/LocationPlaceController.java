package com.example.ms_goodsreceipts.Controller;

import com.example.ms_goodsreceipts.Entity.LocationPlace;
import com.example.ms_goodsreceipts.Request.LocationPlaceRequest;
import com.example.ms_goodsreceipts.service.LocationPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locationPlaces")
@CrossOrigin(origins = "http://localhost:3000")
public class LocationPlaceController {

    @Autowired
    private LocationPlaceService locationPlaceService;

    @PostMapping("/{id}")
    public ResponseEntity<String> createLocationPlace(@PathVariable Long id , @RequestBody LocationPlaceRequest locationPlaceRequest) {
        String createdLocationPlace = locationPlaceService.saveLocationPlace(id,locationPlaceRequest);
        return new ResponseEntity<>(createdLocationPlace, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<LocationPlace>> getLocationPlaceById(@PathVariable Long id) {
        List<LocationPlace> locationPlace = locationPlaceService.getLocationPlaceById(id);
        if (locationPlace != null) {
            return new ResponseEntity<>(locationPlace, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<LocationPlace>> getAllLocationPlaces() {
        List<LocationPlace> locationPlaces = locationPlaceService.getAllLocationPlaces();
        return new ResponseEntity<>(locationPlaces, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocationPlace(@PathVariable Long id) {
        locationPlaceService.deleteLocationPlace(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}