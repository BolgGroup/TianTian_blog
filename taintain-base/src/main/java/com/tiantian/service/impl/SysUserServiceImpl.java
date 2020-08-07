package com.tiantian.service.impl;

import com.tiantian.entity.SysUser;
import com.tiantian.mapper.SysUserMapper;
import com.tiantian.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
