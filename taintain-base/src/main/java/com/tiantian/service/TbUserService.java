package com.tiantian.service;

import com.tiantian.entity.TbUser;

import java.util.Set;

/**
 * @author qi_bingo
 */
public interface TbUserService {

    TbUser getUserByName(String username);

    /**
     * 通过用户名获取用户角色集合
     *
     * @param username 用户名
     * @return 角色集合
     */
    Set<String> getUserRolesSet(String username);

    /**
     * 通过用户名获取用户权限集合
     *
     * @param username 用户名
     * @return 权限集合
     */
    Set<String> getUserPermissionsSet(String username);
}
