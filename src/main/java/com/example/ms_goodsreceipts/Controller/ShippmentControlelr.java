package com.example.ms_goodsreceipts.Controller;

import com.example.ms_goodsreceipts.Entity.Shipment;
import com.example.ms_goodsreceipts.Request.ShippmentRequest;
import com.example.ms_goodsreceipts.service.ShippmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shippment")
public class ShippmentControlelr {

    @Autowired
    private ShippmentService shippmentService;

    @PostMapping("/addshipment")
    public Shipment AddShipment(@RequestBody ShippmentRequest shippmentRequest) {

        return shippmentService.saveReceipt(shippmentRequest);
    }

    @GetMapping("/GetAllshippment")
    public List<Shipment> GetListShippment()
    {
        return shippmentService.GetAlShippment();
    }


}
