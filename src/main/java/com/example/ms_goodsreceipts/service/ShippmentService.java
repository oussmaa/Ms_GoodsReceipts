package com.example.ms_goodsreceipts.service;

import com.example.ms_goodsreceipts.Entity.Picking;
import com.example.ms_goodsreceipts.Entity.Shipment;
import com.example.ms_goodsreceipts.Repository.PickingRepository;
import com.example.ms_goodsreceipts.Repository.ShippmentRepository;
import com.example.ms_goodsreceipts.Request.ShippmentRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShippmentService {

    @Autowired
    private ShippmentRepository shippmentRepository;

    @Autowired
    private PickingRepository pickingRepository;
    @Transactional
    public Shipment saveReceipt(ShippmentRequest shippmentRequest) {

        Shipment receipt = new Shipment();
        List<Picking> pickings = new ArrayList<>();

        List<Picking> pickingOrders = pickingRepository.findAllById(shippmentRequest.getPicking());
        receipt.setPickings(pickingOrders);

        // Set the shipment reference in each PickingOrder
        for (Picking pickingOrder : pickingOrders) {
            pickingOrder.setShipment(receipt);
            pickingOrder.setGoShipmment(true);
        }

        receipt.setPickings(pickings);

        receipt.setReceiptDate(shippmentRequest.getReceiptDate());
        receipt.setReceiptTime(shippmentRequest.getReceiptTime());
        receipt.setReceiptAddress(shippmentRequest.getReceiptAddress());
        receipt.setReceiptCity(shippmentRequest.getReceiptCity());
        receipt.setReceiptState(shippmentRequest.getReceiptState());
        receipt.setReceiptZip(shippmentRequest.getReceiptZip());
        receipt.setReceiptCountry(shippmentRequest.getReceiptCountry());
        receipt.setReceiptPhone(shippmentRequest.getReceiptPhone());
        receipt.setReceiptEmail(shippmentRequest.getReceiptEmail());
        receipt.setReceiptName(shippmentRequest.getReceiptName());

        return shippmentRepository.save(receipt);
    }

public List<Shipment> GetAlShippment()
{
    return shippmentRepository.findAll();
}

}
