package com.tiantian.entity;

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

    private int id;

    private String name;

    private String path = "";

    /** 默认组件是layout */
    private String component;

    private String redirect;

    /** 当alias字段为@时，该路由作为父路由的别名使用 */
    private String alias;

    private String icon;

    private int parentId;

    private boolean isMenu;

    private boolean hidden;

    private int sortId;

    private int status;

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
