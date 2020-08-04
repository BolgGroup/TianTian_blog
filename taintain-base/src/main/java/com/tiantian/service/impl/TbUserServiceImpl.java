package com.tiantian.service.impl;

import com.tiantian.entity.TbUser;
import com.tiantian.service.TbUserService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author qi_bingo
 */
@Service
public class TbUserServiceImpl implements TbUserService {

    @Override
    public TbUser getUserByName(String username) {
        return null;
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
