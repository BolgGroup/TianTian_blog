package com.tiantian.enums;

/**
 * 返回状态码枚举类
 *
 * @author qi_bingo
 */
public enum ResultCode {

    /* 成功状态码 */
    SUCCESS(1, "成功"),
    PARAM_IS_INVALID(2, "参数无效"),
    SYSTEM_INTERNAL_ERROR(9999, "接口内部错误"),
    PUBLIC_KEY_ERROR(3, "获取公钥异常"),
    PRIVATE_KEY_ERROR(4, "获取私钥异常"),
    RSA_DECRYPT_ERROR(5, "RSA解密异常"),
    /* 100~199区间表示系统 */
    CAPTCHA_ERROR(100, "验证码错误"), LOGIN_ERROR(101, "用户名或密码错误"), LOGIN_NULL_ERROR(102, "账号不存在"),
    /* 200~299区间表示应用操作错误 */
    FILE_IS_NULL_ERROR(200, "上传文件为空"), FILE_UPLOAD_ERROR(201, "文件上传错误"),
    /* 300~399区间表示业务错误 */
    USER_ROLE_SELECT_ERROR(301, "查询用户对应角色失败"), RESET_PASSWORD_ERROR(302, "不可重置当前用户"),
    USER_SAVE_ERROR(303, "查询用户对应角色失败"), USER_PWD_EQUAL_ERROR(304, "旧密码错误");


    private Integer code;

    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

}