package com.tiantian.mapper;

import com.tiantian.entity.SysRouter;

import java.util.List;

/**
 * @author qi_bingo
 */
public interface SysMenuMapper {

    /**
     * 获取菜单
     * @return 菜单集合
     */
    List<SysRouter> getAllMenu();
}
