package com.example.ms_goodsreceipts.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class GoodsReceipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
     private String Description;

    @OneToMany(mappedBy = "goodsReceipt", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<GoodsReceiptPos> goodsReceiptPos;

    private LocalDateTime creationDate;
    @PrePersist
    public void prePersist() {
        this.creationDate = LocalDateTime.now();
    }
    @OneToOne
    @JoinColumn(name = "orderStock_id")
    @JsonBackReference
    private OrderStock orderStock;




}
