package com.example.whb.controller.menu;


import com.example.whb.common.response.AjaxResult;
import com.example.whb.system.domain.SysMenu;
import com.example.whb.system.domain.vo.SysMenuVo;
import com.example.whb.system.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MenuController {
    @Autowired
    private SysMenuService sysMenuService;

    @GetMapping("/menu/list")
    public AjaxResult getMenu() {
        // 根据登录用户id获取用户的菜单
        List<SysMenu> treeMenuList = sysMenuService.queryTreeMenuList();
        return AjaxResult.success(treeMenuList);
    }

    @GetMapping("/menu/nav")
    public AjaxResult getMenuNav(@RequestParam Integer userId) {
        // 根据登录用户id获取用户的菜单
        List<SysMenuVo> menuList = sysMenuService.querySysMenuByUserId(userId);
        return AjaxResult.success(menuList);
    }
}
