package com.example.ms_goodsreceipts.Request;

import lombok.Data;

@Data
public class CustomerRequest {


    private String name;
    private String address;

    private String phone;
    private String email;
    private String city;
    private String state;
    private String zip;
    private String country;
    private int allCost;

}
