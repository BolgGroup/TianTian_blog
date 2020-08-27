package com.tiantian.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SysMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    public static interface ACCESS {
        public final static int READONLY = 0;
        public final static int ALL = 1;
    }

    public static int ROOT = 10;
    public static int MENU_ROOT = 20;

    private int privId;

    private String code;

    private String name;

    private String url;

    private String remark;

    private int parentId;

    private int isMenu;

    private int sortId;

    private int status;

    private String component;

    private String redirect;

    private int hidden;

    private String alias;

    private int access;

    private boolean hasMenuChild = false;

    public boolean isHasMenuChild() {
        return hasMenuChild;
    }

    public void setHasMenuChild(boolean hasMenuChild) {
        this.hasMenuChild = hasMenuChild;
    }

    private List<SysMenu> children = new ArrayList<SysMenu>();

    private SysMenu parent;

    public int getPrivId() {
        return privId;
    }

    public void setPrivId(int privId) {
        this.privId = privId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getIsMenu() {
        return isMenu;
    }

    public void setIsMenu(int isMenu) {
        this.isMenu = isMenu;
    }

    public int getSortId() {
        return sortId;
    }

    public void setSortId(int sortId) {
        this.sortId = sortId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public int getHidden() {
        return hidden;
    }

    public void setHidden(int hidden) {
        this.hidden = hidden;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getAccess() {
        return access;
    }

    public void setAccess(int readonly) {
        this.access = readonly;
    }

    public List<SysMenu> getChildren() {
        return children;
    }

    public void setChildren(List<SysMenu> children) {
        this.children = children;
    }

    public SysMenu getParent() {
        return parent;
    }

    public void setParent(SysMenu parent) {
        this.parent = parent;
    }

    public boolean hasChild() {
        if (children == null) {
            return false;
        }
        return !children.isEmpty();
    }

    public SysMenu clone() {
        try {
            return (SysMenu) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

}
