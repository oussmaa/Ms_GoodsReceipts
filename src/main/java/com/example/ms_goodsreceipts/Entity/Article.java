package com.example.ms_goodsreceipts.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String Articel;
    private LocalDateTime creationDate;
    private String TypeArticle;
    private String Description;
    private Double Price;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Globalestock> stocks  = new ArrayList<>();;

    @PrePersist
    public void prePersist() {
        this.creationDate = LocalDateTime.now();
    }


}
