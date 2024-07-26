package com.example.ms_goodsreceipts.Controller;

import com.example.ms_goodsreceipts.Entity.Article;
import com.example.ms_goodsreceipts.Entity.Globalestock;
import com.example.ms_goodsreceipts.Request.GlobaleRequest;
import com.example.ms_goodsreceipts.service.GlobalesStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/globalestocks")
@CrossOrigin(origins = "http://localhost:3000")
public class GlobalesStockController {

    @Autowired
    private GlobalesStockService globalestockService;

    @GetMapping
    public ResponseEntity<List<GlobaleRequest>> getAllGlobalestocks() {
        List<GlobaleRequest> globalestocks = globalestockService.getAllGlobalestocks();
        return ResponseEntity.ok(globalestocks);
    }

    @GetMapping("/GetStockByLocation/{loc}")
    public ResponseEntity<List<GlobaleRequest>> getGlobalestockBylocation(@PathVariable("loc") String location) {
        List<GlobaleRequest> globalestock = globalestockService.getGlobalestockByLocation(location);
        return new ResponseEntity(globalestock, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Globalestock> getGlobalestockById(@PathVariable("id") Long id) {
        Optional<Globalestock> globalestock = globalestockService.getGlobalestockById(id);
        return globalestock.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

   @PostMapping
    public ResponseEntity<Globalestock> createGlobalestock(@RequestBody GlobaleRequest globalestock) {
        return  globalestockService.createGlobalestock(globalestock);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Globalestock> updateGlobalestock(
            @PathVariable("id") Long id,
            @RequestBody Globalestock updatedGlobalestock
    ) {
        Globalestock globalestock = globalestockService.updateGlobalestock(id, updatedGlobalestock);
        if (globalestock != null) {
            return ResponseEntity.ok(globalestock);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGlobalestock(@PathVariable("id") Long id) {
        boolean deleted = globalestockService.deleteGlobalestock(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}