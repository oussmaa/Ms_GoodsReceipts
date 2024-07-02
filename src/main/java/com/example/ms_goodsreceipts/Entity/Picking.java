package com.example.ms_goodsreceipts.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Picking {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    public String Status;

    @OneToMany(mappedBy = "picking", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<PickingPosition> pickingPositions;

    @OneToOne
    @JoinColumn(name = "order_id")
    @JsonBackReference
    private Orders orders;



}
