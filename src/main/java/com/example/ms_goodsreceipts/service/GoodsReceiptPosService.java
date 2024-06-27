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
import java.util.Optional;

@Service
public class GoodsReceiptPosService {
    @Autowired
    private   GoodsReceiptPosRepository goodsReceiptPosRepository;

    @Autowired
    private   OrderStockRepository orderStockRepository;

    @Autowired
    private MouvementService mouvementService;

    @Autowired
    private GoodsReceiptRepository goodsReceiptRepository;

    @Autowired
    private LocationAreaStockRepository locationAreaStockRepository;



    @Transactional
    public String createGoodsReceiptPos(GoodsReceiptPosRequest goodsReceiptPosRequest) {

        // Create a new GoodsReceiptPos instance
        GoodsReceiptPos goodsReceiptPos = new GoodsReceiptPos();
        goodsReceiptPos.setDescription(goodsReceiptPosRequest.getDescription());
        goodsReceiptPos.setArticle(goodsReceiptPosRequest.getArticle());
        goodsReceiptPos.setQuantityBooket(goodsReceiptPosRequest.getQuantityBooked());

        // Find the location area stock by area
        LocationAreaStock locationAreaStock = locationAreaStockRepository.findLocationAreaStockByArea(goodsReceiptPosRequest.getLocation_area());

        if (locationAreaStock != null) {
            // Set the location area stock to the goods receipt position
            goodsReceiptPos.setLocationAreaStock(locationAreaStock);

            // Find the goods receipt by ID
            GoodsReceipt goodsReceipt = goodsReceiptRepository.findById(goodsReceiptPosRequest.getIdgoodesreciept())
                    .orElseThrow(() -> new ResourceNotFoundException("Goods receipt not found for this id :: " + goodsReceiptPosRequest.getIdgoodesreciept()));
            // Set the goods receipt to the goods receipt position
            goodsReceiptPos.setGoodsReceipt(goodsReceipt);

            // Save the goods receipt position
          long idtr =   goodsReceiptPosRepository.save(goodsReceiptPos).getId();
            Mouvement mv = new Mouvement();
            mv.setDescription("Save createGoodsReceiptPos entity");
            mv.setMouvement("Save createGoodsReceiptPos entity");
            mv.setIdtransaction(idtr);

            mouvementService.SaveMouvement(mv);

            CheckStatus(goodsReceipt.getId());



        } else {
            return "The location area does not exist.";
        }

        return "Save successful";
    }

    public void CheckStatus(long idgoodesreciept)
    {
        Double countstock = goodsReceiptPosRepository.GetGoodsReceiptPosByIDGoodsReceip(idgoodesreciept);
        GoodsReceipt goodsReceipt = goodsReceiptRepository.findById(idgoodesreciept).orElseThrow();

        if (countstock.equals(goodsReceipt.getQuantityUsed()))
        {
            goodsReceipt.setStatus("Close");
            goodsReceiptRepository.save(goodsReceipt);
        }


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