package com.example.whb.system.controller.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.whb.system.controller.service.SysUserRoleService;
import com.example.whb.system.domain.SysUserRole;
import com.example.whb.system.mapper.SysUserRoleMapper;
import org.springframework.stereotype.Service;

/**
 * @author coderitl
 * @description 针对表【sys_user_role(用户和角色关联表)】的数据库操作Service实现
 * @createDate 2024-08-01 13:33:02
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole>
        implements SysUserRoleService {

}
