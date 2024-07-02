package com.example.ms_goodsreceipts.service;

import com.example.ms_goodsreceipts.Entity.OrderPosition;
import com.example.ms_goodsreceipts.Entity.Orders;
import com.example.ms_goodsreceipts.Repository.OrderPositionRepository;
import com.example.ms_goodsreceipts.Repository.OrdersRepository;
import com.example.ms_goodsreceipts.Request.OrderPositionRequest;
import com.example.ms_goodsreceipts.Request.OrderRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderGenrateService {

    @Autowired
    public OrdersRepository  ordersRepository;


    @Autowired
    public OrderPositionRepository orderPositionRepository;

    @Transactional
    public Orders addOrder(OrderRequest orderDto) {
        Orders order = new Orders();
        order.setDescription(orderDto.getDescription());
        order.setStatus(orderDto.getStatus());
        return ordersRepository.save(order);
    }


    @Transactional
    public OrderPosition addOrderPosition(OrderPositionRequest orderPositionDto) {
        Orders order = ordersRepository.findById(orderPositionDto.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        OrderPosition position = new OrderPosition();
        position.setArticle(orderPositionDto.getArticle());
        position.setOrders(order);
        return orderPositionRepository.save(position);
    }

    @Transactional
    public Orders updateOrder(OrderRequest orderDto) {
        Orders order = ordersRepository.findById(orderDto.getId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setDescription(orderDto.getDescription());
        order.setStatus(orderDto.getStatus());
        return ordersRepository.save(order);
    }

    @Transactional
    public OrderPosition updateOrderPosition(OrderPositionRequest orderPositionDto) {
        OrderPosition position = orderPositionRepository.findById(orderPositionDto.getId())
                .orElseThrow(() -> new RuntimeException("Order position not found"));

        position.setArticle(orderPositionDto.getArticle());
        return orderPositionRepository.save(position);
    }

    @Transactional
    public void deleteOrder(Long orderId) {
        Orders order = ordersRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        ordersRepository.delete(order);
    }

    @Transactional
    public void deleteOrderPosition(Long positionId) {
        OrderPosition position = orderPositionRepository.findById(positionId)
                .orElseThrow(() -> new RuntimeException("Order position not found"));

        orderPositionRepository.delete(position);
    }

    @Transactional
    public List<Orders> GetallOrder() {
     return  ordersRepository.findAll();

    }

    @Transactional
    public List<OrderPosition> GetallPosition(Long orderId) {
        return  orderPositionRepository.findorderpositionbyidorder(orderId);

    }
}
