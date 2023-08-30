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
import com.qst.system.domain.Overdue;
import com.qst.system.service.IOverdueService;
import com.qst.common.utils.poi.ExcelUtil;
import com.qst.common.core.page.TableDataInfo;

/**
 * 逾期Controller
 *
 * @author qst
 * @date 2021-09-28
 */
@Api("逾期控制类")
@RestController
@RequestMapping("/system/overdue")
public class OverdueController extends BaseController
{
    @Autowired
    private IOverdueService overdueService;

    /**
     * 查询逾期列表
     */
    @ApiOperation("查询逾期列表")
    @PreAuthorize("@ss.hasPermi('system:overdue:list')")
    @GetMapping("/list")
    public TableDataInfo list(Overdue overdue)
    {
        startPage();
        List<Overdue> list = overdueService.selectOverdueList(overdue);
        return getDataTable(list);
    }

    /**
     * 导出逾期列表
     */
    @ApiOperation("导出逾期列表")
    @PreAuthorize("@ss.hasPermi('system:overdue:export')")
    @Log(title = "逾期", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Overdue overdue)
    {
        List<Overdue> list = overdueService.selectOverdueList(overdue);
        ExcelUtil<Overdue> util = new ExcelUtil<Overdue>(Overdue.class);
        return util.exportExcel(list, "逾期数据");
    }

    /**
     * 获取逾期详细信息
     */
    @ApiOperation("获取逾期详细信息")
    @ApiImplicitParam(name = "oveId",value = "要查询的记录id",required = true,dataType ="Long",dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermi('system:overdue:query')")
    @GetMapping(value = "/{oveId}")
    public AjaxResult getInfo(@PathVariable("oveId") Long oveId)
    {
        return AjaxResult.success(overdueService.selectOverdueByOveId(oveId));
    }

    /**
     * 新增逾期
     */
    @ApiOperation("新增逾期")
    @PreAuthorize("@ss.hasPermi('system:overdue:add')")
    @Log(title = "逾期", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Overdue overdue)
    {
        return toAjax(overdueService.insertOverdue(overdue));
    }

    /**
     * 修改逾期
     */
    @ApiOperation("修改逾期")
    @PreAuthorize("@ss.hasPermi('system:overdue:edit')")
    @Log(title = "逾期", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Overdue overdue)
    {
        return toAjax(overdueService.updateOverdue(overdue));
    }

    /**
     * 删除逾期
     */
    @ApiOperation("删除逾期")
    @ApiImplicitParam(name = "oveIds",value = "要删除的逾期id的数组",required = true,dataType ="Long",dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermi('system:overdue:remove')")
    @Log(title = "逾期", businessType = BusinessType.DELETE)
    @DeleteMapping("/{oveIds}")
    public AjaxResult remove(@PathVariable Long[] oveIds)
    {
        return toAjax(overdueService.deleteOverdueByOveIds(oveIds));
    }
}