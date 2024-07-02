package com.example.ms_goodsreceipts.Repository;

import com.example.ms_goodsreceipts.Entity.LocationAreaStock;
import com.example.ms_goodsreceipts.Entity.OrderPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderPositionRepository   extends JpaRepository<OrderPosition, Long> {

    @Query("select lk from  OrderPosition lk where  lk.orders.id=:id")
    List<OrderPosition> findorderpositionbyidorder(Long id);
}
