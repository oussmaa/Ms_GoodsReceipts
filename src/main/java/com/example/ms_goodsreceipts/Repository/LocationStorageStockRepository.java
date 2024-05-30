package com.example.ms_goodsreceipts.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationStorageStockRepository extends JpaRepository<LocationStorageStockRepository, Long> {
}
