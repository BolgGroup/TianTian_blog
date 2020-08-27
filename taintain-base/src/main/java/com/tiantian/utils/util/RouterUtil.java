package com.tiantian.utils.util;

import com.tiantian.entity.SysRouter;
import com.tiantian.entity.SysUser;
import com.tiantian.mapper.SysRouterMapper;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * 获取权限树的工具类
 *
 * @author qi_bingo
 */
@Component
public class RouterUtil {

	@Autowired
	private SysRouterMapper sysRouterMapper;

	private static RouterUtil routerUtil;

	private static final int ROOT = 10;

	/** 为了让spring注入静态类而做的处理 */
	@PostConstruct
	public void init() {
		routerUtil = this;
		routerUtil.sysRouterMapper = this.sysRouterMapper;
	}

	/**
	 * 查询当前登录用户的所有角色权限
	 * @return 菜单列表
	 */
	public static SysRouter getPrivil(int parentId) {
		List<SysRouter> routerList = null;
		if (parentId != 0){
			// 从数据库中获得当前用户的所有权限
			routerList = routerUtil.sysRouterMapper.getUserRoutersByRoles(getAllRoles());
		}else {
			routerList = routerUtil.sysRouterMapper.getAllMenu();
		}
		Map<Integer, SysRouter> routerMap = new HashMap<Integer, SysRouter>();
		// 构建权限树
		for (SysRouter router : routerList) {
			routerMap.put(router.getPrivId(), router);
		}
		for (SysRouter router : routerList) {
			SysRouter parent = routerMap.get(router.getParentId());
			if (parent != null) {
				parent.getChildren().add(router);
				parent.setHasMenuChild(parent.isHasMenuChild() || router.isMenu());
			}
		}
		// 获取根节点
		SysRouter root = routerMap.get(ROOT);
		if (root == null) {
			return null;
		}
		if (parentId == 0){
			parentId = 20;
		}
		if (parentId == root.getPrivId()) {
			return root;
		}

		return getChild(root, parentId);
	}

	/**
	 * 迭代获取权限
	 *
	 * @param parent
	 * @param privId
	 * @return
	 */
	private static SysRouter getChild(SysRouter parent, int privId) {
		for (SysRouter child : parent.getChildren()) {
			if (child.getPrivId() == privId) {
				return child;
			}
			SysRouter grandChild = getChild(child, privId);
			if (grandChild != null) {
				return grandChild;
			}

		}
		return null;
	}

	/**
	 * 从shiro中获得当前用户的所有角色
	 *
	 * @return
	 */
	public static List<String> getAllRoles() {
		SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
		return routerUtil.sysRouterMapper.getUserRolesByUserId(sysUser.getUserId());
	}
}
