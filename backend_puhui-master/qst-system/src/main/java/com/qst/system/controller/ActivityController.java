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
import com.qst.system.domain.Activity;
import com.qst.system.service.IActivityService;
import com.qst.common.utils.poi.ExcelUtil;
import com.qst.common.core.page.TableDataInfo;

/**
 * 活动管理Controller
 *
 * @author zpbao
 * @date 2023-08-28
 */
@Api("活动管理控制类")
@RestController
@RequestMapping("/system/activity")
public class ActivityController extends BaseController
{
    @Autowired
    private IActivityService activityService;

    /**
     * 查询活动管理列表
     */
    @ApiOperation(value = "活动管理列表",notes = "显示全部的活动信息数据")
    @ApiImplicitParam(paramType = "query",dataType = "Activity",name = "activity",required = true)
    @PreAuthorize("@ss.hasPermi('system:activity:list')")
    @GetMapping("/list")
    public TableDataInfo list(Activity activity)
    {
        startPage();
        List<Activity> list = activityService.selectActivityList(activity);
        return getDataTable(list);
    }

    /**
     * 导出活动管理列表
     */
    @ApiOperation(value = "导出活动管理列表",notes = "导出全部的活动信息")
    @ApiImplicitParam(paramType = "query",dataType = "Activity",name = "activity",required = true)
    @PreAuthorize("@ss.hasPermi('system:activity:export')")
    @Log(title = "活动管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Activity activity)
    {
        List<Activity> list = activityService.selectActivityList(activity);
        ExcelUtil<Activity> util = new ExcelUtil<Activity>(Activity.class);
        return util.exportExcel(list, "活动管理数据");
    }

    /**
     * 获取活动管理详细信息
     */
    @ApiOperation(value = "查看活动数据",notes = "查看活动信息数据")
    @ApiImplicitParam(paramType = "query",dataType = "Long",name = "aId",value="aId",example = "1",required = true)
    @PreAuthorize("@ss.hasPermi('system:activity:query')")
    @GetMapping(value = "/{aId}")
    public AjaxResult getInfo(@PathVariable("aId") Long aId)
    {
        return AjaxResult.success(activityService.selectActivityByAId(aId));
    }

    /**
     * 新增活动管理
     */
    @ApiOperation(value = "新增活动信息",notes = "新增活动信息")
    @ApiImplicitParam(paramType = "query",dataType = "Activity",name = "activity",required = true)
    @PreAuthorize("@ss.hasPermi('system:activity:add')")
    @Log(title = "活动管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Activity activity)
    {
        return toAjax(activityService.insertActivity(activity));
    }

    /**
     * 修改活动管理
     */
    @ApiOperation(value = "修改活动信息",notes = "修改活动信息")
    @ApiImplicitParam(paramType = "query",dataType = "Activity",name = "activity",required = true)
    @PreAuthorize("@ss.hasPermi('system:activity:edit')")
    @Log(title = "活动管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Activity activity)
    {
        return toAjax(activityService.updateActivity(activity));
    }

    /**
     * 删除活动管理
     */
    @ApiOperation(value = "删除活动信息",notes = "删除活动信息")
    @ApiImplicitParam(paramType = "query",dataType = "Long",name = "aIds",value="aIds",example = "1",required = true)
    @PreAuthorize("@ss.hasPermi('system:activity:remove')")
    @Log(title = "活动管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{aIds}")
    public AjaxResult remove(@PathVariable Long[] aIds)
    {
        return toAjax(activityService.deleteActivityByAIds(aIds));
    }
}