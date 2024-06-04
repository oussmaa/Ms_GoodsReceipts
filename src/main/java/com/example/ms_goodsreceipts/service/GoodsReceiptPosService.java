package com.example.ms_goodsreceipts.service;

import com.example.ms_goodsreceipts.Entity.GoodsReceiptPos;
import com.example.ms_goodsreceipts.Repository.GoodsReceiptPosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsReceiptPosService {
    @Autowired
    private   GoodsReceiptPosRepository goodsReceiptPosRepository;



    public GoodsReceiptPos saveGoodsReceiptPos(GoodsReceiptPos goodsReceiptPos) {
        return goodsReceiptPosRepository.save(goodsReceiptPos);
    }

    public GoodsReceiptPos getGoodsReceiptPosById(Long id) {
        return goodsReceiptPosRepository.findById(id).orElse(null);
    }

    public List<GoodsReceiptPos> getAllGoodsReceiptPos() {
        return goodsReceiptPosRepository.findAll();
    }

    public void deleteGoodsReceiptPos(Long id) {
        goodsReceiptPosRepository.deleteById(id);
    }
}