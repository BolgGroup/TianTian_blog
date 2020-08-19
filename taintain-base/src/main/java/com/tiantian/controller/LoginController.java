package com.tiantian.controller;

import com.tiantian.annotaion.ResponseResult;
import com.tiantian.entity.SysUser;
import com.tiantian.enums.ResultCode;
import com.tiantian.result.BusinessException;
import com.tiantian.result.CommonMap;
import com.tiantian.service.SysUserService;
import com.tiantian.utils.security.RsaUtils;
import com.tiantian.utils.util.Guid;
import com.tiantian.utils.security.JwtUtil;
import com.tiantian.utils.security.PasswordUtil;
import com.tiantian.utils.util.RedisUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import static com.tiantian.constant.CommonConstant.JWT_LOGIN;
import static com.tiantian.constant.CommonConstant.TOKEN_LAST_TIME;


/**
 * @author qi_bingo
 */
@RestController
@ResponseResult
@Slf4j
@RequestMapping("base")
public class LoginController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 密钥文件位置
     */
    @Value("${security.keypath}")
    private String keyPath;

    @PostMapping("/login")
    @ApiOperation(value = "登录接口", notes = "根据用户名密码登录", httpMethod = "POST")
    public String login(@RequestBody CommonMap commonMap) {
        //1.校验验证码
        String loginCode = commonMap.get("imageCode");
        String key = commonMap.get("captchaKey");
        String cacheCode = (String) redisUtil.get(key);
        redisUtil.del(key);
        if (!loginCode.equalsIgnoreCase(cacheCode)) {
            log.warn("验证码错误,应为:" + cacheCode + ",实际:" + loginCode);
            throw new BusinessException(ResultCode.CAPTCHA_ERROR);
        }
        //2.校验用户是否有效
        SysUser sysUser = sysUserService.getUserById(commonMap.get("userId"));
        if (sysUser == null) {
            throw new BusinessException(ResultCode.LOGIN_NULL_ERROR);
        }
        //3.密码加密,后期有前端，则在前端加密
        String pwd = commonMap.get("pwd");
        String rsaPwd;
        try {
            //获取私钥,并解密
            rsaPwd = RsaUtils.decrypt(pwd, RsaUtils.getKey(keyPath).getPrivate());
        } catch (IOException | ClassNotFoundException ce) {
            ce.printStackTrace();
            throw new BusinessException(ResultCode.PRIVATE_KEY_ERROR);
        }catch (Exception e){
            throw new BusinessException(ResultCode.LOGIN_ERROR);
        }
        //4.解密后再次使用MD5加密，与数据库密码进行比对，从而达到双重加密的效果
        String mdPwd = PasswordUtil.encrypt(rsaPwd, sysUser.getSalt());
        if (!mdPwd.equals(sysUser.getPwd())) {
            throw new BusinessException(ResultCode.LOGIN_ERROR);
        }
        //5.登录成功，保存用户信息
        String secret = Guid.newGuid();
        redisUtil.set(JWT_LOGIN + sysUser.getUserId(), secret, TOKEN_LAST_TIME);
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
        try {
            return Base64.encodeBase64String(RsaUtils.getKey(keyPath).getPublic().getEncoded());
        } catch (Exception e) {
            throw new BusinessException(ResultCode.PUBLIC_KEY_ERROR);
        }
    }
}
