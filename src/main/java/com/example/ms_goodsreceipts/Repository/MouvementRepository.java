package com.example.ms_goodsreceipts.Repository;

 import com.example.ms_goodsreceipts.Entity.Mouvement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MouvementRepository extends JpaRepository<Mouvement, Long> {

}
