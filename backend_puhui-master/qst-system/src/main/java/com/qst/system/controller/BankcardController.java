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
import com.qst.system.domain.Bankcard;
import com.qst.system.service.IBankcardService;
import com.qst.common.utils.poi.ExcelUtil;
import com.qst.common.core.page.TableDataInfo;

/**
 * 银行卡识别信息Controller
 *
 * @author qst
 * @date 2022-06-28
 */
@Api("银行卡识别控制类")
@RestController
@RequestMapping("/system/bankcard")
public class BankcardController extends BaseController
{
    @Autowired
    private IBankcardService bankcardService;

    @Autowired
    private ICompanyService companyService;

    /**
     * 查询银行卡识别信息列表
     */
    @ApiOperation(value = "银行卡识别管理列表",notes = "显示全部的银行卡识别信息数据")
    @ApiImplicitParam(paramType = "query",dataType = "Bankcard",name = "bankcard",required = true)
    @PreAuthorize("@ss.hasPermi('system:bankcard:list')")
    @GetMapping("/list")
    public TableDataInfo list(Bankcard bankcard)
    {
        startPage();
        List<Bankcard> list = bankcardService.selectBankcardList(bankcard);
        return getDataTable(list);
    }

    /**
     * 导出银行卡识别信息列表
     */
    @ApiOperation(value = "导出银行卡识别信息管理列表",notes = "导出全部的银行卡识别信息")
    @ApiImplicitParam(paramType = "query",dataType = "Bankcard",name = "bankcard",required = true)
    @PreAuthorize("@ss.hasPermi('system:bankcard:export')")
    @Log(title = "银行卡识别信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Bankcard bankcard)
    {
        List<Bankcard> list = bankcardService.selectBankcardList(bankcard);
        ExcelUtil<Bankcard> util = new ExcelUtil<Bankcard>(Bankcard.class);
        return util.exportExcel(list, "银行卡识别信息数据");
    }

    /**
     * 获取银行卡识别信息详细信息
     */
    @ApiOperation(value = "查看银行卡识别信息数据",notes = "查看银行卡识别信息信息数据")
    @ApiImplicitParam(paramType = "query",dataType = "Long",name = "id",value="id",example = "1",required = true)
    @PreAuthorize("@ss.hasPermi('system:bankcard:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(bankcardService.selectBankcardById(id));
    }

    /**
     * 新增银行卡识别信息
     */
    @ApiOperation(value = "新增银行卡识别信息",notes = "新增银行卡识别信息")
    @ApiImplicitParam(paramType = "query",dataType = "Bankcard",name = "bankcard",required = true)
    @PreAuthorize("@ss.hasPermi('system:bankcard:add')")
    @Log(title = "银行卡识别信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Bankcard bankcard)
    {
        return toAjax(bankcardService.insertBankcard(bankcard));
    }

    /**
     * 修改银行卡识别信息
     */
    @ApiOperation(value = "修改银行卡识别信息",notes = "修改银行卡识别信息")
    @ApiImplicitParam(paramType = "query",dataType = "Bankcard",name = "bankcard",required = true)
    @PreAuthorize("@ss.hasPermi('system:bankcard:edit')")
    @Log(title = "银行卡识别信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Bankcard bankcard)
    {
        return toAjax(bankcardService.updateBankcard(bankcard));
    }

    /**
     * 删除银行卡识别信息
     */
    @ApiOperation(value = "删除银行卡识别信息",notes = "删除银行卡识别信息")
    @ApiImplicitParam(paramType = "query",dataType = "Long",name = "ids",value="ids",example = "1",required = true)
    @PreAuthorize("@ss.hasPermi('system:bankcard:remove')")
    @Log(title = "银行卡识别信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bankcardService.deleteBankcardByIds(ids));
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