package com.qst.system.controller;

import java.util.List;

import com.qst.system.domain.Company;
import com.qst.system.service.IComDetailsService;
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
import com.qst.system.service.ICompanyService;
import com.qst.common.utils.poi.ExcelUtil;
import com.qst.common.core.page.TableDataInfo;

/**
 * 企业Controller
 *
 * @author qst
 * @date 2021-09-10
 */
@Api("企业控制类")
@RestController
@RequestMapping("/system/company")
public class CompanyController extends BaseController
{
    @Autowired
    private ICompanyService companyService;

    @Autowired
    private IComDetailsService comDetailsService;

    /**
     * 查询企业列表
     */
    @ApiOperation("查询企业列表")
    @PreAuthorize("@ss.hasPermi('system:company:list')")
    @GetMapping("/list")
    public TableDataInfo list(Company company)
    {
        startPage();
        List<Company> list = companyService.selectCompanyList(company);
        return getDataTable(list);
    }

    /**
     * 导出企业列表
     */
    @ApiOperation("导出企业列表")
    @PreAuthorize("@ss.hasPermi('system:company:export')")
    @Log(title = "企业信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Company company)
    {
        List<Company> list = companyService.selectCompanyList(company);
        ExcelUtil<Company> util = new ExcelUtil<Company>(Company.class);
        return util.exportExcel(list, "企业信息数据数据");
    }

    /**
     * 获取企业详细信息
     */
    @ApiOperation("获取企业详细信息")
    @ApiImplicitParam(name = "comId",value = "要查询的企业id",required = true,dataType ="Long",dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermi('system:company:query')")
    @GetMapping(value = "/{comId}")
    public AjaxResult getInfo(@PathVariable("comId") Long comId)
    {
        return AjaxResult.success(companyService.selectCompanyByComId(comId));
    }

    /**
     * 新增企业
     */
    @ApiOperation("新增企业")
    @PreAuthorize("@ss.hasPermi('system:company:add')")
    @Log(title = "新增企业", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Company company)
    {
        return toAjax(companyService.insertCompany(company));
    }

    /**
     * 修改企业信息
     */
    @ApiOperation("修改企业信息")
    @PreAuthorize("@ss.hasPermi('system:company:edit')")
    @Log(title = "修改企业信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Company company)
    {
        return toAjax(companyService.updateCompany(company));
    }

    /**
     * 删除企业
     */
    @ApiOperation("删除企业")
    @ApiImplicitParam(name = "comIds",value = "要删除的企业id的数组",required = true,dataType ="Long",dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermi('system:company:remove')")
    @Log(title = "删除企业", businessType = BusinessType.DELETE)
    @DeleteMapping("/{comIds}")
    public AjaxResult remove(@PathVariable Long[] comIds)
    {
        comDetailsService.deleteComDetailsByCIds(comIds);
        return toAjax(companyService.deleteCompanyByComIds(comIds));
    }
}