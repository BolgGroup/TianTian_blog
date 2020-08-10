package com.tiantian.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tiantian.entity.SysUser;
import org.apache.ibatis.annotations.Param;


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
}
