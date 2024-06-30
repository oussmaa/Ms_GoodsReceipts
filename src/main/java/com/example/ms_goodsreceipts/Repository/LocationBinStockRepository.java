package com.example.ms_goodsreceipts.Repository;

import com.example.ms_goodsreceipts.Entity.LocationAreaStock;
import com.example.ms_goodsreceipts.Entity.LocationBinStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationBinStockRepository extends JpaRepository<LocationBinStock, Long> {
    @Query("select lk from  LocationBinStock lk where  lk.Bin=:id")
    LocationBinStock findLocationBinStockByArea(String id);

    @Query("select lk from  LocationBinStock lk where  lk.locationAreaStock.id=:id")
    List<LocationBinStock> findLocationBinStockBy(Long id);
}
