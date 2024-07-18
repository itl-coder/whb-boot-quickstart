package com.example.whb.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.whb.entity.BaseEntity;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@TableName(value = "sys_user")
@Data
@ToString
public class SysUser extends BaseEntity implements Serializable {
    /**
     * 自增主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 辅助密码
     */
    private String plainPassword;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 头像
     */
    private String photo;

    /**
     * 是否过期
     */
    private Integer expired;

    /**
     * 是否锁定
     */
    private Integer locked;

    /**
     * 是否禁用
     */
    private Integer enabled;

    /**
     * 凭证是否过期
     */
    private Integer credentialExpired;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}