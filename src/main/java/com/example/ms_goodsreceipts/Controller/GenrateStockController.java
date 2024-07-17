package com.example.ms_goodsreceipts.Controller;

import com.example.ms_goodsreceipts.Entity.Globalestock;
import com.example.ms_goodsreceipts.Entity.Picking;
import com.example.ms_goodsreceipts.Entity.PickingPosition;
import com.example.ms_goodsreceipts.service.PickingPositionService;
import com.example.ms_goodsreceipts.service.PickingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/Picking")
public class GenrateStockController {


    @Autowired
    private PickingService pickingService;

    @Autowired
    private PickingPositionService pickingPositionService;

    @GetMapping("/getallpicking")
    public ResponseEntity<List<Picking>> getAllGlobalestocks() {
        List<Picking> globalestocks = pickingService.getAllPickings();
        return ResponseEntity.ok(globalestocks);
    }
    @GetMapping("/getallpickingbyposition/{id}")
    public ResponseEntity<List<PickingPosition>> GetALLPositionById(@PathVariable long id) {
        List<PickingPosition> pickingspsotin = pickingPositionService.GetALLPositionById(id);
        return ResponseEntity.ok(pickingspsotin);
    }


}
