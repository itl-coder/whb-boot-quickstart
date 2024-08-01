package com.example.whb.system.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.whb.system.domain.SysMenu;
import com.example.whb.system.mapper.SysMenuMapper;
import com.example.whb.system.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author coderitl
 * @description 针对表【sys_menu(菜单权限表)】的数据库操作Service实现
 * @createDate 2024-08-01 13:33:02
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu>
        implements SysMenuService {
    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> queryTreeMenuList() {
        // 1. 查询所有的菜单
        List<SysMenu> sysMenuList = sysMenuMapper.selectAll();
        // 2. 构建树形结构
        List<SysMenu> treeMenuList = this.buildTreeMenu(sysMenuList);
        return treeMenuList;
    }

    /**
     * 递归构建树形结构
     *
     * @param sysMenuList
     * @return
     */
    private List<SysMenu> buildTreeMenu(List<SysMenu> sysMenuList) {
        // 递归有两个关键: 1. 递归要有入口条件 2. 查找条件
        List<SysMenu> trees = new ArrayList<>();
        for (SysMenu sysMenu : sysMenuList) {
            // 1. 递归入口条件
            if (sysMenu.getParentId() == 0) {
                // 2. 查找条件
                trees.add(this.findChildren(sysMenu, sysMenuList));
            }
        }
        return trees;
    }

    private SysMenu findChildren(SysMenu sysMenu, List<SysMenu> sysMenuList) {
        // sysMenu 的 menuId 和 sysMenu 中的 parentId 相同, 则将 sysMenu 添加到 children 中
        for (SysMenu menu : sysMenuList) {
            if (sysMenu.getMenuId().equals(menu.getParentId())) {
                if (sysMenu.getChildren() == null) {
                    sysMenu.setChildren(new ArrayList<>());
                }
                sysMenu.getChildren().add(this.findChildren(menu, sysMenuList));
            }
        }
        return sysMenu;
    }
}
