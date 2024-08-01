package com.example.whb.system.controller.dict.data;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.whb.common.controller.BaseController;
import com.example.whb.common.exception.CoderitlException;
import com.example.whb.common.response.AjaxResult;
import com.example.whb.system.controller.dict.data.model.SysDictDataSaveModel;
import com.example.whb.system.domain.SysDictData;
import com.example.whb.system.domain.SysDictType;
import com.example.whb.system.service.SysDictDataService;
import com.example.whb.system.service.SysDictTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Api(tags = "字典类型")
@RestController
@RequestMapping("/admin/sys/dict")
public class SysDictDataController extends BaseController {
    @Autowired
    private SysDictTypeService sysDictTypeService;
    @Autowired
    private SysDictDataService sysDictDataService;


    @ApiOperation(value = "字典数据列表")
    @GetMapping("/type/{dictType}")
    public AjaxResult list(@PathVariable String dictType) {
        if (ObjectUtils.isEmpty(dictType)) {
            throw new CoderitlException("字典类型不允许为空");
        }
        List<SysDictData> list = sysDictDataService.list(Wrappers.<SysDictData>lambdaQuery().eq(SysDictData::getDictType, dictType));
        return success(list);
    }

    /**
     * 新增字典数据的值
     */
    @ApiOperation(value = "新增字典数据的值")
    @PostMapping("/add")
    public AjaxResult add(@RequestBody SysDictDataSaveModel sysDictDataSaveModel) {
        SysDictData sysDictData = copyProperties(sysDictDataSaveModel);
        return toAjax(sysDictDataService.save(sysDictData));
    }

    /**
     * 根据SysDictDataSaveModel复制属性到SysDictData对象。
     * <p>
     * 该方法通过BeanUtils的copyProperties方法，实现从SysDictDataSaveModel到SysDictData的对象属性复制。
     * 主要用于在保存字典数据时，将前端传递的模型对象属性值复制到SysDictData对象中，以便后续的处理和保存操作。
     *
     * @param sysDictDataSaveModel 字典数据保存模型，包含了需要保存的字典数据的属性。
     * @return SysDictData 复制属性后的SysDictData对象。
     */
    private static SysDictData copyProperties(SysDictDataSaveModel sysDictDataSaveModel) {
        SysDictData sysDictData = new SysDictData();
        BeanUtils.copyProperties(sysDictDataSaveModel, sysDictData);
        return sysDictData;
    }

    @ApiOperation(value = "修改字典数据的值")
    @PutMapping("/update")
    public AjaxResult update(@RequestBody SysDictDataSaveModel sysDictDataSaveModel) {
        SysDictData sysDictData = copyProperties(sysDictDataSaveModel);
        return toAjax(sysDictDataService.updateById(sysDictData));
    }

    @ApiOperation(value = "删除字典数据的值")
    @DeleteMapping("/delete/{dictCode}")
    public AjaxResult delete(@PathVariable Long dictCode) {
        return toAjax(sysDictDataService.removeById(dictCode));
    }
}
