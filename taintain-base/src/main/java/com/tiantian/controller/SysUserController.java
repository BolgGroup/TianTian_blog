package com.tiantian.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tiantian.annotaion.ResponseResult;
import com.tiantian.entity.SysRole;
import com.tiantian.entity.SysUser;
import com.tiantian.enums.ResultCode;
import com.tiantian.result.BusinessException;
import com.tiantian.result.CommonMap;
import com.tiantian.service.SysUserService;
import com.tiantian.utils.security.PasswordUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author qi_bingo
 */
@RestController
@Slf4j
@ResponseResult
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @GetMapping("/userList")
    @ApiOperation(value = "用户列表", notes = "根据用户id查询信息", httpMethod = "GET")
    public IPage<SysUser> userList(String userId, String current, String size){
        Page<SysUser> page = new Page<SysUser>(Long.parseLong(current), Long.parseLong(size));
        return sysUserService.getUserList(page, userId);
    }

    @PutMapping("/resetPwd")
    @ApiOperation(value = "重置用户密码", notes = "根据用户id查询信息", httpMethod = "PUT")
    public void resetPwd(@RequestBody SysUser users){
        try {
            SysUser user = (SysUser)SecurityUtils.getSubject().getPrincipal();
            if ((user.getUserId()).equals(users.getUserId())){
                throw new BusinessException(ResultCode.RESET_PASSWORD_ERROR);
            }
            //获取盐值
            String salt = String.valueOf(PasswordUtil.getSalt());
            //加密后密码
            String password = PasswordUtil.encrypt("tt@123_blog", salt);
            //重置密码
            SysUser sysUser = new SysUser();
            sysUser.setPwd(password);
            sysUser.setSalt(salt);
            sysUser.setUserId(users.getUserId());
            sysUserService.resetPwd(sysUser);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(ResultCode.RESET_PASSWORD_ERROR);
        }
    }

    @GetMapping("/userRole")
    @ApiOperation(value = "根据用户id查询角色", notes = "根据用户id查询角色", httpMethod = "GET")
    public List<SysRole> getUserRole(String userId){
        try {
            return sysUserService.getUserRole(userId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(ResultCode.USER_ROLE_SELECT_ERROR);
        }
    }

    @PostMapping("/insertUser")
    @ApiOperation(value = "保存用户信息", notes = "新增用户信息", httpMethod = "POST")
    public void insertUser(@RequestBody SysUser sysUser){
        try {
            sysUserService.insertUser(sysUser);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(ResultCode.USER_SAVE_ERROR);
        }
    }
}
