package com.example.ms_goodsreceipts.Request;

import lombok.Data;

@Data
public class OrderRequest {
    private Long id;

    private String description;
    private String status;

}
