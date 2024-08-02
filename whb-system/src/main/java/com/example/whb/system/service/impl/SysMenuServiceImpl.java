package com.example.whb.system.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.whb.common.exception.CoderitlException;
import com.example.whb.system.domain.SysMenu;
import com.example.whb.system.domain.vo.SysMenuVo;
import com.example.whb.system.mapper.SysMenuMapper;
import com.example.whb.system.service.SysMenuService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
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
     * 构建树状菜单
     *
     * @param sysMenuList 系统菜单列表
     * @return 树状菜单列表
     */
    private List<SysMenu> buildTreeMenu(List<SysMenu> sysMenuList) {
        // 初始化树状菜单列表，用于存储最终的树状结构
        List<SysMenu> trees = new ArrayList<>();

        // 遍历所有系统菜单
        for (SysMenu sysMenu : sysMenuList) {
            // 检查菜单的父ID是否为0，即判断是否为顶级菜单
            if (sysMenu.getParentId() == 0) {
                // 如果是顶级菜单，则递归查找其子菜单，并将其添加到树状菜单列表中
                trees.add(this.findChildren(sysMenu, sysMenuList));
            }
        }
        // 返回构建完成的树状菜单列表
        return trees;
    }


    /**
     * 递归查找给定菜单的子菜单
     *
     * @param sysMenu 当前菜单对象，作为查找的根节点
     * @param sysMenuList 包含所有菜单的列表，用于查找子菜单
     * @return 返回包含所有子菜单的菜单对象
     */
    private SysMenu findChildren(SysMenu sysMenu, List<SysMenu> sysMenuList) {
        // 遍历所有菜单，寻找当前菜单的子菜单
        for (SysMenu menu : sysMenuList) {
            // 如果当前菜单的ID与另一个菜单的父ID匹配，则将其作为子菜单添加到当前菜单中
            if (sysMenu.getMenuId().equals(menu.getParentId())) {
                // 如果当前菜单的子菜单列表尚未初始化，则进行初始化
                if (sysMenu.getChildren() == null) {
                    sysMenu.setChildren(new ArrayList<>());
                }
                // 递归调用findChildren方法，将找到的子菜单添加到当前菜单的子菜单列表中
                sysMenu.getChildren().add(this.findChildren(menu, sysMenuList));
            }
        }
        // 返回包含所有子菜单的当前菜单对象
        return sysMenu;
    }

    /**
     * 根据用户ID查询系统菜单
     * 此方法用于获取指定用户ID对应的所有系统菜单，包括构建树状菜单结构和转换为VO列表
     *
     * @param userId 用户ID，用于查询该用户对应的所有系统菜单
     * @return 返回一个List<SysMenuVo>对象，包含用户的所有系统菜单信息
     * @throws CoderitlException 如果查询到的菜单列表为空，则抛出异常提示“暂无数据”
     */
    @Override
    public List<SysMenuVo> querySysMenuByUserId(Integer userId) {
        // 根据用户ID查询系统菜单
        List<SysMenu> menuList = sysMenuMapper.querySysMenuByUserId(userId);
        // 如果菜单列表为空，抛出异常
        if (ObjectUtils.isEmpty(menuList)) {
            throw new CoderitlException("暂无数据");
        }
        // 构建树状菜单结构
        List<SysMenu> sysMenuList = this.buildTreeMenu(menuList);
        // 如果构建后的树状菜单仍然为空，抛出异常
        if (ObjectUtils.isEmpty(sysMenuList)) {
            throw new CoderitlException("暂无数据");
        }
        // 将系统菜单实体转换为VO列表
        List<SysMenuVo> sysMenuVoList = this.sysMenuToSysMenuVo(sysMenuList);
        // 返回转换后的VO列表
        return sysMenuVoList;
    }

    /**
     * 将系统菜单列表转换为系统菜单视图列表
     * 该方法主要用于处理菜单数据的格式转换，以便于前端展示
     * 它遍历每个系统菜单对象，提取必要信息，并转换为相应的视图对象
     * 如果菜单有子菜单，递归地转换子菜单列表
     *
     * @param sysMenuList 系统菜单列表，包含所有菜单数据
     * @return 返回转换后的系统菜单视图列表
     */
    private List<SysMenuVo> sysMenuToSysMenuVo(List<SysMenu> sysMenuList) {
        // 初始化系统菜单视图列表，用于存储转换后的菜单视图对象
        List<SysMenuVo> sysMenuVoList = new LinkedList<>();
        // 遍历系统菜单列表中的每个菜单项
        for (SysMenu sysMenu : sysMenuList) {
            // 创建一个新的系统菜单视图对象，用于存储转换后的菜单信息
            SysMenuVo sysMenuVo = new SysMenuVo();
            sysMenuVo.setId(sysMenu.getMenuId());
            // 设置菜单标题，从系统菜单对象中获取菜单名称
            sysMenuVo.setTitle(sysMenu.getMenuName());
            // 设置菜单名称，从系统菜单对象中获取路由名称
            sysMenuVo.setName(sysMenu.getRouteName());
            sysMenuVo.setIcon(sysMenu.getIcon());
            sysMenuVo.setPath(sysMenu.getPath());
            sysMenuVo.setComponent(sysMenu.getComponent());
            // 获取当前菜单项的子菜单列表
            List<SysMenu> children = sysMenu.getChildren();
            // 如果子菜单列表不为空，则递归转换子菜单列表
            if (ObjectUtils.isNotEmpty(children)) {
                sysMenuVo.setChildren(this.sysMenuToSysMenuVo(children));
            }
            // 将转换后的菜单视图对象添加到系统菜单视图列表中
            sysMenuVoList.add(sysMenuVo);
        }
        // 返回转换后的系统菜单视图列表
        return sysMenuVoList;
    }

}
