package com.example.ms_goodsreceipts.Entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference
    private List<LocationBinStock> locationBinStocks;

    @ManyToOne
    @JoinColumn(name = "globalestock_id")
    @JsonIgnore
    private Globalestock globalestock;

    private LocalDateTime creationDate;
    @PrePersist
    public void prePersist() {
        this.creationDate = LocalDateTime.now();
    }



}
