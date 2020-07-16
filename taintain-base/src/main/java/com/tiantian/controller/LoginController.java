package com.tiantian.controller;

import com.tiantian.utils.token.JWTToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
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
        try {
            String username = "admin";
            String password = "123456";
            JWTToken jwtToken = new JWTToken(username,password);
            SecurityUtils.getSubject().login(jwtToken);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        return "login ok";
    }

    //注解验角色和权限
    @RequestMapping("/index")
    public String index() {
        return "index!";
    }

}
