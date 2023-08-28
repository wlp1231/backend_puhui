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
import com.qst.system.domain.Aptitude;
import com.qst.system.service.IAptitudeService;
import com.qst.common.utils.poi.ExcelUtil;
import com.qst.common.core.page.TableDataInfo;

/**
 * 资质Controller
 *
 * @author zpbao
 * @date 2023-08-26
 */
@Api("资质控制类")
@RestController
@RequestMapping("/system/aptitude")
public class AptitudeController extends BaseController
{
    @Autowired
    private IAptitudeService aptitudeService;

    @Autowired
    private ICompanyService companyService;

    /**
     * 查询资质列表
     */
    @ApiOperation("查询资质列表")
    @PreAuthorize("@ss.hasPermi('system:aptitude:list')")
    @GetMapping("/list")
    public TableDataInfo list(Aptitude aptitude)
    {
        startPage();
        List<Aptitude> list = aptitudeService.selectAptitudeList(aptitude);
        return getDataTable(list);
    }

    /**
     * 查询公司列表
     */
    @ApiOperation("查询公司列表")
    @GetMapping("/comName")
    public AjaxResult listComName()
    {
        return AjaxResult.success(companyService.selectCompanyNameAutonym());
    }

    /**
     * 导出资质列表
     */
    @ApiOperation("导出资质列表")
    @PreAuthorize("@ss.hasPermi('system:aptitude:export')")
    @Log(title = "资质", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Aptitude aptitude)
    {
        List<Aptitude> list = aptitudeService.selectAptitudeList(aptitude);
        ExcelUtil<Aptitude> util = new ExcelUtil<Aptitude>(Aptitude.class);
        return util.exportExcel(list, "资质数据");
    }

    /**
     * 获取资质详细信息
     */
    @ApiOperation("获取资质详细信息")
    @ApiImplicitParam(name = "aptId",value = "要查询的资质id",required = true,dataType ="Long",dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermi('system:aptitude:query')")
    @GetMapping(value = "/{aptId}")
    public AjaxResult getInfo(@PathVariable("aptId") Long aptId)
    {
        return AjaxResult.success(aptitudeService.selectAptitudeByAptId(aptId));
    }

    /**
     * 新增资质
     */
    @ApiOperation("新增资质")
    @PreAuthorize("@ss.hasPermi('system:aptitude:add')")
    @Log(title = "资质", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Aptitude aptitude)
    {
        return toAjax(aptitudeService.insertAptitude(aptitude));
    }

    /**
     * 修改资质
     */
    @ApiOperation("修改资质")
    @PreAuthorize("@ss.hasPermi('system:aptitude:edit')")
    @Log(title = "资质", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Aptitude aptitude)
    {
        return toAjax(aptitudeService.updateAptitude(aptitude));
    }

    /**
     * 删除资质
     */
    @ApiOperation("删除资质")
    @ApiImplicitParam(name = "aptIds",value = "要删除的资质id的数组",required = true,dataType ="Long",dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermi('system:aptitude:remove')")
    @Log(title = "资质", businessType = BusinessType.DELETE)
    @DeleteMapping("/{aptIds}")
    public AjaxResult remove(@PathVariable Long[] aptIds)
    {
        return toAjax(aptitudeService.deleteAptitudeByAptIds(aptIds));
    }
}