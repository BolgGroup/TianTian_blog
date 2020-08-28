package com.tiantian.service;

import com.tiantian.entity.SysMenu;

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
}
