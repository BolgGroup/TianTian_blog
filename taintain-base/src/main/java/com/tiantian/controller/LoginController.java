package com.tiantian.controller;

import com.tiantian.annotaion.ResponseResult;
import com.tiantian.entity.SysUser;
import com.tiantian.enums.ResultCode;
import com.tiantian.result.BusinessException;
import com.tiantian.service.SysUserService;
import com.tiantian.utils.token.JwtToken;
import com.tiantian.utils.util.Guid;
import com.tiantian.utils.util.JwtUtil;
import com.tiantian.utils.util.RedisUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;

/**
 * @author qi_bingo
 */
@RestController
@Slf4j
public class LoginController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private RedisUtil redisUtil;

    // token持续时间（秒）
    private static final int tokenLastTime = 1800;

    @GetMapping("/login")
    @ResponseBody
    @ResponseResult
    @ApiOperation(value = "登录接口", notes = "根据用户名密码登录", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "账号", dataType = "string", required = true, paramType = "query"),
            @ApiImplicitParam(name = "imageCode", value = "验证码", dataType = "string", required = true, paramType = "query"),
            @ApiImplicitParam(name = "pwd", value = "密码", dataType = "string", required = true, paramType = "query") })
    public String login(String userId, String imageCode, String pwd) {
        try {
            //1. 校验用户是否有效
            SysUser sysUser = sysUserService.getUserById(userId);
            if (sysUser == null) {
                throw new BusinessException(ResultCode.LOGIN_NULL_ERROR);
            }
            String hex = DigestUtils.md5DigestAsHex(sysUser.getPwd().getBytes(StandardCharsets.UTF_8));

            //2. 登录成功，保存用户信息
            String secret = Guid.newGuid();
            redisUtil.set("JWT_admin", secret);
            return JwtUtil.sign(sysUser, secret, tokenLastTime);

        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(e.getMessage());
        }
    }

}
