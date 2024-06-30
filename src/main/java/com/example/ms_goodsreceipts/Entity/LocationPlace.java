package com.example.ms_goodsreceipts.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class LocationPlace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String Place;

    @ManyToOne
    @JoinColumn(name = "locationBinStock_id", nullable = false)
    @JsonBackReference
    private LocationBinStock locationBinStock;
    private LocalDateTime creationDate;
    @PrePersist
    public void prePersist() {
        this.creationDate = LocalDateTime.now();
    }
}
