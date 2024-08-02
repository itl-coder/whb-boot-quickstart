package com.example.whb.system.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.whb.system.domain.SysMenu;
import com.example.whb.system.domain.vo.SysMenuVo;

import java.util.List;

/**
 * @author coderitl
 * @description 针对表【sys_menu(菜单权限表)】的数据库操作Service
 * @createDate 2024-08-01 13:33:02
 */
public interface SysMenuService extends IService<SysMenu> {

    List<SysMenu> queryTreeMenuList();

    List<SysMenuVo> querySysMenuByUserId(Integer userId);
}
