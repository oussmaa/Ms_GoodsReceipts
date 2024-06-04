package com.example.ms_goodsreceipts.service;

import com.example.ms_goodsreceipts.Entity.GoodsReceipt;
import com.example.ms_goodsreceipts.Repository.GoodsReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsReceiptService {
    @Autowired
    private GoodsReceiptRepository goodsReceiptRepository;




    public GoodsReceipt saveGoodsReceipt(GoodsReceipt goodsReceipt) {
        return goodsReceiptRepository.save(goodsReceipt);
    }

    public GoodsReceipt getGoodsReceiptById(Long id) {
        return goodsReceiptRepository.findById(id).orElse(null);
    }

    public List<GoodsReceipt> getAllGoodsReceipts() {
        return goodsReceiptRepository.findAll();
    }

    public void deleteGoodsReceipt(Long id) {
        goodsReceiptRepository.deleteById(id);
    }
}






