package com.example.whb.system.domain.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "系统菜单响应结果实体类")
public class SysMenuVo {
    private Long id;
    private String path;
    private String title;
    private String name;
    private String icon;
    private String component;
    private List<SysMenuVo> children;
}
