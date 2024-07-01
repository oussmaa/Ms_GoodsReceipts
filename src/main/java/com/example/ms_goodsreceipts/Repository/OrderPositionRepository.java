package com.example.ms_goodsreceipts.Repository;

import com.example.ms_goodsreceipts.Entity.OrderPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderPositionRepository   extends JpaRepository<OrderPosition, Integer> {
}
