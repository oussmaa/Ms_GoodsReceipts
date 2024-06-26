package com.example.ms_goodsreceipts.Repository;

import com.example.ms_goodsreceipts.Entity.GoodsReceipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsReceiptRepository extends JpaRepository<GoodsReceipt, Long> {


    @Query("select gd from GoodsReceipt gd where gd.orderStock.id = :idorderStockID")
     GoodsReceipt findGoodsReceiptByOrderStockID(long idorderStockID);
}
