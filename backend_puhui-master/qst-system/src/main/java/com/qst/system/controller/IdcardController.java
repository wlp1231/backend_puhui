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
import com.qst.system.domain.Idcard;
import com.qst.system.service.IIdcardService;
import com.qst.common.utils.poi.ExcelUtil;
import com.qst.common.core.page.TableDataInfo;

/**
 * 身份证识别Controller
 *
 * @author qst
 * @date 2022-06-28
 */
@Api("身份识别控制类")
@RestController
@RequestMapping("/system/idcard")
public class IdcardController extends BaseController
{
    @Autowired
    private IIdcardService idcardService;

    @Autowired
    private ICompanyService companyService;

    /**
     * 查询身份证识别列表
     */
    @ApiOperation(value = "身份证识别列表",notes = "显示全部的身份证识别信息数据")
    @ApiImplicitParam(paramType = "query",dataType = "Idcard",name = "idcard",required = true)
    @PreAuthorize("@ss.hasPermi('system:idcard:list')")
    @GetMapping("/list")
    public TableDataInfo list(Idcard idcard)
    {
        System.out.println("idcard=========="+idcard);
        startPage();
        List<Idcard> list = idcardService.selectIdcardList(idcard);
        return getDataTable(list);
    }

    /**
     * 导出身份证识别列表
     */
    @ApiOperation(value = "导出身份证识别管理列表",notes = "导出全部的身份证识别信息")
    @ApiImplicitParam(paramType = "query",dataType = "Idcard",name = "idcard",required = true)
    @PreAuthorize("@ss.hasPermi('system:idcard:export')")
    @Log(title = "身份证识别", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Idcard idcard)
    {
        List<Idcard> list = idcardService.selectIdcardList(idcard);
        ExcelUtil<Idcard> util = new ExcelUtil<Idcard>(Idcard.class);
        return util.exportExcel(list, "身份证识别数据");
    }

    /**
     * 获取身份证识别详细信息
     */
    @ApiOperation(value = "查看身份证识别数据",notes = "查看身份证识别信息数据")
    @ApiImplicitParam(paramType = "query",dataType = "Long",name = "id",value="id",example = "1",required = true)
    @PreAuthorize("@ss.hasPermi('system:idcard:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(idcardService.selectIdcardById(id));
    }

    /**
     * 新增身份证识别
     */
    @ApiOperation(value = "新增身份证识别信息",notes = "新增身份证识别信息")
    @ApiImplicitParam(paramType = "query",dataType = "Idcard",name = "idcard",required = true)
    @PreAuthorize("@ss.hasPermi('system:idcard:add')")
    @Log(title = "身份证识别", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Idcard idcard)
    {
        return toAjax(idcardService.insertIdcard(idcard));
    }

    /**
     * 修改身份证识别
     */
    @ApiOperation(value = "修改身份证识别信息",notes = "修改身份证识别信息")
    @ApiImplicitParam(paramType = "query",dataType = "Idcard",name = "idcard",required = true)
    @PreAuthorize("@ss.hasPermi('system:idcard:edit')")
    @Log(title = "身份证识别", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Idcard idcard)
    {
        return toAjax(idcardService.updateIdcard(idcard));
    }

    /**
     * 删除身份证识别
     */
    @ApiOperation(value = "删除身份证识别信息",notes = "删除身份证识别信息")
    @ApiImplicitParam(paramType = "query",dataType = "Long",name = "ids",value="ids",example = "1",required = true)
    @PreAuthorize("@ss.hasPermi('system:idcard:remove')")
    @Log(title = "身份证识别", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(idcardService.deleteIdcardByIds(ids));
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