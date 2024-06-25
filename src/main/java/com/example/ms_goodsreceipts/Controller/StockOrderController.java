package com.example.ms_goodsreceipts.Controller;

import com.example.ms_goodsreceipts.Entity.OrderStock;
import com.example.ms_goodsreceipts.Request.OrderStockRequest;
import com.example.ms_goodsreceipts.service.OrderStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orderstocks")
public class StockOrderController {

    @Autowired
    private OrderStockService orderStockService;

    @PostMapping("/test")
    public ResponseEntity<OrderStock> createOrderStock(@RequestBody OrderStockRequest orderStockRequest) {
        return orderStockService.createOrderStock(orderStockRequest);
    }
}
