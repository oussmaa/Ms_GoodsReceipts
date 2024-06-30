package com.example.ms_goodsreceipts.Entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class LocationBinStock {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String Bin;
    private LocalDateTime creationDate;
    @PrePersist
    public void prePersist() {
        this.creationDate = LocalDateTime.now();
    }

    @ManyToOne
    @JoinColumn(name = "locationAreaStock_id", nullable = false)
    @JsonBackReference
    private LocationAreaStock locationAreaStock;


    @OneToMany(mappedBy = "locationBinStock", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<LocationPlace> locationPlaces;


}
