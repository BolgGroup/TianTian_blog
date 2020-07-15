package com.tiantian.utils.token;

import lombok.Data;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author qi_bingo
 */
public class JWTToken implements AuthenticationToken{
    private static final long serialVersionUID = 9217639903967592166L;

    private String username;

    private String password;

    public JWTToken(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public Object getPrincipal() {
        return getUsername();
    }

    @Override
    public Object getCredentials() {
        return getPassword();
    }
}
