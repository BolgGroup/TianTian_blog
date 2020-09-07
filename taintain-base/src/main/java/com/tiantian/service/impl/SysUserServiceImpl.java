package com.tiantian.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tiantian.entity.SysRole;
import com.tiantian.entity.SysUser;
import com.tiantian.enums.ResultCode;
import com.tiantian.mapper.SysUserMapper;
import com.tiantian.result.BusinessException;
import com.tiantian.service.SysUserService;
import com.tiantian.utils.security.PasswordUtil;
import com.tiantian.utils.security.RsaUtils;
import com.tiantian.utils.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * @author qi_bingo
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    /**
     * 密钥文件位置
     */
    @Value("${security.keypath}")
    private String keyPath;

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
    public void updateUser(SysUser sysUser) throws Exception {
        // 如果传递参数存在用户ID，则表明是修改操作，否则为新增
        //获取私钥,并解密
        if (!StringUtil.isEmpty(sysUser.getPwd())) {
            // 旧密码解密
            String pwd = RsaUtils.decrypt(sysUser.getPwd(), RsaUtils.getKey(keyPath).getPrivate());
            // 比对数据库盐加密密码
            SysUser user = sysUserMapper.getUserById(sysUser.getUserId());
            String mdPwd = PasswordUtil.encrypt(pwd, user.getSalt());
            if (!user.getPwd().equals(mdPwd)) {
                throw new BusinessException(ResultCode.USER_PWD_EQUAL_ERROR);
            }
            // 新密码RSA解密
            String password = RsaUtils.decrypt(sysUser.getPassword(), RsaUtils.getKey(keyPath).getPrivate());
            //获取盐值
            String salt = String.valueOf(PasswordUtil.getSalt());
            //加密后新密码
            String newPwd = PasswordUtil.encrypt(password, salt);
            sysUser.setPwd(newPwd);
            sysUser.setSalt(salt);
        }
        sysUserMapper.updateUser(sysUser);
        // 删除用户已有角色
        sysUserMapper.deleteUserRoles(sysUser.getUserId());
        // 重新添加用户角色
        sysUserMapper.updateUserRoles(sysUser);
    }

    @Override
    public void insertUser(SysUser sysUser) {
        //获取盐值
        String salt = String.valueOf(PasswordUtil.getSalt());
        //加密后密码
        String password = PasswordUtil.encrypt("tt@123_blog", salt);
        sysUser.setPwd(password);
        sysUser.setSalt(salt);
        sysUserMapper.insertUser(sysUser);
        sysUserMapper.updateUserRoles(sysUser);
    }
}
