package com.example.ms_goodsreceipts.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Customer extends Supplier{


    private String allCost;

    @OneToOne
    @JoinColumn(name = "shipment_id")
    private Shipment shipment;
}
