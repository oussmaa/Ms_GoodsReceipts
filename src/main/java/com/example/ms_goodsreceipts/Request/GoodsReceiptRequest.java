package com.example.ms_goodsreceipts.Request;

import lombok.Data;
import org.springframework.stereotype.Service;

@Data
public class GoodsReceiptRequest {

    private String description;
    private Long orderStockId;

}
