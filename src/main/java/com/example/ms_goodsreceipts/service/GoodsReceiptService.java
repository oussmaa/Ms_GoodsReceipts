package com.example.ms_goodsreceipts.service;

import com.example.ms_goodsreceipts.Entity.GoodsReceipt;
import com.example.ms_goodsreceipts.Entity.GoodsReceiptPos;
import com.example.ms_goodsreceipts.Entity.OrderStock;
import com.example.ms_goodsreceipts.Exception.ResourceNotFoundException;
import com.example.ms_goodsreceipts.Repository.GoodsReceiptPosRepository;
import com.example.ms_goodsreceipts.Repository.GoodsReceiptRepository;
import com.example.ms_goodsreceipts.Repository.OrderStockRepository;
import com.example.ms_goodsreceipts.Request.GoodsReceiptRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GoodsReceiptService {

    @Autowired
    private GoodsReceiptRepository goodsReceiptRepository;

    @Autowired
    private GoodsReceiptPosRepository goodsReceiptPosRepository;

    @Autowired
    private OrderStockRepository orderStockRepository;



    @Transactional
    public String createGoodsReceipt(GoodsReceiptRequest goodsReceiptCreateDTO) {
        GoodsReceipt goodsReceipt = new GoodsReceipt();
        goodsReceipt.setDescription(goodsReceiptCreateDTO.getDescription());

        OrderStock orderStock = orderStockRepository.findById(goodsReceiptCreateDTO.getOrderStockId())
                .orElseThrow(() -> new ResourceNotFoundException("OrderStock not found for this id :: " + goodsReceiptCreateDTO.getOrderStockId()));

        GoodsReceipt goodsReceipt2=  goodsReceiptRepository.findGoodsReceiptByOrderStockID(goodsReceiptCreateDTO.getOrderStockId());
        if (goodsReceipt2 == null) {
            goodsReceipt.setOrderStock(orderStock);

             goodsReceiptRepository.save(goodsReceipt);
             return  "Goods Receipt Is Created ";
        }

          return "the order is saved with another Goods Receipt";

    }


    @Transactional(readOnly = true)
    public GoodsReceipt getGoodsReceipt(Long id) {
        return goodsReceiptRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("GoodsReceipt not found for this id :: " + id));
    }

    @Transactional(readOnly = true)
    public List<GoodsReceipt> getAllGoodsReceipts() {
        return goodsReceiptRepository.findAll();
    }

    @Transactional
    public GoodsReceipt updateGoodsReceipt(Long id, GoodsReceiptRequest goodsReceiptUpdateDTO) {
        GoodsReceipt goodsReceipt = goodsReceiptRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("GoodsReceipt not found for this id :: " + id));

        goodsReceipt.setDescription(goodsReceiptUpdateDTO.getDescription());

        OrderStock orderStock = orderStockRepository.findById(goodsReceiptUpdateDTO.getOrderStockId())
                .orElseThrow(() -> new ResourceNotFoundException("OrderStock not found for this id :: " + goodsReceiptUpdateDTO.getOrderStockId()));

        goodsReceipt.setOrderStock(orderStock);

        return goodsReceiptRepository.save(goodsReceipt);
    }

    @Transactional
    public void deleteGoodsReceipt(Long id) {
        GoodsReceipt goodsReceipt = goodsReceiptRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("GoodsReceipt not found for this id :: " + id));

        goodsReceiptRepository.delete(goodsReceipt);
    }
}

