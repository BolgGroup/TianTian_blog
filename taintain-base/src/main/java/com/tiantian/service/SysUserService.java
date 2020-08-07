package com.tiantian.service;

import com.tiantian.entity.SysUser;

import java.util.Set;

/**
 * @author qi_bingo
 */
public interface SysUserService {

    /**
     * 通过用户id获取用户信息
     *
     * @param userId 用户名
     * @return 用户信息
     */
    SysUser getUserById(String userId);

    /**
     * 通过用户id获取用户角色集合
     *
     * @param userId 用户名
     * @return 角色集合
     */
    Set<String> getUserRolesSet(String userId);

    /**
     * 通过用户名获取用户权限集合
     *
     * @param userId 用户名
     * @return 权限集合
     */
    Set<String> getUserPermissionsSet(String userId);
}
