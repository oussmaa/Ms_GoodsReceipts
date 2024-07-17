package com.example.ms_goodsreceipts.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class OrderPosition {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "orders_id")
    @JsonBackReference
    private Orders orders;

    public String Article;

    public String Description;

    private Double Quantity;

    private String LocationArea;

    private String LocationBin;
    private String LocationPlace;



}
