package com.example.ms_goodsreceipts.Controller;

import com.example.ms_goodsreceipts.Entity.OrderPosition;
import com.example.ms_goodsreceipts.Entity.Orders;
import com.example.ms_goodsreceipts.Entity.Picking;
import com.example.ms_goodsreceipts.Entity.PickingPosition;
import com.example.ms_goodsreceipts.service.PickingPositionService;
import com.example.ms_goodsreceipts.service.PickingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/picking")
public class PickingController {

    @Autowired
    private PickingService pickingService;

    @Autowired
    private PickingPositionService pickingPositionService;

    @GetMapping("/getallpositionbyid/{id}")
    public ResponseEntity<List<PickingPosition>> getOrderPositionsByOrderId(@PathVariable Long id) {
        List<PickingPosition> positions = pickingService.getpickingpositionbyid(id);
        return ResponseEntity.ok(positions);
    }
    @GetMapping("/getallpicking")
    public ResponseEntity<List<Picking>> getAllPicking() {
        List<Picking> positions = pickingService.getAllPickings();
        return ResponseEntity.ok(positions);
    }

    @PostMapping("/position/bookposition/{id}")
    public ResponseEntity<String> bookPosition(@PathVariable Long id ,@RequestBody Double quantitybooked) {

      return pickingPositionService.bookPosition(id,quantitybooked);
    }

}
