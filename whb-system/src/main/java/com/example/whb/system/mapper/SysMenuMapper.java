package com.example.whb.system.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.whb.system.domain.SysMenu;
import org.springframework.stereotype.Repository;

/**
 * @author coderitl
 * @description 针对表【sys_menu(菜单权限表)】的数据库操作Mapper
 * @createDate 2024-08-01 13:33:02
 * @Entity com.example.whb.domain.domain.SysMenu
 */
@Repository
public interface SysMenuMapper extends BaseMapper<SysMenu> {


}
