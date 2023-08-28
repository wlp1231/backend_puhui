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
import com.qst.system.domain.ShareRecord;
import com.qst.system.service.IShareRecordService;
import com.qst.common.utils.poi.ExcelUtil;
import com.qst.common.core.page.TableDataInfo;

/**
 * 分享数据管理Controller
 *
 * @author qst
 * @date 2022-06-14
 */
@Api(tags = "分享数据管理类")
@RestController
@RequestMapping("/system/record")
public class ShareRecordController extends BaseController
{
    @Autowired
    private IShareRecordService shareRecordService;

    @Autowired
    private ICompanyService companyService;

    /**
     * 查询分享数据管理列表
     */
    @ApiOperation(value = "分享数据管理列表",notes = "显示全部的分享数据信息")
    @ApiImplicitParam(paramType = "query",dataType = "ShareRecord",name = "shareRecord",required = true)
    @PreAuthorize("@ss.hasPermi('system:record:list')")
    @GetMapping("/list")
    public TableDataInfo list(ShareRecord shareRecord)
    {
        startPage();
        List<ShareRecord> list = shareRecordService.selectShareRecordList(shareRecord);
        return getDataTable(list);
    }

    /**
     * 导出分享数据管理列表
     */
    @ApiOperation(value = "导出分享数据管理列表",notes = "导出全部的分享数据信息")
    @ApiImplicitParam(paramType = "query",dataType = "ShareRecord",name = "shareRecord",required = true)
    @PreAuthorize("@ss.hasPermi('system:record:export')")
    @Log(title = "分享数据管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ShareRecord shareRecord)
    {
        List<ShareRecord> list = shareRecordService.selectShareRecordList(shareRecord);
        ExcelUtil<ShareRecord> util = new ExcelUtil<ShareRecord>(ShareRecord.class);
        return util.exportExcel(list, "分享数据管理数据");
    }

    /**
     * 获取分享数据管理详细信息
     */
    @ApiOperation(value = "查看分享数据信息",notes = "查看分享数据信息")
    @ApiImplicitParam(paramType = "query",dataType = "Long",name = "rId",value="rId",example = "1",required = true)
    @PreAuthorize("@ss.hasPermi('system:record:query')")
    @GetMapping(value = "/{rId}")
    public AjaxResult getInfo(@PathVariable("rId") Long rId)
    {
        return AjaxResult.success(shareRecordService.selectShareRecordByRId(rId));
    }

    /**
     * 新增分享数据管理
     */
    @ApiOperation(value = "新增分享数据",notes = "新增分享数据")
    @ApiImplicitParam(paramType = "query",dataType = "ShareRecord",name = "shareRecord",required = true)
    @PreAuthorize("@ss.hasPermi('system:record:add')")
    @Log(title = "分享数据管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ShareRecord shareRecord)
    {
        return toAjax(shareRecordService.insertShareRecord(shareRecord));
    }

    /**
     * 修改分享数据管理
     */
    @ApiOperation(value = "修改分享数据",notes = "修改分享数据")
    @ApiImplicitParam(paramType = "query",dataType = "ShareRecord",name = "shareRecord",required = true)
    @PreAuthorize("@ss.hasPermi('system:record:edit')")
    @Log(title = "分享数据管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ShareRecord shareRecord)
    {
        return toAjax(shareRecordService.updateShareRecord(shareRecord));
    }

    /**
     * 删除分享数据管理
     */
    @ApiOperation(value = "删除分享数据",notes = "删除分享数据")
    @ApiImplicitParam(paramType = "query",dataType = "Long",name = "rIds",value="rId",example = "1",required = true)
    @PreAuthorize("@ss.hasPermi('system:record:remove')")
    @Log(title = "分享数据管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{rIds}")
    public AjaxResult remove(@PathVariable Long[] rIds)
    {
        return toAjax(shareRecordService.deleteShareRecordByRIds(rIds));
    }

    /**
     * 查询资质列表
     */
    @ApiOperation(value = "查询资质列表",notes = "查询资质列表")
    @GetMapping("/comName")
    public AjaxResult listComName()
    {
        return AjaxResult.success(companyService.selectCompanyNameAutonym());
    }
}