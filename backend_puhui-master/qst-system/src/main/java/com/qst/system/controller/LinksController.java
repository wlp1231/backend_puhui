package com.qst.system.controller;

import java.util.List;

import com.qst.system.domain.Links;
import com.qst.system.service.ILinksService;
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
import com.qst.common.utils.poi.ExcelUtil;
import com.qst.common.core.page.TableDataInfo;

/**
 * 友情链接管理Controller
 *
 * @author qst
 * @date 2022-06-16
 */
@Api(tags = "友情链接管理类")
@RestController
@RequestMapping("/system/links")
public class LinksController extends BaseController
{
    @Autowired
    private ILinksService linksService;

    /**
     * 查询友情链接管理列表
     */
    @ApiOperation(value = "友情链接管理列表",notes = "显示全部的友情链接数据信息")
    @ApiImplicitParam(paramType = "query",dataType = "Links",name = "links",required = true)
    @PreAuthorize("@ss.hasPermi('system:links:list')")
    @GetMapping("/list")
    public TableDataInfo list(Links links)
    {
        startPage();
        List<Links> list = linksService.selectLinksList(links);
        return getDataTable(list);
    }

    /**
     * 导出友情链接管理列表
     */
    @ApiOperation(value = "导出友情链接数据管理列表",notes = "导出全部的友情链接数据信息")
    @ApiImplicitParam(paramType = "query",dataType = "Links",name = "links",required = true)
    @PreAuthorize("@ss.hasPermi('system:links:export')")
    @Log(title = "友情链接管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Links links)
    {
        List<Links> list = linksService.selectLinksList(links);
        ExcelUtil<Links> util = new ExcelUtil<Links>(Links.class);
        return util.exportExcel(list, "友情链接管理数据");
    }

    /**
     * 获取友情链接管理详细信息
     */
    @ApiOperation(value = "查看友情链接数据信息",notes = "查看友情链接数据信息")
    @ApiImplicitParam(paramType = "query",dataType = "Long",name = "linkId",value="linkId",example = "1",required = true)
    @PreAuthorize("@ss.hasPermi('system:links:query')")
    @GetMapping(value = "/{linkId}")
    public AjaxResult getInfo(@PathVariable("linkId") Long linkId)
    {
        return AjaxResult.success(linksService.selectLinksByLinkId(linkId));
    }

    /**
     * 新增友情链接管理
     */
    @ApiOperation(value = "新增友情链接数据",notes = "新增友情链接数据")
    @ApiImplicitParam(paramType = "query",dataType = "Links",name = "links",required = true)
    @PreAuthorize("@ss.hasPermi('system:links:add')")
    @Log(title = "友情链接管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Links links)
    {
        return toAjax(linksService.insertLinks(links));
    }

    /**
     * 修改友情链接管理
     */
    @ApiOperation(value = "修改友情链接数据",notes = "修改友情链接数据")
    @ApiImplicitParam(paramType = "query",dataType = "Links",name = "links",required = true)
    @PreAuthorize("@ss.hasPermi('system:links:edit')")
    @Log(title = "友情链接管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Links links)
    {
        return toAjax(linksService.updateLinks(links));
    }

    /**
     * 删除友情链接管理
     */
    @ApiOperation(value = "删除友情链接数据",notes = "删除友情链接数据")
    @ApiImplicitParam(paramType = "query",dataType = "Long",name = "linkIds",value="linkIds",example = "1",required = true)
    @PreAuthorize("@ss.hasPermi('system:links:remove')")
    @Log(title = "友情链接管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{linkIds}")
    public AjaxResult remove(@PathVariable Long[] linkIds)
    {
        return toAjax(linksService.deleteLinksByLinkIds(linkIds));
    }
}