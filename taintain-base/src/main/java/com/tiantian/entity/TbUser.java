package com.tiantian.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;


/**
 * @author qi_bingo
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("TB_USER")
public class TbUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("USER_ID")
    private String userId;

    @TableField("USER_NAME")
    private String userName;

    /**
     * 最后登陆时间
     */
    @TableField("LAST_LOGIN_TIME")
    private Date lastLoginTime;

    /**
     * 用户密码(MD5)
     */
    @TableField("PWD")
    private String pwd;

    /**
     * 10正常;20冻结;
     */
    @TableField("STATUS")
    private String status;

    private String status_name;

    /**
     * 页面分格，1.默认，2.现代
     */
    @TableField("USER_STYLE")
    private String userStyle;

    @TableField("TYPE")
    private String type;

    private String ip;
}
