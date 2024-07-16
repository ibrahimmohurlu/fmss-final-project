package com.ibrahimmohurlu.payment_service.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {

    private Long id;


    private String email;


    private String password;


    private LocalDateTime createdAt;


    private LocalDateTime updatedAt;

}
