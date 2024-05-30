package com.example.ms_goodsreceipts.Repository;


import com.example.ms_goodsreceipts.Entity.OrderStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStockRepository extends JpaRepository<OrderStock, Long> {
}
