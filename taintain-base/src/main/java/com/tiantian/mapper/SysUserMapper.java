package com.tiantian.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tiantian.entity.SysRole;
import com.tiantian.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author qi_bingo
 */
public interface SysUserMapper {

    /**
     * @param userId 用户ID
     * @return SysUser 用户信息
     */
    SysUser getUserById(String userId);

    /**
     * @param page   分頁信息
     * @param userId 用户ID
     * @return List<SysUser> 用户集合
     */
    IPage<SysUser> getUserList(Page<SysUser> page, @Param("userId") String userId);

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
     * 新增用户信息
     * @param sysUser
     */
    void insertUser(SysUser sysUser);

    /**
     * 新增用户角色
     * @param sysUser
     */
    void insertUserRoles(SysUser sysUser);
}
