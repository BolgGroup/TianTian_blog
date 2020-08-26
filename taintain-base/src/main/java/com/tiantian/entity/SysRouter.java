package com.tiantian.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author qi_bingo
 */
@Data
@TableName("SYS_ROUTER")
public class SysRouter implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 权限ID
     */
    @TableId("PRIV_ID")
    private int privId;

    /**
     * 权限编码
     */
    @TableField("PRIV_CODE")
    private String name;

    /**
     * 上级权限ID
     */
    @TableField("PARENT_ID")
    private int parentId;

    /**
     * 前端菜单名
     */
    private SysRouterMeta meta;

    /**
     * url
     */
    @TableField("PRIV_URL")
    private String path;

    /**
     * 是否菜单
     */
    @TableField("IS_MENU")
    private boolean isMenu;

    /**
     * 排序
     */
    @TableField("SORT_ID")
    private int sortId;

    /**
     * 状态10启用20冻结
     */
    @TableField("STATUS")
    private int status;

    /**
     * 前端组件
     */
    @TableField("COMPONENT")
    private String component;

    /**
     * 重定向
     */
    @TableField("REDIRECT")
    private String redirect;

    /**
     * 是否隐藏路由
     */
    @TableField("HIDDEN")
    private int hidden;

    /**
     * 别名 为'@'时指向父路由
     */
    @TableField("ALIAS")
    private String alias;

    private boolean hasMenuChild = false;

    public boolean isHasMenuChild() {
        return hasMenuChild;
    }

    private List<SysRouter> children = new ArrayList<SysRouter>();

    private SysRouter parent;

    public boolean hasChild() {
        return children != null && !children.isEmpty();
    }

    @Override
    public SysRouter clone() {
        try {
            return (SysRouter) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

}
