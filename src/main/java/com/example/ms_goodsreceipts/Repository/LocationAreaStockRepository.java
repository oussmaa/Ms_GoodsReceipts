package com.example.ms_goodsreceipts.Repository;

import com.example.ms_goodsreceipts.Entity.LocationAreaStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationAreaStockRepository extends JpaRepository<LocationAreaStock, Long> {

    @Query("select lk from  LocationAreaStock lk where  lk.Area=:id")
    LocationAreaStock findLocationAreaStockByArea(String id);
}
