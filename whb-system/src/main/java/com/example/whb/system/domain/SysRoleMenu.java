package com.example.whb.system.domain;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色和菜单关联表
 *
 * @TableName sys_role_menu
 */
@TableName(value = "sys_role_menu")
@Data
public class SysRoleMenu implements Serializable {
    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 菜单ID
     */

    private Long menuId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}