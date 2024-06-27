package com.example.ms_goodsreceipts.Repository;

import com.example.ms_goodsreceipts.Entity.GoodsReceiptPos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsReceiptPosRepository extends JpaRepository<GoodsReceiptPos, Long> {

     @Query("SELECT SUM(gd.QuantityBooket) FROM GoodsReceiptPos gd WHERE gd.goodsReceipt.id = :id")
     Double  GetGoodsReceiptPosByIDGoodsReceip(Long id);
}
