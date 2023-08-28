package com.qst.system.controller;

import java.util.List;

import com.qst.system.service.ICompanyService;
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
import com.qst.system.domain.ScoreLevel;
import com.qst.system.service.IScoreLevelService;
import com.qst.common.utils.poi.ExcelUtil;
import com.qst.common.core.page.TableDataInfo;

/**
 * 用户等级Controller
 *
 * @author qst
 * @date 2022-06-27
 */
@Api(tags = "用户等级管理类")
@RestController
@RequestMapping("/system/scorelevel")
public class ScoreLevelController extends BaseController
{
    @Autowired
    private IScoreLevelService scoreLevelService;

    @Autowired
    private ICompanyService companyService;

    /**
     * 查询用户等级列表
     */
    @ApiOperation(value = "用户等级管理列表",notes = "显示全部的用户等级数据")
    @ApiImplicitParam(paramType = "query",dataType = "ScoreLevel",name = "scoreLevel",required = true)
    @PreAuthorize("@ss.hasPermi('system:level:list')")
    @GetMapping("/list")
    public TableDataInfo list(ScoreLevel scoreLevel)
    {
        startPage();
        List<ScoreLevel> list = scoreLevelService.selectScoreLevelList(scoreLevel);
        return getDataTable(list);
    }

    /**
     * 导出用户等级列表
     */
    @ApiOperation(value = "导出用户等级管理列表",notes = "导出全部的用户等级信息")
    @ApiImplicitParam(paramType = "query",dataType = "ScoreLevel",name = "scoreLevel",required = true)
    @PreAuthorize("@ss.hasPermi('system:level:export')")
    @Log(title = "用户等级", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ScoreLevel scoreLevel)
    {
        List<ScoreLevel> list = scoreLevelService.selectScoreLevelList(scoreLevel);
        ExcelUtil<ScoreLevel> util = new ExcelUtil<ScoreLevel>(ScoreLevel.class);
        return util.exportExcel(list, "用户等级数据");
    }

    /**
     * 获取用户等级详细信息
     */
    @ApiOperation(value = "查看用户等级数据",notes = "查看用户等级信息数据")
    @ApiImplicitParam(paramType = "query",dataType = "Long",name = "scoreId",value="scoreId",example = "1",required = true)
    @PreAuthorize("@ss.hasPermi('system:level:query')")
    @GetMapping(value = "/{scoreId}")
    public AjaxResult getInfo(@PathVariable("scoreId") Long scoreId)
    {
        return AjaxResult.success(scoreLevelService.selectScoreLevelByScoreId(scoreId));
    }

    /**
     * 新增用户等级
     */
    @ApiOperation(value = "新增用户等级信息",notes = "新增用户等级信息")
    @ApiImplicitParam(paramType = "query",dataType = "ScoreLevel",name = "scoreLevel",required = true)
    @PreAuthorize("@ss.hasPermi('system:level:add')")
    @Log(title = "用户等级", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ScoreLevel scoreLevel)
    {
        return toAjax(scoreLevelService.insertScoreLevel(scoreLevel));
    }

    /**
     * 修改用户等级
     */
    @ApiOperation(value = "修改用户等级信息",notes = "修改用户等级信息")
    @ApiImplicitParam(paramType = "query",dataType = "ScoreLevel",name = "scoreLevel",required = true)
    @PreAuthorize("@ss.hasPermi('system:level:edit')")
    @Log(title = "用户等级", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ScoreLevel scoreLevel)
    {
        return toAjax(scoreLevelService.updateScoreLevel(scoreLevel));
    }

    /**
     * 删除用户等级
     */
    @ApiOperation(value = "删除用户等级信息",notes = "删除用户等级信息")
    @ApiImplicitParam(paramType = "query",dataType = "Long",name = "scoreIds",value="scoreIds",example = "1",required = true)
    @PreAuthorize("@ss.hasPermi('system:level:remove')")
    @Log(title = "用户等级", businessType = BusinessType.DELETE)
    @DeleteMapping("/{scoreIds}")
    public AjaxResult remove(@PathVariable Long[] scoreIds)
    {
        return toAjax(scoreLevelService.deleteScoreLevelByScoreIds(scoreIds));
    }

    /**
     * 查询资质列表
     */
    @ApiOperation(value = "查询资质信息",notes = "查询资质信息")
    @GetMapping("/comName")
    public AjaxResult listComName()
    {
        return AjaxResult.success(companyService.selectCompanyNameAutonym());
    }
}