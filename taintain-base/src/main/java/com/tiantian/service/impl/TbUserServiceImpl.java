package com.tiantian.service.impl;

import com.tiantian.entity.TbUser;
import com.tiantian.mapper.TbUserMapper;
import com.tiantian.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

/**
 * @author qi_bingo
 */
@Service
public class TbUserServiceImpl implements TbUserService {

    @Autowired
    private TbUserMapper tbUserMapper;

    @Override
    public TbUser getUserById(String userId) {
        return tbUserMapper.getUserById(userId);
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
