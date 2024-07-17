package com.example.ms_goodsreceipts.service;

import com.example.ms_goodsreceipts.Entity.OrderPosition;
import com.example.ms_goodsreceipts.Entity.Orders;
import com.example.ms_goodsreceipts.Entity.Picking;
import com.example.ms_goodsreceipts.Entity.PickingPosition;
import com.example.ms_goodsreceipts.Repository.OrderPositionRepository;
import com.example.ms_goodsreceipts.Repository.OrdersRepository;
import com.example.ms_goodsreceipts.Repository.PickingPositionRepository;
import com.example.ms_goodsreceipts.Repository.PickingRepository;
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
    public PickingPositionRepository pickingPositionRepository;

    @Autowired
    public PickingRepository pickingRepository;


    @Autowired
    public OrderPositionRepository orderPositionRepository;

    @Transactional
    public Orders addOrder(OrderRequest orderDto) {
        Orders order = new Orders();
        order.setDescription(orderDto.getDescription());
        order.setStatus("INPROGRESS");
        order.setGoPicking(false);
        return ordersRepository.save(order);
    }



    @Transactional
    public String generatepicking(Long orderId) {
        try {

            Orders order = ordersRepository.findById(orderId)
                    .orElseThrow(() -> new RuntimeException("Order not found"));
            if(order.getGoPicking()) {
                return "Order Is Genrated to Picking";
            }
            else {

            List<OrderPosition> orderPositions = orderPositionRepository.findorderpositionbyidorder(order.getId());

            Picking picking = new Picking();
            picking.setDescription(order.getDescription());
            picking.setName("Picking For this Order: "+order.getId());
            picking.setName("INPROGRESS");
            picking.setOrders(order);


            Picking pickingsaved= pickingRepository.save(picking);

            for (OrderPosition orderPosition : orderPositions) {
                PickingPosition pickingPosition = new PickingPosition();
                pickingPosition.setPicking(pickingsaved);
                pickingPosition.setOpenquantity(orderPosition.getQuantity());
                pickingPosition.setStatus("INPROGRESS");
                pickingPosition.setLocationArea(orderPosition.getLocationArea());
                pickingPosition.setLocationBin(orderPosition.getLocationBin());
                pickingPosition.setLocationPlace(orderPosition.getLocationPlace());
                pickingPosition.setBookedquantity(0.0);
                pickingPositionRepository.save(pickingPosition);

            }
            order.setGoPicking(true);
            return "Picking Genrated successfully for this Order: "+order.getId();

            }
        }catch (Exception e)
        {
            return e.getMessage();
        }


    }

    @Transactional
    public OrderPosition addOrderPosition(OrderPositionRequest orderPositionDto) {
        Orders order = ordersRepository.findById(orderPositionDto.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        OrderPosition position = new OrderPosition();
        position.setArticle(orderPositionDto.getArticel());
        position.setDescription(orderPositionDto.getDescription());
        position.setQuantity(orderPositionDto.getQuantity());
        position.setLocationArea(orderPositionDto.getLocationArea());
        position.setLocationBin(orderPositionDto.getLocationBin());
        position.setLocationPlace(orderPositionDto.getLocationPlace());
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

        position.setArticle(orderPositionDto.getArticel());
        position.setDescription(orderPositionDto.getDescription());
        position.setQuantity(orderPositionDto.getQuantity());
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
