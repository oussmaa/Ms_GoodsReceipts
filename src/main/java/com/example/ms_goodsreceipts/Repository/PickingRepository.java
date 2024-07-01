package com.example.ms_goodsreceipts.Repository;

import com.example.ms_goodsreceipts.Entity.Picking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PickingRepository extends JpaRepository<Picking, Integer> {
}
