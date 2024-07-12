package com.example.ms_goodsreceipts.Controller;

import com.example.ms_goodsreceipts.Entity.GoodsReceiptPos;
import com.example.ms_goodsreceipts.Request.GoodsReceiptPosRequest;
import com.example.ms_goodsreceipts.service.GoodsReceiptPosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goodsReceiptPos")
@CrossOrigin(origins = "http://localhost:3000")
public class GoodsReceiptPosController {
    @Autowired
    private GoodsReceiptPosService goodsReceiptPosService;

    @PostMapping
    public ResponseEntity<String> createGoodsReceiptPos(@RequestBody GoodsReceiptPosRequest GoodsReceiptPosRequest) {
        String goodsReceiptPos = goodsReceiptPosService.createGoodsReceiptPos(GoodsReceiptPosRequest);
        return new ResponseEntity<>(goodsReceiptPos, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<GoodsReceiptPos>> getGoodsReceiptPosById(@PathVariable Long id) {
        List<GoodsReceiptPos> goodsReceiptPos = goodsReceiptPosService.getGoodsReceiptPosById(id);
        if (goodsReceiptPos != null) {
            return new ResponseEntity<>(goodsReceiptPos, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<GoodsReceiptPos>> getAllGoodsReceiptPos() {
        List<GoodsReceiptPos> goodsReceiptPosList = goodsReceiptPosService.getAllGoodsReceiptPos();
        return new ResponseEntity<>(goodsReceiptPosList, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGoodsReceiptPos(@PathVariable Long id) {
        goodsReceiptPosService.deleteGoodsReceiptPos(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}