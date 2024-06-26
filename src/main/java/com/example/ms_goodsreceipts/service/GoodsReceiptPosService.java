package com.example.ms_goodsreceipts.service;

import com.example.ms_goodsreceipts.Entity.*;
import com.example.ms_goodsreceipts.Exception.ResourceNotFoundException;
import com.example.ms_goodsreceipts.Repository.*;
import com.example.ms_goodsreceipts.Request.GoodsReceiptPosRequest;
import com.example.ms_goodsreceipts.Request.GoodsReceiptRequest;
import com.example.ms_goodsreceipts.Request.LocationAreaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GoodsReceiptPosService {
    @Autowired
    private   GoodsReceiptPosRepository goodsReceiptPosRepository;

    @Autowired
    private GoodsReceiptRepository goodsReceiptRepository;

    @Autowired
    private LocationAreaStockRepository locationAreaStockRepository;

    @Autowired
    private LocationBinStockRepository locationBinStockRepositoryl;

    @Autowired
    private LocationPlaceRepository locationPlaceRepository;

    @Transactional
    public String createGoodsReceiptPos(GoodsReceiptPosRequest goodsReceiptPosRequest) {

        GoodsReceiptPos goodsReceiptpos = new GoodsReceiptPos();
        goodsReceiptpos.setDescription(goodsReceiptPosRequest.getDescription());
        goodsReceiptpos.setArticle(goodsReceiptPosRequest.getArticle());
        goodsReceiptpos.setQuantityBooket(goodsReceiptPosRequest.getQuantityBooked());


        LocationAreaStock locationAreaStock = locationAreaStockRepository.findLocationAreaStockByArea(goodsReceiptPosRequest.getLocation_area());

        if (locationAreaStock != null) {
            goodsReceiptpos.setLocationAreaStock(locationAreaStock);
            GoodsReceipt goodsReceipt = goodsReceiptRepository.findById(goodsReceiptPosRequest.getIdgoodesreciept())
                    .orElseThrow(() -> new ResourceNotFoundException("Goods receipt not found for this id :: " + goodsReceiptPosRequest.getIdgoodesreciept()));

            goodsReceiptpos.setGoodsReceipt(goodsReceipt);

            goodsReceiptPosRepository.save(goodsReceiptpos);
        }
        else {
            return "the Location doesn't Exist ";
        }


        return "Save successful";
    }


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