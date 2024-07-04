package com.example.ms_goodsreceipts.Repository;

import com.example.ms_goodsreceipts.Entity.PickingPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public interface PickingPositionRepository extends JpaRepository<PickingPosition, Long> {

    @Query("select lk from  PickingPosition lk where  lk.picking.id=:id")
    List<PickingPosition> findpickingByPosition(Long id);

    @Query("select lk from  PickingPosition lk where  lk.Status=:status")
    List<PickingPosition> findpickingByPositionSatus(String status);


}
