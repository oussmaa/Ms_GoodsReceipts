package com.example.ms_goodsreceipts.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class OrderStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
     private String description;
    private Double quantityNeeded;
    private String articel;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;


    @OneToOne(mappedBy = "orderStock", cascade = CascadeType.ALL)
    private GoodsReceipt goodsReceipt;

    private LocalDateTime creationDate;
    private Boolean documentOpenOrNot;
    @PrePersist
    public void prePersist() {
        this.creationDate = LocalDateTime.now();
    }

}
