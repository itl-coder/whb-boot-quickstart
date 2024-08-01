package com.example.whb.system.controller.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.whb.system.controller.service.SysRoleMenuService;
import com.example.whb.system.domain.SysRoleMenu;
import com.example.whb.system.mapper.SysRoleMenuMapper;
import org.springframework.stereotype.Service;

/**
 * @author coderitl
 * @description 针对表【sys_role_menu(角色和菜单关联表)】的数据库操作Service实现
 * @createDate 2024-08-01 13:33:02
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu>
        implements SysRoleMenuService {

}
