package com.tiantian.controller;

import com.tiantian.annotaion.ResponseResult;
import com.tiantian.entity.SysRouter;
import com.tiantian.utils.util.RouterUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<SysRouter> getAllMenu(){
        List<SysRouter> routerList = new ArrayList<SysRouter>();
        SysRouter router = RouterUtil.getPrivil(0);
        routerList = (router == null) ? null : router.getChildren();
        return routerList;
    }
}
