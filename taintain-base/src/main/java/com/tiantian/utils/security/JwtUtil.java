package com.tiantian.utils.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.tiantian.constant.CommonConstant;
import com.tiantian.entity.SysUser;
import com.tiantian.utils.util.CommonUtils;
import com.tiantian.utils.util.SpringContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;

/**
 * JWT工具类
 **/
public class JwtUtil {

    // 过期时间30分钟
    public static final long EXPIRE_TIME = 30 * 60 * 1000;

    /**
     * 校验token是否正确
     *
     * @param token  密钥
     * @param secret 用户的密码
     * @return 是否正确
     */
    public static boolean verify(String token, String id, String secret) {
        try {
            // 根据密码生成JWT效验器
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).withClaim("id", id).build();
            // 效验TOKEN
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户id
     */
    public static String getUserId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("id").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 生成签名,5min后过期
     *
     * @param id 用户名
     * @param secret   用户的密码
     * @return 加密的token
     */
    public static String sign(String id, String secret) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        // 附带username信息
        return JWT.create().withClaim("id", id).withExpiresAt(date).sign(algorithm);

    }

    /**
     * 生成签名,expireTime后过期
     *
     * @param user 用户
     * @param secret  密码
     * @param time     过期时间s
     * @return 加密的token
     */
    public static String sign(SysUser user, String secret, long time) {
        try {
            Date date = new Date(System.currentTimeMillis() + time * 1000);
            // String[] roles = user.getRoleList().toArray(new
            // String[user.getRoleList().size()]);
            // JWT中存放的信息有：userid
            return JWT.create().withClaim("id", user.getUserId())
                    /*
                     * .withClaim("dept", user.getDeptCode()) .withClaim("name", user.getUserName())
                     * .withClaim("dept", user.getDeptCode()) .withArrayClaim("roles", roles)
                     */
                    .withExpiresAt(date).withIssuedAt(new Date()).sign(JwtUtil.getAlgorithm(secret));
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    private static Algorithm getAlgorithm(String secret) {
        /*
         * KeyPair keyPair = RsaUtil.getKeyPair(keyPath); return
         * Algorithm.RSA256((RSAPublicKey) keyPair.getPublic(), (RSAPrivateKey)
         * keyPair.getPrivate());
         */
        return Algorithm.HMAC256(secret);
    }

    /**
     * 根据request中的token获取用户账号
     *
     * @param request
     * @return
     * @throws Exception
     */
    public static String getUserNameByToken(HttpServletRequest request) throws Exception {
        String accessToken = request.getHeader(CommonConstant.ACCESS_TOKEN);
        String username = getUserId(accessToken);
        if (CommonUtils.isEmpty(username)) {
            throw new Exception("未获取到用户");
        }
        return username;
    }

    /**
     * token是否过期
     *
     * @return true：过期
     */
    public static boolean isTokenExpired(String token) {
        Date now = Calendar.getInstance().getTime();
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getExpiresAt().before(now);
    }

    /**
     * 从session中获取变量
     *
     * @param key
     * @return
     */
    public static String getSessionData(String key) {
        //${myVar}%
        //得到${} 后面的值
        String moshi = "";
        if (key.indexOf("}") != -1) {
            moshi = key.substring(key.indexOf("}") + 1);
        }
        String returnValue = null;
        if (key.contains("#{")) {
            key = key.substring(2, key.indexOf("}"));
        }
        if (CommonUtils.isNotEmpty(key)) {
            HttpSession session = SpringContextUtils.getHttpServletRequest().getSession();
            returnValue = (String) session.getAttribute(key);
        }
        //结果加上${} 后面的值
        if (returnValue != null) {
            returnValue = returnValue + moshi;
        }
        return returnValue;
    }

    public static void main(String[] args) {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NjUzMzY1MTMsInVzZXJuYW1lIjoiYWRtaW4ifQ.xjhud_tWCNYBOg_aRlMgOdlZoWFFKB_givNElHNw3X0";
        System.out.println(JwtUtil.getUserId(token));
    }
}
