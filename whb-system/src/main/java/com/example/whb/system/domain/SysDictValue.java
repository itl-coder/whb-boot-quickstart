package com.example.whb.system.domain;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 字典键值表
 *
 * @TableName sys_dict_value
 */
@Data
@ApiModel("字典键值表实体")
@TableName(value = "sys_dict_value")
public class SysDictValue implements Serializable {
    /**
     * 自增主键
     */
    @TableId
    private Integer id;

    /**
     * 字典标签
     */
    @ApiModelProperty("字典标签")
    private String dictLabel;

    /**
     * 字典键值
     */
    @ApiModelProperty("字典键值")
    private String dictValue;

    /**
     * 字典排序
     */
    @ApiModelProperty("字典排序")
    private Integer dictOrder;

    /**
     * 字典键表关联Id
     */
    @ApiModelProperty("字典键表关联Id")
    private Integer dictTypeId;

    /**
     * 状态
     */
    @ApiModelProperty("状态")
    private Integer state;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;

    /**
     * 创建时间
     */

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}