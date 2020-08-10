package com.tiantian.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tiantian.annotaion.ResponseResult;
import com.tiantian.entity.SysUser;
import com.tiantian.enums.ResultCode;
import com.tiantian.result.BusinessException;
import com.tiantian.service.SysUserService;
import com.tiantian.utils.util.PasswordUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author qi_bingo
 */
@RestController
@Slf4j
@ResponseResult
@Api(tags = "系统用户接口类")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @GetMapping("/userList")
    @ApiOperation(value = "用户列表", notes = "根据用户id查询信息", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "页容量", dataType = "string", paramType = "query"),
    })
    public IPage<SysUser> userList(String userId, String current, String size){
        Page<SysUser> page = new Page<SysUser>(Long.parseLong(current), Long.parseLong(size));
        return sysUserService.getUserList(page, userId);
    }

    @PutMapping("/resetPwd")
    @ApiOperation(value = "重置用户密码", notes = "根据用户id查询信息", httpMethod = "PUT")
    public void resetPwd(String userId){
        try {
            SysUser user = (SysUser)SecurityUtils.getSubject().getPrincipal();
            if ((user.getUserId()).equals(userId)){
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
            sysUser.setUserId(userId);
            sysUserService.resetPwd(sysUser);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(ResultCode.RESET_PASSWORD_ERROR);
        }
    }
}
