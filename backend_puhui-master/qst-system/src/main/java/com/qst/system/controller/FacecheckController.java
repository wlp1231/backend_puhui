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
import com.qst.system.domain.Facecheck;
import com.qst.system.service.IFacecheckService;
import com.qst.common.utils.poi.ExcelUtil;
import com.qst.common.core.page.TableDataInfo;

/**
 * 人脸识别记录Controller
 *
 * @author zpbao
 * @date 2023-08-26
 */
@Api("人脸识别控制类")
@RestController
@RequestMapping("/system/facecheck")
public class FacecheckController extends BaseController
{
    @Autowired
    private IFacecheckService facecheckService;

    /**
     * 查询人脸识别记录列表
     */
    @ApiOperation("查询人脸识别记录列表")
    @PreAuthorize("@ss.hasPermi('system:facecheck:list')")
    @GetMapping("/list")
    public TableDataInfo list(Facecheck facecheck)
    {
        startPage();
        System.out.println("facecheck--- 参数"+ facecheck);
        List<Facecheck> list = facecheckService.selectFacecheckList(facecheck);
        System.out.println("facecheck"+ list);
        return getDataTable(list);
    }

    /**
     * 导出人脸识别记录列表
     */
    @ApiOperation("导出人脸识别记录列表")
    @PreAuthorize("@ss.hasPermi('system:facecheck:export')")
    @Log(title = "人脸识别记录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Facecheck facecheck)
    {
        List<Facecheck> list = facecheckService.selectFacecheckList(facecheck);
        ExcelUtil<Facecheck> util = new ExcelUtil<Facecheck>(Facecheck.class);
        return util.exportExcel(list, "人脸识别记录数据");
    }

    /**
     * 获取人脸识别记录详细信息
     */
    @ApiOperation("获取人脸识别记录详细信息")
    @ApiImplicitParam(name = "id",value = "要查询记录的id",required = true,dataType ="Long",dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermi('system:facecheck:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(facecheckService.selectFacecheckById(id));
    }

    /**
     * 新增人脸识别记录
     */
    @ApiOperation("新增人脸识别记录")
    @PreAuthorize("@ss.hasPermi('system:facecheck:add')")
    @Log(title = "人脸识别记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Facecheck facecheck)
    {
        return toAjax(facecheckService.insertFacecheck(facecheck));
    }

    /**
     * 修改人脸识别记录
     */
    @ApiOperation("修改人脸识别记录")
    @PreAuthorize("@ss.hasPermi('system:facecheck:edit')")
    @Log(title = "人脸识别记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Facecheck facecheck)
    {
        return toAjax(facecheckService.updateFacecheck(facecheck));
    }

    /**
     * 删除人脸识别记录
     */
    @ApiOperation("删除人脸识别记录")
    @ApiImplicitParam(name = "ids",value = "要删除的记录的数组",required = true,dataType ="Long",dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermi('system:facecheck:remove')")
    @Log(title = "人脸识别记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(facecheckService.deleteFacecheckByIds(ids));
    }
}