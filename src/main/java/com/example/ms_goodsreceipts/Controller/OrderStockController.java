package com.example.ms_goodsreceipts.Controller;

import com.example.ms_goodsreceipts.Entity.OrderStock;
import com.example.ms_goodsreceipts.service.OrderStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orderstocks")
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
    public OrderStock createOrderStock(@RequestBody OrderStock orderStock) {
        return orderStockService.createOrderStock(orderStock);
    }

    // Add any additional endpoints for CRUD operations or custom actions
}
