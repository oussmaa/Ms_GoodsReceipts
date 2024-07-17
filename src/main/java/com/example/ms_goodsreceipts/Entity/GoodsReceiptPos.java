package com.example.ms_goodsreceipts.Entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class GoodsReceiptPos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double QuantityBooked;

    private String Description;
    private String Article;
    private LocalDateTime creationDate;
    @PrePersist
    public void prePersist() {
        this.creationDate = LocalDateTime.now();
    }

    @ManyToOne
    @JoinColumn(name = "goodsreceipt_id")
    @JsonBackReference
    private GoodsReceipt goodsReceipt;


    private String location_area;


    private String location_bin;

    private String location_place;


}
