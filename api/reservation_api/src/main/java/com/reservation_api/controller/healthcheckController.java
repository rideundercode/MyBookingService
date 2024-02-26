package com.reservation_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class healthcheckController {
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping("/health")
    public String healthCheck() {
        return "alive";
    }
}
