package com.tiantian.mapper;

import com.tiantian.entity.SysRole;
import com.tiantian.result.CommonMap;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author qi_bingo
 */
public interface SysRoleMapper {

    /**
     * 查询所有角色信息
     * @return 角色集合
     */
    List<SysRole> getAllRoles();

    /**
     * 禁用角色
     * @param roleId
     * @param status
     */
    void editRoleStatus(@Param("roleId") String roleId,@Param("status") String status);

    /**
     * 修改角色信息
     * @param commonMap
     */
    void updateRole(CommonMap commonMap);

    /**
     * 删除角色菜单
     * @param commonMap
     */
    void deleteMenu(CommonMap commonMap);

    /**
     * 重置角色菜单
     * @param commonMap
     * @param routes
     */
    void insertMenu(@Param("commonMap") CommonMap commonMap,@Param("routes") String[] routes);

    /**
     * 新增角色信息
     * @param sysRole
     * @return
     */
    int insertRole(SysRole sysRole);
}
