package com.example.ms_goodsreceipts.Entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class LocationAreaStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String Area;

    @OneToMany(mappedBy = "locationAreaStock", cascade = CascadeType.ALL)
    private List<LocationBinStock> locationBinStocks;

    @ManyToOne
    @JoinColumn(name = "globalestock_id")
    private Globalestock globalestock;
    private LocalDateTime creationDate;
    @PrePersist
    public void prePersist() {
        this.creationDate = LocalDateTime.now();
    }

    @OneToOne
    @JoinColumn(name = "goodsReceiptPos_id")
    @JsonBackReference
    private GoodsReceiptPos goodsReceiptPos;

}
