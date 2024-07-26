package com.example.ms_goodsreceipts.Request;

import com.example.ms_goodsreceipts.Entity.Article;
import com.example.ms_goodsreceipts.Entity.Globalestock;
import com.example.ms_goodsreceipts.Entity.LocationAreaStock;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GlobaleRequest {

    private Double QuantityUsed;
    private Article article;
    private String articleID;
    public Long id;
    private Long articleId;
    private Double OpeningQuantity;
    public LocationAreaStock locationAreaStock;
    private List<LocationAreaRequest> locationAreaRequests  = new ArrayList<>();
    private String LocationArea;
    private String LocationBin;
    private String LocationPlace;

}
