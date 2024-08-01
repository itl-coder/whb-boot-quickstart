package com.example.whb.system.controller.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.whb.system.controller.service.SysRoleService;
import com.example.whb.system.domain.SysRole;
import com.example.whb.system.mapper.SysRoleMapper;
import org.springframework.stereotype.Service;

/**
 * @author coderitl
 * @description 针对表【sys_role(角色信息表)】的数据库操作Service实现
 * @createDate 2024-08-01 13:33:02
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole>
        implements SysRoleService {

}
