package com.ibrahimmohurlu.package_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/packages")
public class PackageController {
    @GetMapping
    public String get() {
        return "hello from package service";
    }
}
