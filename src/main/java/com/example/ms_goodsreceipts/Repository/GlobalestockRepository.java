package com.example.ms_goodsreceipts.Repository;

import com.example.ms_goodsreceipts.Entity.Globalestock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GlobalestockRepository extends JpaRepository<Globalestock, Long> {
}
