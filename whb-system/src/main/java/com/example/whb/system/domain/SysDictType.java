package com.example.whb.system.domain;


import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 字典键表
 *
 * @TableName sys_dict_type
 */
@Data
@ApiModel(value = "字典键表实体")
@TableName(value = "sys_dict_type")
public class SysDictType implements Serializable {
    /**
     * 自增主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 字典名称
     */
    @ApiModelProperty(value = "字典名称")
    private String dictName;

    /**
     * 字典类型
     */
    @ApiModelProperty(value = "字典类型")
    private String dictType;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer state;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}