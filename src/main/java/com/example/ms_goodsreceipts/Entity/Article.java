package com.example.ms_goodsreceipts.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Artticle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String Articel;
    private LocalDateTime creationDate;
    private String TypeArticle;
    private String Description;


    @PrePersist
    public void prePersist() {
        this.creationDate = LocalDateTime.now();
    }


}
