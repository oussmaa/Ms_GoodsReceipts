package com.example.ms_goodsreceipts.Request;

import lombok.Data;

@Data
public class GoodsReceiptPosRequest {

    private Long  goodsReceiptid;
    private Double quantityBooked;
    private String description;
    private String article;
    private String location_area;
    private String location_bin;
    private String location_place;
}
