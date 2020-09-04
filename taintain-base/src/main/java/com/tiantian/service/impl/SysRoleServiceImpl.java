package com.tiantian.service.impl;

import com.tiantian.entity.SysRole;
import com.tiantian.mapper.SysRoleMapper;
import com.tiantian.result.CommonMap;
import com.tiantian.service.SysRoleService;
import com.tiantian.utils.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qi_bingo
 */
@Service
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
        if (!StringUtil.isEmpty(commonMap.get("routes"))){
            String[] routes;
            StringBuilder route = new StringBuilder(commonMap.get("routes"));
            //赋予权限菜单，如未给任何菜单，则默认给两个最上级菜单,否则前端路由加载报错
            if ((route.toString().split(",").length > 1)){
                routes = route.substring(1, route.length() - 1).split(",");
            }else {
                routes = new String[2];
                routes[0] = "10";
                routes[1] = "20";
            }
            //新增角色菜单
            sysRoleMapper.insertMenu(commonMap,routes);
        }
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
        if (!StringUtil.isEmpty(commonMap.get("routes"))){
            StringBuilder route = new StringBuilder(commonMap.get("routes"));
            String[] routes = route.substring(1, route.length() - 1).split(",");
            //新增角色菜单
            sysRoleMapper.insertMenu(commonMap,routes);
        }
    }

    @Override
    public void openRole(CommonMap commonMap) {
        int status = 1;
        sysRoleMapper.editRoleStatus(commonMap.get("roleId"), String.valueOf(status));
    }
}
