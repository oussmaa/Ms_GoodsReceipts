package com.example.ms_goodsreceipts.Entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class GoodsReceiptPos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double QuantityBooket;
    private String Description;
    private String Article;

    @ManyToOne
    @JoinColumn(name = "goodsreceipt_id")
    private GoodsReceipt goodsReceipt;



}
