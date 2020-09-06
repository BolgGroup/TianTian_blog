package com.tiantian.service.impl;

import com.tiantian.entity.SysRole;
import com.tiantian.mapper.SysRoleMapper;
import com.tiantian.result.CommonMap;
import com.tiantian.service.SysRoleService;
import com.tiantian.utils.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author qi_bingo
 */
@Service
@Transactional
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public List<SysRole> getAllRoles() {
        return sysRoleMapper.getAllRoles();
    }

    @Override
    public void deleteRole(String roleId) {
        int status = 0;
        sysRoleMapper.editRoleStatus(roleId, String.valueOf(status));
    }

    @Override
    public void updateRole(CommonMap commonMap) {
        //删除角色菜单
        sysRoleMapper.deleteMenu(commonMap);
        String rou = commonMap.get("routes").substring(1);
        rou = rou.substring(0, rou.length() - 1);
        String[] routes;
        if (!StringUtil.isEmpty(rou)) {
            //赋予权限菜单，如未给任何菜单，则默认给两个最上级菜单,否则前端路由加载报错
            routes = rou.split(",");
        } else {
            routes = new String[2];
            routes[0] = "10";
            routes[1] = "20";
        }
        sysRoleMapper.insertMenu(commonMap, routes);
        //修改角色信息
        sysRoleMapper.updateRole(commonMap);
    }

    @Override
    public void insertRole(CommonMap commonMap) {
        //新增角色并返回主键
        SysRole sysRole = new SysRole();
        sysRole.setRoleCode(commonMap.get("roleCode"));
        sysRole.setRoleName(commonMap.get("roleName"));
        sysRoleMapper.insertRole(sysRole);
        commonMap.put("roleId", sysRole.getRoleId());
        String rou = commonMap.get("routes").substring(1);
        rou = rou.substring(0, rou.length() - 1);
        String[] routes;
        if (!StringUtil.isEmpty(rou)) {
            routes = rou.split(",");
            //新增角色菜单
        }else {
            routes = new String[2];
            routes[0] = "10";
            routes[1] = "20";
        }
        sysRoleMapper.insertMenu(commonMap, routes);
    }

    @Override
    public void openRole(CommonMap commonMap) {
        int status = 1;
        sysRoleMapper.editRoleStatus(commonMap.get("roleId"), String.valueOf(status));
    }
}
