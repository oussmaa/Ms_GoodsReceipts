package com.example.ms_goodsreceipts.Controller;

import com.example.ms_goodsreceipts.Entity.GoodsReceipt;
import com.example.ms_goodsreceipts.Request.GoodsReceiptRequest;
import com.example.ms_goodsreceipts.service.GoodsReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goodsReceipts")
@CrossOrigin(origins = "http://localhost:3000")
public class GoodsReceiptController {
    @Autowired
    private  GoodsReceiptService goodsReceiptService;


    @PostMapping
    public ResponseEntity<String> createGoodsReceipt(@RequestBody GoodsReceiptRequest goodsReceiptRequest) {
        String createdGoodsReceipt = goodsReceiptService.createGoodsReceipt(goodsReceiptRequest);
            return new ResponseEntity<>(createdGoodsReceipt, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GoodsReceipt> getGoodsReceiptById(@PathVariable Long id) {
        GoodsReceipt goodsReceipt = goodsReceiptService.getGoodsReceipt(id);
        if (goodsReceipt != null) {
            return new ResponseEntity<>(goodsReceipt, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping
    public ResponseEntity<List<GoodsReceipt>> getAllGoodsReceipts() {
        List<GoodsReceipt> goodsReceipts = goodsReceiptService.getAllGoodsReceipts();
        return new ResponseEntity<>(goodsReceipts, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGoodsReceipt(@PathVariable Long id) {
        goodsReceiptService.deleteGoodsReceipt(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}