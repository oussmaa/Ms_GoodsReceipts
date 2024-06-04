package com.example.ms_goodsreceipts.Controller;

import com.example.ms_goodsreceipts.Entity.GoodsReceiptPos;
import com.example.ms_goodsreceipts.service.GoodsReceiptPosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goodsReceiptPos")
public class GoodsReceiptPosController {
    @Autowired
    private GoodsReceiptPosService goodsReceiptPosService;



    @PostMapping
    public ResponseEntity<GoodsReceiptPos> createGoodsReceiptPos(@RequestBody GoodsReceiptPos goodsReceiptPos) {
        GoodsReceiptPos createdGoodsReceiptPos = goodsReceiptPosService.saveGoodsReceiptPos(goodsReceiptPos);
        return new ResponseEntity<>(createdGoodsReceiptPos, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GoodsReceiptPos> getGoodsReceiptPosById(@PathVariable Long id) {
        GoodsReceiptPos goodsReceiptPos = goodsReceiptPosService.getGoodsReceiptPosById(id);
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