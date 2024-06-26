package com.example.ms_goodsreceipts.Repository;


import com.example.ms_goodsreceipts.Entity.LocationAreaStock;
import com.example.ms_goodsreceipts.Entity.LocationPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationPlaceRepository extends JpaRepository<LocationPlace, Long> {

}
