package com.example.ms_goodsreceipts.Repository;

import com.example.ms_goodsreceipts.Entity.PickingPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PickingPositionRepository extends JpaRepository<PickingPosition, Integer> {

    @Query("select lk from  PickingPosition lk where  lk.picking.id=:id")
    List<PickingPosition> findpickingByPosition(Long id);
}
