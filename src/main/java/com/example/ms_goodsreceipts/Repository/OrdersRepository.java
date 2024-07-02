package com.example.ms_goodsreceipts.Repository;

import com.example.ms_goodsreceipts.Entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {


}
