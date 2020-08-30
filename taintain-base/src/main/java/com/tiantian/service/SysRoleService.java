package com.tiantian.service;

import com.tiantian.entity.SysRole;
import com.tiantian.result.CommonMap;

import java.util.List;

/**
 * @author qi_bingo
 */
public interface SysRoleService {

    /**
     * 查询所有角色信息
     * @return 角色集合
     */
    List<SysRole> getAllRoles();

    /**
     * 禁用角色
     * @param roleId
     */
    void deleteRole(String roleId);

    /**
     * 修改角色信息
     * @param commonMap
     */
    void updateRole(CommonMap commonMap);

    /**
     * 新增角色信息
     * @param commonMap
     */
    void insertRole(CommonMap commonMap);

    /**
     * 启用角色
     * @param commonMap
     */
    void openRole(CommonMap commonMap);
}
