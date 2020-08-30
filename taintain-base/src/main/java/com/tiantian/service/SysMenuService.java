package com.tiantian.service;

import com.tiantian.entity.SysMenu;

import java.util.List;

/**
 * @author qi_bingo
 */
public interface SysMenuService {

    /**
     * 获取菜单
     * @return 菜单集合
     */
    SysMenu getAllMenu();

    /**
     * 新增、修改菜单
     * @param menu
     * @return
     * @exception Exception
     */
    int saveMenu(SysMenu menu) throws Exception;

    /**
     * 根据角色获取菜单
     * @param roleId
     * @return
     */
    List<SysMenu> roleMenu(String roleId);
}
