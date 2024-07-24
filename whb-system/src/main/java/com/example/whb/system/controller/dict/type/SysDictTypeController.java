package com.example.whb.system.controller.dict.type;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.whb.common.controller.BaseController;
import com.example.whb.common.response.AjaxResult;
import com.example.whb.system.controller.dict.type.model.SysDictTypePageModel;
import com.example.whb.system.controller.dict.type.vo.SysDictTypeVo;
import com.example.whb.system.domain.SysDictType;
import com.example.whb.system.service.SysDictTypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.whb.common.response.AjaxResult.success;

@Slf4j
@Api(tags = "字典类型")
@RestController
@RequestMapping("/admin/sys/dict")
public class SysDictTypeController  {
    @Autowired
    private SysDictTypeService sysDictTypeService;

    @GetMapping
    @ApiOperation("字典类型列表")
    public AjaxResult list(SysDictTypePageModel sysDictTypePageModel) {
        Page<SysDictType> pageParam = new Page<>(sysDictTypePageModel.getPageNum(), sysDictTypePageModel.getPageSize());
        // 构建查询条件
        LambdaQueryWrapper<SysDictType> sysDictTypeLambdaQueryWrapper = Wrappers.<SysDictType>lambdaQuery()
                .like(StringUtils.isNotEmpty(sysDictTypePageModel.getDictName()), SysDictType::getDictName, sysDictTypePageModel.getDictName())
                .like(StringUtils.isNotEmpty(sysDictTypePageModel.getDictType()), SysDictType::getDictType, sysDictTypePageModel.getDictType())
                .eq(StringUtils.isNotEmpty(sysDictTypePageModel.getStatus()), SysDictType::getStatus, sysDictTypePageModel.getStatus())
                .between(sysDictTypePageModel.getCreateTime() != null && sysDictTypePageModel.getUpdateTime() != null, SysDictType::getCreateTime, sysDictTypePageModel.getCreateTime(), sysDictTypePageModel.getUpdateTime())
                .orderByDesc(SysDictType::getCreateTime);

      IPage<SysDictType> sysDictTypeList = sysDictTypeService.page(pageParam, sysDictTypeLambdaQueryWrapper);
        // 转换为VO对象
        List<SysDictTypeVo> sysDictTypeVoList = sysDictTypeList.getRecords().stream()
                .map(item -> {
                    SysDictTypeVo sysDictTypeVo = new SysDictTypeVo();
                    BeanUtils.copyProperties(item, sysDictTypeVo);
                    return sysDictTypeVo;
                }).collect(Collectors.toList());

        // 包装分页信息
        return success(sysDictTypeVoList);
    }
}
