package com.example.ms_goodsreceipts.Request;

import lombok.Data;

import java.util.List;

@Data
public class ShippmentRequest {

    private List<Long> picking;
    private String receiptDate;
    private String receiptTime;
    private String receiptAddress;
    private String receiptCity;
    private String receiptState;
    private String receiptZip;
    private String receiptCountry;
    private String receiptPhone;
    private String receiptEmail;
    private String receiptName;





}
