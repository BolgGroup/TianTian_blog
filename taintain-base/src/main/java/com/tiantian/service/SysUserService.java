package com.tiantian.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tiantian.entity.SysRole;
import com.tiantian.entity.SysUser;

import java.io.IOException;
import java.util.List;
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
     * @param userId 用户ID
     * @return 角色集合
     */
    Set<String> getUserRolesSet(String userId);

    /**
     * 通过用户名获取用户权限集合
     *
     * @param userId 用户ID
     * @return 权限集合
     */
    Set<String> getUserPermissionsSet(String userId);

    /**
     * 查询所有用户集合
     *
     * @param page 分頁信息
     * @param userId 用户ID
     * @return 权限集合
     */
    IPage<SysUser> getUserList(Page<SysUser> page, String userId);

    /**
     * 重置用户密码和盐值
     * @param sysUser
     */
    void resetPwd(SysUser sysUser);

    /**
     * 根据用户id查询用户角色
     * @param userId
     * @return
     */
    List<SysRole> getUserRole(String userId);

    /**
     * 保存用户信息，包括用户角色
     * @param sysUser
     */
    void updateUser(SysUser sysUser) throws Exception;

    /**
     * 新增用户信息
     * @param sysUser
     */
    void insertUser(SysUser sysUser);
}
