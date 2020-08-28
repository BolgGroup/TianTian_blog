package com.tiantian.controller;

import com.tiantian.annotaion.ResponseResult;
import com.tiantian.entity.SysMenu;
import com.tiantian.enums.ResultCode;
import com.tiantian.result.BusinessException;
import com.tiantian.service.SysMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private SysMenuService sysMenuService;

    @GetMapping("/tree")
    public List<SysMenu> getAllMenu(){
        List<SysMenu> routerList = new ArrayList<SysMenu>();
        SysMenu router = sysMenuService.getAllMenu();
        routerList = (router == null) ? null : router.getChildren();
        return routerList;
    }

    /**
     * 菜单保存
     *
     * @param menu
     */
    @PostMapping("save")
    public void saveMenu(@RequestBody SysMenu menu) {
        try {
            int privilegeId = sysMenuService.saveMenu(menu);
            log.info("privilegeId = " + privilegeId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(ResultCode.SYSTEM_INTERNAL_ERROR);
        }
    }
}
