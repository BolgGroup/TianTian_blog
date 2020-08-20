package com.tiantian.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 角色类
 * @author qi_bingo
 */
@Data
@TableName("SYS_ROLE")
public class SysRole {

    /**
     * 角色id
     */
    @TableId("ROLE_ID")
    private String roleId;

    /**
     * 角色编码
     */
    @TableField("ROLE_CODE")
    private String roleCode;
    /**
     * 角色名称
     */
    @TableField("ROLE_NAME")
    private String roleName;

    /**
     * 角色状态
     */
    @TableField("STATUS")
    private int status;

}
