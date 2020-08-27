package com.tiantian.controller;

import com.tiantian.annotaion.ResponseResult;
import com.tiantian.entity.SysMenu;
import com.tiantian.entity.SysRouter;
import com.tiantian.enums.ResultCode;
import com.tiantian.result.BusinessException;
import com.tiantian.result.CommonMap;
import com.tiantian.utils.util.RouterUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qi_bingo
 */
@RestController
@ResponseResult
@Slf4j
@RequestMapping("menu")
public class SysMenuController {

    @GetMapping("/tree")
    public List<SysMenu> getAllMenu(){
        List<SysMenu> routerList = new ArrayList<SysMenu>();
        SysMenu router = RouterUtil.getMenu();
        routerList = (router == null) ? null : router.getChildren();
        return routerList;
    }

    /**
     * 菜单保存
     *
     * @param params
     */
    @PostMapping("save")
    public void save(@RequestBody CommonMap params) {
        try {
//            int privilegeId = RouterUtil.save(params);
//            log.info("privilegeId = " + privilegeId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(ResultCode.SYSTEM_INTERNAL_ERROR);
        }
    }
}
