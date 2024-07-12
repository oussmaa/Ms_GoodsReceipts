package com.example.ms_goodsreceipts.Controller;

import com.example.ms_goodsreceipts.Entity.OrderStock;
import com.example.ms_goodsreceipts.Request.OrderStockRequest;
import com.example.ms_goodsreceipts.service.OrderStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orderstocks")
@CrossOrigin(origins = "http://localhost:3000")
public class StockOrderController {

    @Autowired
    private OrderStockService orderStockService;

    @PostMapping("/addstock")
    public ResponseEntity<OrderStock> createOrderStock(@RequestBody OrderStockRequest orderStockRequest) {
        return orderStockService.createOrderStock(orderStockRequest);
    }
    @GetMapping
    public Iterable<OrderStock> getAllOrderStocks() {
        return orderStockService.getAllOrderStocks();
    }
}
