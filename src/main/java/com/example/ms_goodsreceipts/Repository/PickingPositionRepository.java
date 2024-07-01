package com.example.ms_goodsreceipts.Repository;

import com.example.ms_goodsreceipts.Entity.PickingPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PickingPositionRepository extends JpaRepository<PickingPosition, Integer> {
}
