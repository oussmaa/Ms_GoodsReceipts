package com.example.ms_goodsreceipts.service;

import com.example.ms_goodsreceipts.Entity.OrderStock;
import com.example.ms_goodsreceipts.Repository.OrderStockRepository;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderStockService {
    @Autowired
    private OrderStockRepository orderStockRepository;




    public Iterable<OrderStock> getAllOrderStocks() {
        return orderStockRepository.findAll();
    }

    public Optional<OrderStock> getOrderStockById(Long id) {
        return orderStockRepository.findById(id);
    }

    public OrderStock createOrderStock(OrderStock orderStock) {
        return orderStockRepository.save(orderStock);
    }

    public OrderStock updateOrderStock(Long id, OrderStock updatedOrderStock) {
        Optional<OrderStock> existingOrderStock = orderStockRepository.findById(id);
        if (existingOrderStock.isPresent()) {
            OrderStock orderStock = existingOrderStock.get();
            orderStock.setOrderStockNb(updatedOrderStock.getOrderStockNb());
            orderStock.setDescription(updatedOrderStock.getDescription());
            orderStock.setQuantityNeeded(updatedOrderStock.getQuantityNeeded());
            orderStock.setSupplier(updatedOrderStock.getSupplier());
            orderStock.setGoodsReceipt(updatedOrderStock.getGoodsReceipt());

            return orderStockRepository.save(orderStock);
        } else {
            throw new IllegalArgumentException("OrderStock with ID " + id + " not found");
        }
    }

    public void deleteOrderStock(Long id) {
        orderStockRepository.deleteById(id);
    }
}