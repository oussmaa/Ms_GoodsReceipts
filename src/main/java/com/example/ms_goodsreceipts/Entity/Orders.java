package com.example.ms_goodsreceipts.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String Description;

    public String Status;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<OrderPosition> orderPositions;

    @OneToOne(mappedBy = "orders", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Picking picking;

}
