package com.example.ms_goodsreceipts.Request;

import com.example.ms_goodsreceipts.Entity.Globalestock;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Data
public class ArticleRequest {

    private Long id;


    private String Articel;
    private LocalDateTime creationDate;
    private String TypeArticle;
    private String Description;
    private Double Price;


    private List<GlobaleRequest> stocks  = new ArrayList<>();
}
