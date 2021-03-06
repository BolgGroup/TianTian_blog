package com.tiantian.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;


/**
 * @author qi_bingo
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("SYS_USER")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("USER_ID")
    private String userId;

    @TableField("USER_NAME")
    private String userName;

    /**
     * 用户密码(MD5)
     */
    @TableField("PWD")
    private String pwd;

    /**
     * 用于接收前台传递的新密码，RSA加密，
     */
    private String password;

    /**
     * 加密盐值
     */
    @TableField("SALT")
    private String salt;

    /**
     * 10正常;20冻结;
     */
    @TableField("STATUS")
    private String status;

    /**
     * 用户角色
     */
    List<String> roles;

}
