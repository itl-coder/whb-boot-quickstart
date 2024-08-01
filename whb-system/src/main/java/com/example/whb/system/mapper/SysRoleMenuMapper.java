package com.example.whb.system.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.whb.system.domain.SysRoleMenu;
import org.springframework.stereotype.Repository;

/**
 * @author coderitl
 * @description 针对表【sys_role_menu(角色和菜单关联表)】的数据库操作Mapper
 * @createDate 2024-08-01 13:33:02
 * @Entity com.example.whb.domain.domain.SysRoleMenu
 */
@Repository
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {


}
