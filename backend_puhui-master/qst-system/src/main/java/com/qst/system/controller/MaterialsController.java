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
import com.qst.system.domain.Materials;
import com.qst.system.service.IMaterialsService;
import com.qst.common.utils.poi.ExcelUtil;
import com.qst.common.core.page.TableDataInfo;

/**
 * 材料Controller
 *
 * @author zpbao
 * @date 2023-08-26
 */
@Api("材料控制类")
@RestController
@RequestMapping("/system/materials")
public class MaterialsController extends BaseController
{
    @Autowired
    private IMaterialsService materialsService;

    /**
     * 查询材料列表
     */
    @ApiOperation("查询材料列表")
    @PreAuthorize("@ss.hasPermi('system:materials:list')")
    @GetMapping("/list")
    public TableDataInfo list(Materials materials)
    {
        startPage();
        List<Materials> list = materialsService.selectMaterialsList(materials);
        return getDataTable(list);
    }

    /**
     * 导出材料列表
     */
    @ApiOperation("导出材料列表")
    @PreAuthorize("@ss.hasPermi('system:materials:export')")
    @Log(title = "材料", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Materials materials)
    {
        List<Materials> list = materialsService.selectMaterialsList(materials);
        ExcelUtil<Materials> util = new ExcelUtil<Materials>(Materials.class);
        return util.exportExcel(list, "材料数据");
    }

    /**
     * 获取材料详细信息
     */
    @ApiOperation("获取材料详细信息")
    @ApiImplicitParam(name = "lisId",value = "要查询的材料业id",required = true,dataType ="Long",dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermi('system:materials:query')")
    @GetMapping(value = "/{lisId}")
    public AjaxResult getInfo(@PathVariable("lisId") Long lisId)
    {
        return AjaxResult.success(materialsService.selectMaterialsByLisId(lisId));
    }

    /**
     * 新增材料
     */
    @ApiOperation("新增材料")
    @PreAuthorize("@ss.hasPermi('system:materials:add')")
    @Log(title = "材料", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Materials materials)
    {
        return toAjax(materialsService.insertMaterials(materials));
    }

    /**
     * 修改材料
     */
    @ApiOperation("修改材料")
    @PreAuthorize("@ss.hasPermi('system:materials:edit')")
    @Log(title = "材料", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Materials materials)
    {
        return toAjax(materialsService.updateMaterials(materials));
    }

    /**
     * 删除材料
     */
    @ApiOperation("删除材料")
    @ApiImplicitParam(name = "lisIds",value = "要删除的材料id的数组",required = true,dataType ="Long",dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermi('system:materials:remove')")
    @Log(title = "材料", businessType = BusinessType.DELETE)
    @DeleteMapping("/{lisIds}")
    public AjaxResult remove(@PathVariable Long[] lisIds)
    {
        return toAjax(materialsService.deleteMaterialsByLisIds(lisIds));
    }
}