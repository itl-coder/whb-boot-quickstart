package com.example.whb.system.controller.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.whb.system.controller.service.SysMenuService;
import com.example.whb.system.domain.SysMenu;
import com.example.whb.system.mapper.SysMenuMapper;
import org.springframework.stereotype.Service;

/**
 * @author coderitl
 * @description 针对表【sys_menu(菜单权限表)】的数据库操作Service实现
 * @createDate 2024-08-01 13:33:02
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu>
        implements SysMenuService {

}
