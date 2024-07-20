package com.example.whb.common.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统用户
 *
 * @TableName sys_user
 */
@Data
@TableName(value = "sys_user")
@ApiModel(description = "系统用户实体类")
public class SysUser extends BaseEntity implements Serializable {
    /**
     * 自增主键
     */
    @ExcelProperty("序号")
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "自增主键ID", example = "1")
    private Integer id;

    /**
     * 用户名
     */
    @ExcelProperty("用户名")
    @ApiModelProperty(value = "用户名", example = "admin")
    private String username;

    /**
     * 密码
     */
    @ExcelProperty("密码")
    @ApiModelProperty(value = "密码", hidden = true)
    private String password;

    /**
     * 用户昵称
     */
    @ExcelProperty("用户昵称")
    @ApiModelProperty(value = "用户昵称", example = "超级管理员")
    private String nickname;

    /**
     * 联系人邮箱
     */
    @ExcelProperty("联系人邮箱")
    @ApiModelProperty(value = "联系人邮箱", example = "admin@example.com")
    private String email;

    /**
     * 联系人手机号
     */
    @ExcelProperty("联系人手机号")
    @ApiModelProperty(value = "联系人手机号", example = "13800138000")
    private String mobile;

    /**
     * 状态
     */
    @ExcelProperty("状态")
    @ApiModelProperty(value = "状态（0：禁用，1：启用）", example = "1")
    private Integer status;

    /**
     * 辅助密码
     */
    @ExcelProperty("辅助明文密码")
    @ApiModelProperty(value = "辅助密码", hidden = true)
    private String plainPassword;

    /**
     * 头像
     */
    @ExcelProperty("头像")
    @ApiModelProperty(value = "头像URL", example = "/static/avatar.png")
    private String photo;

    /**
     * 是否过期
     */
    @ExcelProperty("是否过期")
    @ApiModelProperty(value = "是否过期（0：未过期，1：已过期）", example = "0")
    private Integer expired;

    /**
     * 是否锁定
     */
    @ExcelProperty("是否锁定")
    @ApiModelProperty(value = "是否锁定（0：未锁定，1：已锁定）", example = "0")
    private Integer locked;

    /**
     * 是否禁用
     */
    @ExcelProperty("是否禁用")
    @ApiModelProperty(value = "是否禁用（0：未禁用，1：已禁用）", example = "0")
    private Integer enabled;

    /**
     * 凭证是否过期
     */
    @ExcelProperty("凭证是否过期")
    @ApiModelProperty(value = "凭证是否过期（0：未过期，1：已过期）", example = "0")
    private Integer credentialExpired;

    /**
     * 最后登录IP
     */
    @ExcelProperty("最后登录IP")
    private String loginIp;

    /**
     * 最后登录时间
     */
    @ExcelProperty("最后登录时间")
    private Date loginDate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
