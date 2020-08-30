package com.tiantian.controller;

import com.tiantian.annotaion.ResponseResult;
import com.tiantian.entity.SysRole;
import com.tiantian.result.CommonMap;
import com.tiantian.service.SysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseResult
@Slf4j
@RequestMapping("/role")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @GetMapping("/roles")
    public List<SysRole> getAllRoles() {
        return sysRoleService.getAllRoles();
    }

    @DeleteMapping("/delete/{roleId}")
    public void deleteRole(@PathVariable("roleId") String roleId) {
        sysRoleService.deleteRole(roleId);
    }

    @PutMapping("/updateRole")
    public void updateRole(@RequestBody CommonMap commonMap) {
        sysRoleService.updateRole(commonMap);
    }

    @PostMapping("/insertRole")
    public void insertRole(@RequestBody CommonMap commonMap) {
        sysRoleService.insertRole(commonMap);
    }

    @PostMapping("/openRole")
    public void openRole(@RequestBody CommonMap commonMap) {
        sysRoleService.openRole(commonMap);
    }
}
