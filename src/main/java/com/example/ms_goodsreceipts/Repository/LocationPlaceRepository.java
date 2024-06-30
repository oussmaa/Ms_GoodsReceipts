package com.example.ms_goodsreceipts.Repository;


import com.example.ms_goodsreceipts.Entity.LocationAreaStock;
import com.example.ms_goodsreceipts.Entity.LocationBinStock;
import com.example.ms_goodsreceipts.Entity.LocationPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationPlaceRepository extends JpaRepository<LocationPlace, Long> {
    @Query("select lk from  LocationPlace lk where  lk.Place=:id")
    LocationPlace findLocationBinStockByPlace(String id);

    @Query("select lk from  LocationPlace lk where  lk.locationBinStock.id=:id")
    List<LocationPlace> findLocationplaceStockById(Long id);
}
