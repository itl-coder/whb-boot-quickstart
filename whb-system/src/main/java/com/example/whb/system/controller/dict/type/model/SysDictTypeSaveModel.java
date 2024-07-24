package com.example.whb.system.controller.dict.type.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("字典类型添加修改实体")
public class SysDictTypeSaveModel {
    /**
     * 字典主键
     */
    private Long dictId;

    /**
     * 字典名称
     */
    private String dictName;

    /**
     * 字典类型
     */
    private String dictType;

    /**
     * 状态（0正常 1停用）
     */
    private String status;

    /**
     * 备注
     */
    private String remark;
}
