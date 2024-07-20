package com.example.whb.common.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableLogic;

import java.util.Date;

public class BaseEntity {
    /**
     * 创建人Id
     */
    @ExcelProperty(value = "创建人Id")
    private Integer createBy;

    /**
     * 更新人Id
     */
    @ExcelProperty(value = "更新人Id")
    private Integer updateBy;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @ExcelProperty(value = "更新时间")
    private Date updateTime;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


    /**
     * 是否删除
     */
    @ExcelProperty(value = "是否删除")
    @TableLogic
    private Integer idDel;
}
