package com.qst.web.controller.system;

import java.util.List;

import com.qst.common.core.domain.entity.Provinces;
import io.swagger.annotations.Api;
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
import com.qst.system.service.IProvincesService;
import com.qst.common.utils.poi.ExcelUtil;
import com.qst.common.core.page.TableDataInfo;

/**
 * 【省】Controller
 * 
 * @author qst
 * @date 2021-09-15
 */
@Api("省控制类")
@RestController
@RequestMapping("/system/provinces")
public class ProvincesController extends BaseController
{
    @Autowired
    private IProvincesService provincesService;

    /**
     * 查询省列表
     */
    @ApiOperation("查询省列表")
    @PreAuthorize("@ss.hasPermi('system:provinces:list')")
    @GetMapping("/list")
    public TableDataInfo list(Provinces provinces)
    {
        startPage();
        List<Provinces> list = provincesService.selectProvincesList(provinces);
        return getDataTable(list);
    }

    /**
     * 导出省列表
     */
    @ApiOperation("导出省列表")
    @PreAuthorize("@ss.hasPermi('system:provinces:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Provinces provinces)
    {
        List<Provinces> list = provincesService.selectProvincesList(provinces);
        ExcelUtil<Provinces> util = new ExcelUtil<Provinces>(Provinces.class);
        return util.exportExcel(list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @ApiOperation("按照id查询省列表")
    @PreAuthorize("@ss.hasPermi('system:provinces:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(provincesService.selectProvincesById(id));
    }

    /**
     * 新增省
     */
    @ApiOperation("新增省")
    @PreAuthorize("@ss.hasPermi('system:provinces:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Provinces provinces)
    {
        return toAjax(provincesService.insertProvinces(provinces));
    }

    /**
     * 修改【省
     */
    @ApiOperation("修改省")
    @PreAuthorize("@ss.hasPermi('system:provinces:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Provinces provinces)
    {
        return toAjax(provincesService.updateProvinces(provinces));
    }

    /**
     * 删除省
     */
    @ApiOperation("删除省")
    @PreAuthorize("@ss.hasPermi('system:provinces:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(provincesService.deleteProvincesByIds(ids));
    }
}
