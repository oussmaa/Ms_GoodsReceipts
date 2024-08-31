package com.example.ms_goodsreceipts.Repository;

import com.example.ms_goodsreceipts.Entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippmentRepository extends JpaRepository<Shipment,Long> {
}
