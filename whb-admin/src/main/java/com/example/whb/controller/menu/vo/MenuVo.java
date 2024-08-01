package com.example.whb.controller.menu.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "菜单响应实体")
public class MenuVo {
    @ApiModelProperty("菜单id")
    private Integer id;
    @ApiModelProperty("图标")
    private String icon;
    @ApiModelProperty("菜单名称")
    private String name;
    @ApiModelProperty("菜单类型")
    private String type;
    @ApiModelProperty("菜单地址")
    private String url;
}
