package com.example.ms_goodsreceipts.Repository;

import com.example.ms_goodsreceipts.Entity.LocationAreaStock;
import com.example.ms_goodsreceipts.Entity.LocationBinStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationBinStockRepository extends JpaRepository<LocationBinStock, Long> {

}
