package com.example.ms_goodsreceipts.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PickingPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "picking_id")
    @JsonBackReference
    private Picking picking;

    public String Status;

    private Double Openquantity;

    private Double Bookedquantity;

    private String LocationArea;
    private String LocationBin;
    private String LocationPlace;

    private String Article;



}
