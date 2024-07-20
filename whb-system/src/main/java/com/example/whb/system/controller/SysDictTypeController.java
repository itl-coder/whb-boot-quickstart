package com.example.whb.system.controller;

import com.example.whb.common.controller.BaseController;
import com.example.whb.common.response.AjaxResult;
import com.example.whb.system.service.SysDictTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = "字典类型")
@RestController
@RequestMapping("/admin/sys/dict")
public class SysDictTypeController extends BaseController {
    @Autowired
    private SysDictTypeService sysDictTypeService;

    @GetMapping
    @ApiOperation("字典类型列表")
    public AjaxResult list(){
        return success(sysDictTypeService.list());
    }
}
