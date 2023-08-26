package com.qst.system.controller;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.qst.common.annotation.Log;
import com.qst.common.core.controller.BaseController;
import com.qst.common.core.domain.AjaxResult;
import com.qst.common.enums.BusinessType;
import com.qst.system.domain.Level;
import com.qst.system.service.ILevelService;
import com.qst.common.utils.poi.ExcelUtil;
import com.qst.common.core.page.TableDataInfo;

/**
 * 等级设置Controller
 *
 * @author qst
 * @date 2022-06-27
 */
@Api("等级设置控制类")
@RestController
@RequestMapping("/system/level")
public class LevelController extends BaseController
{
    @Autowired
    private ILevelService levelService;

    /**
     * 查询等级设置列表
     */
    @ApiOperation(value = "等级设置管理列表",notes = "显示全部的等级设置数据")
    @ApiImplicitParam(paramType = "query",dataType = "Level",name = "level",required = true)
    @PreAuthorize("@ss.hasPermi('system:level:list')")
    @GetMapping("/list")
    public TableDataInfo list(Level level)
    {
        startPage();
        List<Level> list = levelService.selectLevelList(level);
        return getDataTable(list);
    }

    /**
     * 导出等级设置列表
     */
    @ApiOperation(value = "导出等级设置管理列表",notes = "导出全部的等级设置信息")
    @ApiImplicitParam(paramType = "query",dataType = "Level",name = "level",required = true)
    @PreAuthorize("@ss.hasPermi('system:level:export')")
    @Log(title = "等级设置", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Level level)
    {
        List<Level> list = levelService.selectLevelList(level);
        ExcelUtil<Level> util = new ExcelUtil<Level>(Level.class);
        return util.exportExcel(list, "等级设置数据");
    }

    /**
     * 获取等级设置详细信息
     */
    @ApiOperation(value = "查看等级设置数据",notes = "查看用户等级设置数据")
    @ApiImplicitParam(paramType = "query",dataType = "Long",name = "levelId",value="levelId",example = "1",required = true)
    @PreAuthorize("@ss.hasPermi('system:level:query')")
    @GetMapping(value = "/{levelId}")
    public AjaxResult getInfo(@PathVariable("levelId") Long levelId)
    {
        return AjaxResult.success(levelService.selectLevelByLevelId(levelId));
    }

    /**
     * 新增等级设置
     */
    @ApiOperation(value = "新增等级设置信息",notes = "新增等级设置信息")
    @ApiImplicitParam(paramType = "query",dataType = "Level",name = "level",required = true)
    @PreAuthorize("@ss.hasPermi('system:level:add')")
    @Log(title = "等级设置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Level level)
    {
        return toAjax(levelService.insertLevel(level));
    }

    /**
     * 修改等级设置
     */
    @ApiOperation(value = "修改等级设置信息",notes = "修改等级设置信息")
    @ApiImplicitParam(paramType = "query",dataType = "Level",name = "level",required = true)
    @PreAuthorize("@ss.hasPermi('system:level:edit')")
    @Log(title = "等级设置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Level level)
    {
        return toAjax(levelService.updateLevel(level));
    }

    /**
     * 删除等级设置
     */
    @ApiOperation(value = "删除等级设置信息",notes = "删除等级设置信息")
    @ApiImplicitParam(paramType = "query",dataType = "Long",name = "levelIds",value="levelIds",example = "1",required = true)
    @PreAuthorize("@ss.hasPermi('system:level:remove')")
    @Log(title = "等级设置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{levelIds}")
    public AjaxResult remove(@PathVariable Long[] levelIds)
    {
        return toAjax(levelService.deleteLevelByLevelIds(levelIds));
    }
}