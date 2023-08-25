package com.qst.web.controller.system;

import java.util.List;

import com.qst.common.core.domain.entity.Areas;
import com.qst.system.service.IAreasService;
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
 * 区Controller
 * 
 * @author qst
 * @date 2021-09-22
 */
@Api("区控制类")
@RestController
@RequestMapping("/common/areas")
public class AreasController extends BaseController
{
    @Autowired
    private IAreasService areasService;

    /**
     * 查询区列表
     */
    @ApiOperation("查询区列表")
    @PreAuthorize("@ss.hasPermi('common:areas:list')")
    @GetMapping("/list")
    public TableDataInfo list(Areas areas)
    {
        startPage();
        List<Areas> list = areasService.selectAreasList(areas);
        return getDataTable(list);
    }

    /**
     * 导出区列表
     */
    @ApiOperation("导出区列表")
    @PreAuthorize("@ss.hasPermi('common:areas:export')")
    @Log(title = "区", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Areas areas)
    {
        List<Areas> list = areasService.selectAreasList(areas);
        ExcelUtil<Areas> util = new ExcelUtil<Areas>(Areas.class);
        return util.exportExcel(list, "区数据");
    }

    /**
     * 获取区详细信息
     */
    @ApiOperation("获取区详细信息")
    @ApiImplicitParam(name = "id",value = "要查询的区id",required = true,dataType ="Long",dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermi('common:areas:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(areasService.selectAreasById(id));
    }

    /**
     * 新增区
     */
    @ApiOperation("新增区")
    @PreAuthorize("@ss.hasPermi('common:areas:add')")
    @Log(title = "区", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Areas areas)
    {
        return toAjax(areasService.insertAreas(areas));
    }

    /**
     * 修改区
     */
    @ApiOperation("修改区")
    @PreAuthorize("@ss.hasPermi('common:areas:edit')")
    @Log(title = "区", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Areas areas)
    {
        return toAjax(areasService.updateAreas(areas));
    }

    /**
     * 删除区
     */
    @ApiOperation("删除区")
    @ApiImplicitParam(name = "ids",value = "要删除的区id的数组",required = true,dataType ="Long",dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermi('common:areas:remove')")
    @Log(title = "区", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(areasService.deleteAreasByIds(ids));
    }
}
