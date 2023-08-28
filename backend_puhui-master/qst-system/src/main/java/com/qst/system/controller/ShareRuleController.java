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
import com.qst.system.domain.ShareRule;
import com.qst.system.service.IShareRuleService;
import com.qst.common.utils.poi.ExcelUtil;
import com.qst.common.core.page.TableDataInfo;

/**
 * 分享规则管理Controller
 *
 * @author qst
 * @date 2022-06-14
 */
@Api(tags = "分享规则管理类")
@RestController
@RequestMapping("/system/rule")
public class ShareRuleController extends BaseController
{
    @Autowired
    private IShareRuleService shareRuleService;

    /**
     * 查询分享规则管理列表
     */
    @ApiOperation(value = "分享规则管理列表",notes = "显示全部的分享规则信息")
    @ApiImplicitParam(paramType = "query",dataType = "ShareRule",name = "shareRule",required = true)
    @PreAuthorize("@ss.hasPermi('system:rule:list')")
    @GetMapping("/list")
    public TableDataInfo list(ShareRule shareRule)
    {
        startPage();
        List<ShareRule> list = shareRuleService.selectShareRuleList(shareRule);
        return getDataTable(list);
    }

    /**
     * 导出分享规则管理列表
     */
    @ApiOperation(value = "导出分享规则管理列表",notes = "导出全部的分享规则信息")
    @ApiImplicitParam(paramType = "query",dataType = "ShareRule",name = "shareRule",required = true)
    @PreAuthorize("@ss.hasPermi('system:rule:export')")
    @Log(title = "分享规则管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ShareRule shareRule)
    {
        List<ShareRule> list = shareRuleService.selectShareRuleList(shareRule);
        ExcelUtil<ShareRule> util = new ExcelUtil<ShareRule>(ShareRule.class);
        return util.exportExcel(list, "分享规则管理数据");
    }

    /**
     * 获取分享规则管理详细信息
     */
    @ApiOperation(value = "查看分享规则信息",notes = "查看分享规则信息")
    @ApiImplicitParam(paramType = "query",dataType = "Long",name = "sId",value="sId",example = "1",required = true)
    @PreAuthorize("@ss.hasPermi('system:rule:query')")
    @GetMapping(value = "/{sId}")
    public AjaxResult getInfo(@PathVariable("sId") Long sId)
    {
        return AjaxResult.success(shareRuleService.selectShareRuleBySId(sId));
    }

    /**
     * 新增分享规则管理
     */
    @ApiOperation(value = "新增规则数据",notes = "新增规则数据")
    @ApiImplicitParam(paramType = "query",dataType = "ShareRule",name = "shareRule",required = true)
    @PreAuthorize("@ss.hasPermi('system:rule:add')")
    @Log(title = "分享规则管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ShareRule shareRule)
    {
        return toAjax(shareRuleService.insertShareRule(shareRule));
    }

    /**
     * 修改分享规则管理
     */
    @ApiOperation(value = "修改分享规则",notes = "修改分享规则")
    @ApiImplicitParam(paramType = "query",dataType = "ShareRule",name = "shareRule",required = true)
    @PreAuthorize("@ss.hasPermi('system:rule:edit')")
    @Log(title = "分享规则管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ShareRule shareRule)
    {
        return toAjax(shareRuleService.updateShareRule(shareRule));
    }

    /**
     * 删除分享规则管理
     */
    @ApiOperation(value = "删除分享规则",notes = "删除分享规则")
    @ApiImplicitParam(paramType = "query",dataType = "Long",name = "sIds",value="sId",example = "1",required = true)
    @PreAuthorize("@ss.hasPermi('system:rule:remove')")
    @Log(title = "分享规则管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{sIds}")
    public AjaxResult remove(@PathVariable Long[] sIds)
    {
        return toAjax(shareRuleService.deleteShareRuleBySIds(sIds));
    }
}