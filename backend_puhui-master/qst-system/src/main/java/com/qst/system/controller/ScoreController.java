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
import com.qst.system.domain.Score;
import com.qst.system.service.IScoreService;
import com.qst.common.utils.poi.ExcelUtil;
import com.qst.common.core.page.TableDataInfo;

/**
 * 用户积分Controller
 *
 * @author qst
 * @date 2022-06-27
 */
@Api(tags = "用户积分管理类")
@RestController
@RequestMapping("/system/score")
public class ScoreController extends BaseController
{
    @Autowired
    private IScoreService scoreService;

    @Autowired
    private ICompanyService companyService;

    /**
     * 查询用户积分列表
     */
    @ApiOperation(value = "用户积分管理列表",notes = "显示全部的用户积分数据")
    @ApiImplicitParam(paramType = "query",dataType = "Score",name = "score",required = true)
    @PreAuthorize("@ss.hasPermi('system:score:list')")
    @GetMapping("/list")
    public TableDataInfo list(Score score)
    {
        startPage();
        List<Score> list = scoreService.selectScoreList(score);
        return getDataTable(list);
    }

    /**
     * 导出用户积分列表
     */
    @ApiOperation(value = "导出用户积分管理列表",notes = "导出全部的用户积分信息")
    @ApiImplicitParam(paramType = "query",dataType = "Score",name = "score",required = true)
    @PreAuthorize("@ss.hasPermi('system:score:export')")
    @Log(title = "用户积分", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Score score)
    {
        List<Score> list = scoreService.selectScoreList(score);
        ExcelUtil<Score> util = new ExcelUtil<Score>(Score.class);
        return util.exportExcel(list, "用户积分数据");
    }

    /**
     * 获取用户积分详细信息
     */
    @ApiOperation(value = "查看用户积分数据",notes = "查看用户积分信息数据")
    @ApiImplicitParam(paramType = "query",dataType = "Long",name = "scoreId",value="scoreId",example = "1",required = true)
    @PreAuthorize("@ss.hasPermi('system:score:query')")
    @GetMapping(value = "/{scoreId}")
    public AjaxResult getInfo(@PathVariable("scoreId") Long scoreId)
    {
        return AjaxResult.success(scoreService.selectScoreByScoreId(scoreId));
    }

    /**
     * 新增用户积分
     */
    @ApiOperation(value = "新增用户积分信息",notes = "新增用户积分信息")
    @ApiImplicitParam(paramType = "query",dataType = "Score",name = "score",required = true)
    @PreAuthorize("@ss.hasPermi('system:score:add')")
    @Log(title = "用户积分", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Score score)
    {
        return toAjax(scoreService.insertScore(score));
    }

    /**
     * 修改用户积分
     */
    @ApiOperation(value = "修改用户积分信息",notes = "修改用户积分信息")
    @ApiImplicitParam(paramType = "query",dataType = "Score",name = "score",required = true)
    @PreAuthorize("@ss.hasPermi('system:score:edit')")
    @Log(title = "用户积分", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Score score)
    {
        return toAjax(scoreService.updateScore(score));
    }

    /**
     * 删除用户积分
     */
    @ApiOperation(value = "删除用户积分信息",notes = "删除用户积分信息")
    @ApiImplicitParam(paramType = "query",dataType = "Long",name = "scoreIds",value="scoreIds",example = "1",required = true)
    @PreAuthorize("@ss.hasPermi('system:score:remove')")
    @Log(title = "用户积分", businessType = BusinessType.DELETE)
    @DeleteMapping("/{scoreIds}")
    public AjaxResult remove(@PathVariable Long[] scoreIds)
    {
        return toAjax(scoreService.deleteScoreByScoreIds(scoreIds));
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