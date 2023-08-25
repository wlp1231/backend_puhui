package com.qst.web.controller.system;

import java.util.List;

import com.qst.common.core.domain.entity.Cities;
import com.qst.system.service.ICitiesService;
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
import com.qst.common.utils.poi.ExcelUtil;
import com.qst.common.core.page.TableDataInfo;

/**
 * 市Controller
 * 
 * @author qst
 * @date 2021-09-15
 */
@Api("市控制器")
@RestController
@RequestMapping("/common/cities")
public class CitiesController extends BaseController
{
    @Autowired
    private ICitiesService citiesService;

    /**
     * 查询市列表
     */
    @ApiOperation("查询市列表")
    @PreAuthorize("@ss.hasPermi('common:cities:list')")
    @GetMapping("/list")
    public TableDataInfo list(Cities cities)
    {
        startPage();
        List<Cities> list = citiesService.selectCitiesList(cities);
        return getDataTable(list);
    }

    /**
     * 导出市列表
     */
    @ApiOperation("导出市列表")
    @PreAuthorize("@ss.hasPermi('common:cities:export')")
    @Log(title = "市", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Cities cities)
    {
        List<Cities> list = citiesService.selectCitiesList(cities);
        ExcelUtil<Cities> util = new ExcelUtil<Cities>(Cities.class);
        return util.exportExcel(list, "市数据");
    }

    /**
     * 获取市详细信息
     */
    @ApiOperation("获取市详细信息")
    @PreAuthorize("@ss.hasPermi('common:cities:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(citiesService.selectCitiesById(id));
    }

    /**
     * 新增市
     */
    @ApiOperation("新增市")
    @PreAuthorize("@ss.hasPermi('common:cities:add')")
    @Log(title = "市", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Cities cities)
    {
        return toAjax(citiesService.insertCities(cities));
    }

    /**
     * 修改市
     */
    @ApiOperation("修改市")
    @PreAuthorize("@ss.hasPermi('common:cities:edit')")
    @Log(title = "市", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Cities cities)
    {
        return toAjax(citiesService.updateCities(cities));
    }

    /**
     * 删除市
     */
    @ApiOperation("删除市")
    @PreAuthorize("@ss.hasPermi('common:cities:remove')")
    @Log(title = "市", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(citiesService.deleteCitiesByIds(ids));
    }
}
