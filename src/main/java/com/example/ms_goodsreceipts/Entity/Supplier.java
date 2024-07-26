package com.example.ms_goodsreceipts.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED) // or SINGLETABLE, TABLE_PER_CLASS
public abstract class  Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;

    private String phone;
    private String email;
    private String city;
    private String state;
    private String zip;
    private String country;
    private LocalDateTime creationDate;
    @PrePersist
    public void prePersist() {
        this.creationDate = LocalDateTime.now();
    }
}
