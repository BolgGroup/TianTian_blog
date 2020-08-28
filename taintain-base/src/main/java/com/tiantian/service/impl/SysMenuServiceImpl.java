package com.tiantian.service.impl;

import com.tiantian.entity.SysMenu;
import com.tiantian.mapper.SysMenuMapper;
import com.tiantian.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author qi_bingo
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {

    private static final int ROOT = 10;

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public SysMenu getAllMenu() {
        List<SysMenu> routerList = sysMenuMapper.getAllMenu();
        Map<Integer, SysMenu> routerMap = new HashMap<Integer, SysMenu>();
        // 构建权限树
        for (SysMenu router : routerList) {
            routerMap.put(router.getPrivId(), router);
        }
        for (SysMenu router : routerList) {
            SysMenu parent = routerMap.get(router.getParentId());
            if (parent != null) {
                parent.getChildren().add(router);
                parent.setHasMenuChild(parent.isHasMenuChild() || router.getIsMenu() == 1);
            }
        }
        // 获取根节点
        SysMenu root = routerMap.get(ROOT);
        if (root == null) {
            return null;
        }
        if (ROOT == root.getPrivId()) {
            return root;
        }

        return getChild(root, ROOT);
    }

    @Override
    public int saveMenu(SysMenu menu) throws Exception{
        if (menu.getPrivId() == 0) {
            String privCode = menu.getCode();
            int count = sysMenuMapper.getMenuNumberByCode(privCode);
            if (count != 0) {
                throw new Exception("菜单编码不能重复！");
            }
            menu.setPrivId(sysMenuMapper.insertMenu(menu));
        } else {
            sysMenuMapper.updateMenu(menu);
        }

        // 保存后清除缓存
//		redisUtils.del("userPrivil*");
//		redisUtils.del("userRouter*");
        return menu.getPrivId();

    }

    /**
     * 迭代获取菜单
     *
     * @param parent
     * @param privId
     * @return
     */
    private static SysMenu getChild(SysMenu parent, int privId) {
        for (SysMenu child : parent.getChildren()) {
            if (child.getPrivId() == privId) {
                return child;
            }
            SysMenu grandChild = getChild(child, privId);
            if (grandChild != null) {
                return grandChild;
            }

        }
        return null;
    }
}
