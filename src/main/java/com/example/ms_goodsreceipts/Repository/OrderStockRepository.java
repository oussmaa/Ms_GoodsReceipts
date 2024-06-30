package com.example.ms_goodsreceipts.Repository;


import com.example.ms_goodsreceipts.Entity.LocationPlace;
import com.example.ms_goodsreceipts.Entity.OrderStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStockRepository extends JpaRepository<OrderStock, Long> {

        @Query("select lk from  OrderStock lk where  lk.goodsReceipt.id=:id")
        OrderStock findArticlebyorderofstock(Long id);
}
