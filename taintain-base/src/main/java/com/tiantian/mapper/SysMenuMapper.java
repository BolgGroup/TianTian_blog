package com.tiantian.mapper;

import com.tiantian.entity.SysMenu;
import com.tiantian.entity.SysRouter;
import com.tiantian.result.CommonMap;

import java.util.List;

/**
 * @author qi_bingo
 */
public interface SysMenuMapper {

    /**
     * 获取菜单
     * @return 菜单集合
     */
    List<SysMenu> getAllMenu();

    /**
     * 查询是否已有菜单编码
     * @param privCode
     * @return
     */
    int getMenuNumberByCode(String privCode);

    /**
     * 新增菜单
     * @param params
     * @return
     */
    Integer insertMenu(SysMenu params);

    /**
     * 修改菜单
     * @param params
     */
    void updateMenu(SysMenu params);
}
