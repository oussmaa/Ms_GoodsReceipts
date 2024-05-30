package com.example.ms_goodsreceipts.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class OrderStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long orderStockNb;
    private String Description;
    private Double QuantityNeeded;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;


    @OneToOne(mappedBy = "orderStock", cascade = CascadeType.ALL)
    private GoodsReceipt goodsReceipt;


}
