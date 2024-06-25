package com.example.ms_goodsreceipts.Controller;

import com.example.ms_goodsreceipts.Entity.OrderStock;
import com.example.ms_goodsreceipts.Request.OrderStockRequest;
import com.example.ms_goodsreceipts.service.OrderStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/oussama")
public class OrderStockController {

    @Autowired
    private   OrderStockService orderStockService;

    // Endpoint to retrieve all OrderStock entities
    @GetMapping
    public Iterable<OrderStock> getAllOrderStocks() {
        return orderStockService.getAllOrderStocks();
    }

    // Endpoint to create a new OrderStock entity
    @PostMapping
    public ResponseEntity<OrderStock> createOrderStock4(@RequestBody OrderStockRequest orderStock) {
        return orderStockService.createOrderStock(orderStock);
    }
    @PostMapping("/test")
    public ResponseEntity<OrderStock> createOrderStock(@Validated @RequestBody OrderStockRequest orderStockRequest) {
        return orderStockService.createOrderStock(orderStockRequest);
    }
    // Add any additional endpoints for CRUD operations or custom actions
}
