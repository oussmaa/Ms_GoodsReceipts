package com.example.ms_goodsreceipts.service;

import com.example.ms_goodsreceipts.Entity.OrderStock;
import com.example.ms_goodsreceipts.Entity.Supplier;
import com.example.ms_goodsreceipts.Repository.OrderStockRepository;
import com.example.ms_goodsreceipts.Repository.SupplierRepository;
import com.example.ms_goodsreceipts.Request.OrderStockRequest;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderStockService {

    @Autowired
    private OrderStockRepository orderStockRepository;
    @Autowired
    private SupplierRepository supplierRepository;
    public Iterable<OrderStock> getAllOrderStocks() {
        return orderStockRepository.findAll();
    }

    public Optional<OrderStock> getOrderStockById(Long id) {
        return orderStockRepository.findById(id);
    }

    public ResponseEntity<OrderStock> createOrderStock(OrderStockRequest orderStockRequest) {
        Optional<Supplier> supplierOptional = supplierRepository.findById(orderStockRequest.getSupplier_id());
        if (!supplierOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Supplier supplier = supplierOptional.get();

        OrderStock orderStock = new OrderStock();
        orderStock.setDescription(orderStockRequest.getDescription());
        orderStock.setQuantityNeeded(orderStockRequest.getQuantityNeeded());
        orderStock.setArticel(orderStockRequest.getArticel());
        orderStock.setSupplier(supplier);

        orderStockRepository.save(orderStock);

        return ResponseEntity.ok(orderStock);
    }

    public OrderStock updateOrderStock(Long id, OrderStock updatedOrderStock) {
        Optional<OrderStock> existingOrderStock = orderStockRepository.findById(id);
        if (existingOrderStock.isPresent()) {
            OrderStock orderStock = existingOrderStock.get();
             orderStock.setDescription(updatedOrderStock.getDescription());
             orderStock.setArticel(updatedOrderStock.getArticel());
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