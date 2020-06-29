package com.tiantian.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseBody
@Slf4j
public class LoginController {

    @GetMapping("/login")
    @ResponseBody
    public String login(){
        return "login ok";
    }

    @GetMapping("/login2")
    @ResponseBody
    public String login2(){
        return "login2 ok";
    }

}
