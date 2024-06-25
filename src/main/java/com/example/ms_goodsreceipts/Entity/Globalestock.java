package com.example.ms_goodsreceipts.Entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonBackReference
    private Article article;

    private Double OpeningQuantity;

    @OneToMany(mappedBy = "globalestock", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<LocationAreaStock> locationAreaStocks;
}
