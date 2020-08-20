package com.tiantian.mapper;

import com.tiantian.entity.SysRouter;

import java.util.List;

/**
 * @author qi_bingo
 */
public interface SysRouterMapper {

    /**
     * 根据用户所有的角色，查询该用户菜单路由权限
     * @param allRoles
     * @return
     */
    List<SysRouter> getUserRoutersByRoles(List<String> allRoles);

    /**
     * 根据用户id查询该用户所有的角色
     * @param userId
     * @return
     */
    List<String> getUserRolesByUserId(String userId);
}
