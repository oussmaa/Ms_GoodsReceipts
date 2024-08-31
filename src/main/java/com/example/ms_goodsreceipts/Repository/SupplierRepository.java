package com.example.ms_goodsreceipts.Repository;

import com.example.ms_goodsreceipts.Entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {


     @Query("SELECT s FROM Supplier s WHERE TYPE(s) = Supplier")
    List<Supplier> findAllSuppliers();
}
