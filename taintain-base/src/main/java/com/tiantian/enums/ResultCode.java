package com.tiantian.enums;

/**
 * 返回状态码枚举类
 */
public enum ResultCode {

    /* 成功状态码 */
    SUCCESS(1, "成功"),
    /* 1000~1999区间表示参数错误 */
    PARAM_IS_INVALID(1001, "参数无效"),
    /* 2000~2999区间表示用户错误 */
    CAPTCHA_ERROR(2002, "验证码错误"), LOGIN_ERROR(2003, "用户名或密码错误"), LOGIN_NULL_ERROR(2004, "账号不存在"),
    /* 3000~3999区间表示接口错误 */
    SYSTEM_INTERNAL_ERROR(3001, "接口内部错误"),
    /* 4000~4999区间表示文件错误 */
    FILE_IS_NULL_ERROR(4001, "上传文件为空"), FILE_UPLOAD_ERROR(4002, "文件上传错误");


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