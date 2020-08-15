package com.tiantian.controller;

import com.tiantian.annotaion.ResponseResult;
import com.tiantian.entity.SysUser;
import com.tiantian.enums.ResultCode;
import com.tiantian.result.BusinessException;
import com.tiantian.result.CommonMap;
import com.tiantian.service.SysUserService;
import com.tiantian.utils.util.Guid;
import com.tiantian.utils.util.JwtUtil;
import com.tiantian.utils.util.PasswordUtil;
import com.tiantian.utils.util.RedisUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.tiantian.constant.CommonConstant.JWT_LOGIN;
import static com.tiantian.constant.CommonConstant.TOKEN_LAST_TIME;


/**
 * @author qi_bingo
 */
@RestController
@ResponseResult
@RequestMapping("base")
public class LoginController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private RedisUtil redisUtil;

    // 密钥文件位置
//    @Value("${security.keypath}")
//    private String keyPath;

    @PostMapping("/login")
    @ApiOperation(value = "登录接口", notes = "根据用户名密码登录", httpMethod = "POST")
    public String login(@RequestBody CommonMap commonMap) {
            //1. 校验用户是否有效
            SysUser sysUser = sysUserService.getUserById(commonMap.get("userId"));
            if (sysUser == null) {
                throw new BusinessException(ResultCode.LOGIN_NULL_ERROR);
            }
            //密码加密,后期有前端，则在前端加密
            String password = PasswordUtil.encrypt(commonMap.get("pwd"), sysUser.getSalt());
            if (!password.equals(sysUser.getPwd())) {
                throw new BusinessException(ResultCode.LOGIN_ERROR);
            }
            //2. 登录成功，保存用户信息
            String secret = Guid.newGuid();
            redisUtil.set(JWT_LOGIN + sysUser.getUserId(), secret,TOKEN_LAST_TIME);
            return JwtUtil.sign(sysUser, secret, TOKEN_LAST_TIME);
    }

    @PostMapping("/logout")
    public void logout() {
        // 如果已经登录，则跳转到管理首页
        SysUser coreUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        if (coreUser != null) {
            // 用户登出日志
            redisUtil.del(JWT_LOGIN + coreUser.getUserId());
            SecurityUtils.getSubject().logout();
        }
    }

    @GetMapping(value = "/user/info")
    public SysUser getUser() {
        // shiro获得用户对象
        return (SysUser) SecurityUtils.getSubject().getPrincipal();
    }

    @GetMapping(value = "/publickey")
    public String getPublicKey() {
        // 传递RSA公钥
//        String publicKey = RSAUtil.getPublicKey(keyPath);
        return "";
    }
}
