package com.tiantian.controller;

import com.tiantian.utils.token.JWTToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qi_bingo
 */
@RestController
@Slf4j
public class LoginController {

    @GetMapping("/login")
    @ResponseBody
    public String login(){
        String username = "admin";
        String password = "123456";
        JWTToken jwtToken = new JWTToken(username,password);
        SecurityUtils.getSubject().login(jwtToken);
        return "login ok";
    }

    @GetMapping("/login2")
    @ResponseBody
    public String login2(){
        return "login2 ok";
    }

}
