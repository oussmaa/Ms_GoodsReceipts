package com.example.ms_goodsreceipts.Controller;

import com.example.ms_goodsreceipts.Entity.OrderPosition;
import com.example.ms_goodsreceipts.Entity.Orders;
import com.example.ms_goodsreceipts.Request.OrderPositionRequest;
import com.example.ms_goodsreceipts.Request.OrderRequest;
import com.example.ms_goodsreceipts.service.OrderGenrateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordersgenrate")
public class OrdersController {


        @Autowired
        private OrderGenrateService orderService;

        @PostMapping
        public ResponseEntity<Orders> addOrder(@RequestBody OrderRequest orderDto) {
            Orders order = orderService.addOrder(orderDto);
            return ResponseEntity.ok(order);
        }

        @PostMapping("/position")
        public ResponseEntity<OrderPosition> addOrderPosition(@RequestBody OrderPositionRequest orderPositionDto) {
            OrderPosition position = orderService.addOrderPosition(orderPositionDto);
            return ResponseEntity.ok(position);
        }

        @PutMapping
        public ResponseEntity<Orders> updateOrder(@RequestBody OrderRequest orderDto) {
            Orders order = orderService.updateOrder(orderDto);
            return ResponseEntity.ok(order);
        }

        @PutMapping("/position")
        public ResponseEntity<OrderPosition> updateOrderPosition(@RequestBody OrderPositionRequest orderPositionDto) {
            OrderPosition position = orderService.updateOrderPosition(orderPositionDto);
            return ResponseEntity.ok(position);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
            orderService.deleteOrder(id);
            return ResponseEntity.noContent().build();
        }

        @DeleteMapping("/position/{id}")
        public ResponseEntity<Void> deleteOrderPosition(@PathVariable Long id) {
            orderService.deleteOrderPosition(id);
            return ResponseEntity.noContent().build();
        }

    @GetMapping("/{id}/positions")
    public ResponseEntity<List<OrderPosition>> getOrderPositionsByOrderId(@PathVariable Long id) {
        List<OrderPosition> positions = orderService.GetallPosition(id);
        return ResponseEntity.ok(positions);
    }
    @GetMapping
    public ResponseEntity<List<Orders>> getallorder() {
        List<Orders> positions = orderService.GetallOrder();
        return ResponseEntity.ok(positions);
    }
    }
