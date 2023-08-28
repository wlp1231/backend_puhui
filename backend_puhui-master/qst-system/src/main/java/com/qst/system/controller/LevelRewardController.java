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
import com.qst.system.domain.LevelReward;
import com.qst.system.service.ILevelRewardService;
import com.qst.common.utils.poi.ExcelUtil;
import com.qst.common.core.page.TableDataInfo;

/**
 * level_rewardController
 *
 * @author zpbao
 * @date 2023-08-28
 */
@Api(tags = "等级奖励管理类")
@RestController
@RequestMapping("/system/reward")
public class LevelRewardController extends BaseController
{
    @Autowired
    private ILevelRewardService levelRewardService;

    /**
     * 查询level_reward列表
     */
    @ApiOperation(value = "等级奖励管理列表",notes = "显示全部的等级奖励数据")
    @ApiImplicitParam(paramType = "query",dataType = "LevelReward",name = "levelReward",required = true)
    @PreAuthorize("@ss.hasPermi('system:reward:list')")
    @GetMapping("/list")
    public TableDataInfo list(LevelReward levelReward)
    {
        startPage();
        List<LevelReward> list = levelRewardService.selectLevelRewardList(levelReward);
        return getDataTable(list);
    }

    /**
     * 导出level_reward列表
     */
    @ApiOperation(value = "导出等级奖励管理列表",notes = "导出全部的等级奖励信息")
    @ApiImplicitParam(paramType = "query",dataType = "LevelReward",name = "levelReward",required = true)
    @PreAuthorize("@ss.hasPermi('system:reward:export')")
    @Log(title = "level_reward", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(LevelReward levelReward)
    {
        List<LevelReward> list = levelRewardService.selectLevelRewardList(levelReward);
        ExcelUtil<LevelReward> util = new ExcelUtil<LevelReward>(LevelReward.class);
        return util.exportExcel(list, "level_reward数据");
    }

    /**
     * 获取level_reward详细信息
     */
    @ApiOperation(value = "查看等级奖励数据",notes = "查看等级奖励信息数据")
    @ApiImplicitParam(paramType = "query",dataType = "Long",name = "rewardId",value="rewardId",example = "1",required = true)
    @PreAuthorize("@ss.hasPermi('system:reward:query')")
    @GetMapping(value = "/{rewardId}")
    public AjaxResult getInfo(@PathVariable("rewardId") Long rewardId)
    {
        return AjaxResult.success(levelRewardService.selectLevelRewardByRewardId(rewardId));
    }

    /**
     * 新增level_reward
     */
    @ApiOperation(value = "新增等级奖励信息",notes = "新增等级奖励信息")
    @ApiImplicitParam(paramType = "query",dataType = "LevelReward",name = "levelReward",required = true)
    @PreAuthorize("@ss.hasPermi('system:reward:add')")
    @Log(title = "level_reward", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LevelReward levelReward)
    {
        return toAjax(levelRewardService.insertLevelReward(levelReward));
    }

    /**
     * 修改level_reward
     */
    @ApiOperation(value = "修改等级奖励信息",notes = "修改等级奖励信息")
    @ApiImplicitParam(paramType = "query",dataType = "LevelReward",name = "levelReward",required = true)
    @PreAuthorize("@ss.hasPermi('system:reward:edit')")
    @Log(title = "level_reward", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LevelReward levelReward)
    {
        return toAjax(levelRewardService.updateLevelReward(levelReward));
    }

    /**
     * 删除level_reward
     */
    @ApiOperation(value = "删除等级奖励信息",notes = "删除等级奖励信息")
    @ApiImplicitParam(paramType = "query",dataType = "Long",name = "rewardIds",value="rewardIds",example = "1",required = true)
    @PreAuthorize("@ss.hasPermi('system:reward:remove')")
    @Log(title = "level_reward", businessType = BusinessType.DELETE)
    @DeleteMapping("/{rewardIds}")
    public AjaxResult remove(@PathVariable Long[] rewardIds)
    {
        return toAjax(levelRewardService.deleteLevelRewardByRewardIds(rewardIds));
    }
}