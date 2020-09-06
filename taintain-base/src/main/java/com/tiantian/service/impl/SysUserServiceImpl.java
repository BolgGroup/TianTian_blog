package com.tiantian.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tiantian.entity.SysRole;
import com.tiantian.entity.SysUser;
import com.tiantian.mapper.SysUserMapper;
import com.tiantian.service.SysUserService;
import com.tiantian.utils.security.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author qi_bingo
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser getUserById(String userId) {
        return sysUserMapper.getUserById(userId);
    }

    @Override
    public Set<String> getUserRolesSet(String username) {
        return null;
    }

    @Override
    public Set<String> getUserPermissionsSet(String username) {
        return null;
    }

    @Override
    public IPage<SysUser> getUserList(Page<SysUser> page, String userId) {
        return sysUserMapper.getUserList(page, userId);
    }

    @Override
    public void resetPwd(SysUser sysUser) {
        sysUserMapper.resetPwd(sysUser);
    }

    @Override
    public List<SysRole> getUserRole(String userId) {
        return sysUserMapper.getUserRole(userId);
    }

    @Override
    public void insertUser(SysUser sysUser) {
        // 如果传递参数存在用户ID，则表明是修改操作，否则为新增
        //获取盐值
        String salt = String.valueOf(PasswordUtil.getSalt());
        //加密后密码
        String password = PasswordUtil.encrypt("tt@123_blog", salt);
        //保存加密后得密码
        sysUser.setPwd(password);
        sysUserMapper.insertUser(sysUser);
        sysUserMapper.insertUserRoles(sysUser);
    }
}
