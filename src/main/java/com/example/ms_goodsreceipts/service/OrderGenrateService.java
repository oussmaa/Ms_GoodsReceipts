package com.example.ms_goodsreceipts.service;

import com.example.ms_goodsreceipts.Entity.*;
import com.example.ms_goodsreceipts.Repository.*;
import com.example.ms_goodsreceipts.Request.OrderPositionRequest;
import com.example.ms_goodsreceipts.Request.OrderRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderGenrateService {

    @Autowired
    public OrdersRepository  ordersRepository;

    @Autowired
    public GlobalestockRepository globalestockRepository;

    @Autowired
    public PickingPositionRepository pickingPositionRepository;

    @Autowired
    public PickingRepository pickingRepository;

    @Autowired
    public CustomerRepository customerRepository;


    @Autowired
    public OrderPositionRepository orderPositionRepository;

    @Transactional
    public Orders addOrder(OrderRequest orderDto) {
        Orders order = new Orders();
        order.setDescription(orderDto.getDescription());
        order.setStatus("INPROGRESS");
        order.setGoPicking(false);
        order.setType(orderDto.getType());

        Customer customer = customerRepository.findById((Long.parseLong(orderDto.getIdcustomer()))).orElseThrow();
        order.setCustomer(customer);
        return ordersRepository.save(order);
    }



    @Transactional
    public ResponseEntity<String> generatepicking(Long orderId) {
        try {

            Orders order = ordersRepository.findById(orderId)
                    .orElseThrow(() -> new RuntimeException("Order not found"));
            if(order.getGoPicking() ) {
                 return new ResponseEntity<>("Order Is Genrated to Picking", HttpStatus.BAD_REQUEST);

            }
            else if(order.getOrderPositions().isEmpty())
            {
                return new ResponseEntity<>("This order has no Position", HttpStatus.BAD_REQUEST);

            }
            else if(order.getStatus() == "CLOSED")
            {
                return new ResponseEntity<>("This order is CLOSED", HttpStatus.BAD_REQUEST);

            }
            else {

            List<OrderPosition> orderPositions = orderPositionRepository.findorderpositionbyidorder(order.getId());

            Picking picking = new Picking();
            picking.setDescription(order.getDescription());
            picking.setName("Picking For this Order: "+order.getId());
            picking.setName(order.getDescription());
            picking.setStatus("INPROGRESS");
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
                pickingPosition.setArticle(orderPosition.getArticle());
                pickingPositionRepository.save(pickingPosition);



            }

            order.setGoPicking(true);
            order.setStatus("CLOSED");
            return new ResponseEntity<>("Picking Genrated successfully for this Order", HttpStatus.OK);

            }
        }catch (Exception e)
        {

            return new ResponseEntity<>( e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }


    }

    @Transactional
    public ResponseEntity<String> addOrderPosition(OrderPositionRequest orderPositionDto) {

        Orders order = ordersRepository.findById(orderPositionDto.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));


        Globalestock globalestock = globalestockRepository.findlistStockByLocationAndArticle(orderPositionDto.getLocationArea(), orderPositionDto.getLocationBin(), orderPositionDto.getLocationPlace(), orderPositionDto.getArticle());

        if(globalestock !=null && globalestock.getOpeningQuantity() >= orderPositionDto.getQuantity() && "INPROGRESS".equals(order.getStatus())) {

            OrderPosition position = new OrderPosition();
            position.setArticle(orderPositionDto.getArticle());
            position.setDescription(orderPositionDto.getDescription());
            position.setQuantity(orderPositionDto.getQuantity());
            position.setLocationArea(orderPositionDto.getLocationArea());
            position.setLocationBin(orderPositionDto.getLocationBin());
            position.setLocationPlace(orderPositionDto.getLocationPlace());
            position.setOrders(order);
            orderPositionRepository.save(position);
            globalestock.setReservedStock(globalestock.getReservedStock()+orderPositionDto.getQuantity());
            globalestock.setOpeningQuantity(Math.abs(globalestock.getOpeningQuantity()-orderPositionDto.getQuantity()));
            globalestockRepository.save(globalestock);
            return new ResponseEntity<>("The position was crated and the quantity was reserved", HttpStatus.OK);

        }

        else
        {
            return new ResponseEntity<>("No Valid  quantity in this Location Or order is CLOSED ", HttpStatus.BAD_REQUEST);

        }

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
