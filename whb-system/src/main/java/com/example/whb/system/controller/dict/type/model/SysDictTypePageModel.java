package com.example.whb.system.controller.dict.type.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("字典类型分页请求实体")
public class SysDictTypePageModel {
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

    private Integer pageNum;

    private Integer pageSize;

    private String createTime;

    private String updateTime;
}
