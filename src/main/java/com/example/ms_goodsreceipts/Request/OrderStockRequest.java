package com.example.ms_goodsreceipts.Request;

import lombok.Data;

@Data
public class OrderStockRequest {

    private Long id;

    private String Description;
    private Double QuantityNeeded;
    private Long supplier_id;
    private String Articel;
}
