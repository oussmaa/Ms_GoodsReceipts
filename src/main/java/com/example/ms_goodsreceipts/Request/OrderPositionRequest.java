package com.example.ms_goodsreceipts.Request;

import lombok.Data;

@Data
public class OrderPositionRequest {
    private Long orderId;
    private String article;
    private Long id;
private Double quantity;
private String description;
private String locationarea;

}
