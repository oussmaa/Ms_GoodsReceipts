package com.example.ms_goodsreceipts.Entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Globalestock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double QuantityUsed;

    @ManyToOne
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;

    private Double OpeningQuantity;

    @OneToMany(mappedBy = "globalestock", cascade = CascadeType.ALL)
    private List<LocationAreaStock> locationAreaStocks;
}
