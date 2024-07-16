package com.ibrahimmohurlu.notification_service.model;

import lombok.Data;


@Data
public class Package {


    private Long id;


    private String name;

    private String description;


    private double price;


    private int durationDays;


    private int listingAllowance;

}

