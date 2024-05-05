package com.tds.VMonClick.VMonClick.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api")
public class Root {

    @GetMapping("hello")
    public String hello() {
        return "hello";
    }
}
