package com.qst.system.controller;

import java.util.List;

import com.qst.system.domain.News;
import com.qst.system.service.INewsService;
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
 * 宣传信息管理Controller
 *
 * @author qst
 * @date 2022-06-16
 */
@Api(tags = "宣传信息管理类")
@RestController
@RequestMapping("/system/news")
public class NewsController extends BaseController
{
    @Autowired
    private INewsService newsService;

    /**
     * 查询宣传信息管理列表
     */
    @ApiOperation(value = "宣传信息管理列表",notes = "显示全部的宣传信息数据")
    @ApiImplicitParam(paramType = "query",dataType = "News",name = "news",required = true)
    @PreAuthorize("@ss.hasPermi('system:news:list')")
    @GetMapping("/list")
    public TableDataInfo list(News news)
    {
        startPage();
        List<News> list = newsService.selectNewsList(news);
        return getDataTable(list);
    }

    /**
     * 导出宣传信息管理列表
     */
    @ApiOperation(value = "导出宣传信息管理列表",notes = "导出全部的宣传信息")
    @ApiImplicitParam(paramType = "query",dataType = "News",name = "news",required = true)
    @PreAuthorize("@ss.hasPermi('system:news:export')")
    @Log(title = "宣传信息管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(News news)
    {
        List<News> list = newsService.selectNewsList(news);
        ExcelUtil<News> util = new ExcelUtil<News>(News.class);
        return util.exportExcel(list, "宣传信息管理数据");
    }

    /**
     * 获取宣传信息管理详细信息
     */
    @ApiOperation(value = "查看宣传信息数据",notes = "查看宣传信息数据")
    @ApiImplicitParam(paramType = "query",dataType = "Long",name = "nId",value="nId",example = "1",required = true)
    @PreAuthorize("@ss.hasPermi('system:news:query')")
    @GetMapping(value = "/{nId}")
    public AjaxResult getInfo(@PathVariable("nId") Long nId)
    {
        return AjaxResult.success(newsService.selectNewsByNId(nId));
    }

    /**
     * 新增宣传信息管理
     */
    @ApiOperation(value = "新增宣传信息",notes = "新增宣传信息")
    @ApiImplicitParam(paramType = "query",dataType = "News",name = "news",required = true)
    @PreAuthorize("@ss.hasPermi('system:news:add')")
    @Log(title = "宣传信息管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody News news)
    {
        return toAjax(newsService.insertNews(news));
    }

    /**
     * 修改宣传信息管理
     */
    @ApiOperation(value = "修改宣传信息",notes = "修改宣传信息")
    @ApiImplicitParam(paramType = "query",dataType = "News",name = "news",required = true)
    @PreAuthorize("@ss.hasPermi('system:news:edit')")
    @Log(title = "宣传信息管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody News news)
    {
        return toAjax(newsService.updateNews(news));
    }

    /**
     * 删除宣传信息管理
     */
    @ApiOperation(value = "删除宣传信息",notes = "删除宣传信息")
    @ApiImplicitParam(paramType = "query",dataType = "Long",name = "nIds",value="nIds",example = "1",required = true)
    @PreAuthorize("@ss.hasPermi('system:news:remove')")
    @Log(title = "宣传信息管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{nIds}")
    public AjaxResult remove(@PathVariable Long[] nIds)
    {
        return toAjax(newsService.deleteNewsByNIds(nIds));
    }
}