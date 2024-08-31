package com.example.ms_goodsreceipts.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToMany(mappedBy = "shipment", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Picking> pickings;



    private String receiptDate;
    private String receiptTime;
    private String receiptAddress;
    private String receiptCity;
    private String receiptState;
    private String receiptZip;
    private String receiptCountry;
    private String receiptPhone;
    private String receiptEmail;
    private String receiptName;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "shipment")
    @JsonManagedReference
    private Customer customer;





}
