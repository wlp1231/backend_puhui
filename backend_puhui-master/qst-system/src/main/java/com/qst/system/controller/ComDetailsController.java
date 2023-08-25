package com.qst.system.controller;

import com.qst.common.annotation.Log;
import com.qst.common.core.controller.BaseController;
import com.qst.common.core.domain.AjaxResult;
import com.qst.common.core.domain.entity.Provinces;
import com.qst.common.core.page.TableDataInfo;
import com.qst.common.enums.BusinessType;
import com.qst.common.utils.poi.ExcelUtil;
import com.qst.system.domain.ComDetails;
import com.qst.system.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 企业详情Controller
 *
 * @author wen
 * @date 2021-09-11
 */
@Api("企业详情控制类")
@RestController
@RequestMapping("/system/details")
public class ComDetailsController extends BaseController {
    @Autowired
    private IComDetailsService comDetailsService;

    @Autowired
    private IProvincesService provincesService;

    @Autowired
    private ICitiesService citiesService;

    @Autowired
    private ICompanyService companyService;

    @Autowired
    private IAreasService areasService;



    /**
     * 查询企业详情列表
     */
    @ApiOperation("查询企业详情列表")
    @PreAuthorize("@ss.hasPermi('system:details:list')")
    @GetMapping("/list")
    public TableDataInfo list(ComDetails comDetails) {
        startPage();
        List<ComDetails> list = comDetailsService.selectComDetailsList(comDetails);
        return getDataTable(list);
    }

    /**
     * 导出企业详情列表
     */
    @ApiOperation("导出企业详情列表")
    @PreAuthorize("@ss.hasPermi('system:details:export')")
    @Log(title = "企业详情", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ComDetails comDetails) {
        List<ComDetails> list = comDetailsService.selectComDetailsList(comDetails);
        ExcelUtil<ComDetails> util = new ExcelUtil<ComDetails>(ComDetails.class);
        return util.exportExcel(list, "企业详情数据");
    }

    /**
     * 获取企业详情详细信息
     */
    @ApiOperation("获取企业详情详细信息")
    @ApiImplicitParam(name = "cId",value = "要查询的企业id",required = true,dataType ="Long",dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermi('system:details:query')")
    @GetMapping(value = "/{cId}")
    public AjaxResult getInfo(@PathVariable("cId") Long cId) {
        AjaxResult ajax = AjaxResult.success();
        ajax.put(AjaxResult.DATA_TAG, comDetailsService.selectComDetailsByCId(cId));
        return ajax;
    }

    /**
     * 获取企业详情详细信息
     */
    @ApiOperation("获取企业详情详细信息")
    @ApiImplicitParam(name = "coId",value = "企业id",required = true,dataType ="String",dataTypeClass = String.class)
    @PreAuthorize("@ss.hasPermi('system:details:query')")
    @GetMapping(value = "/comName/{coId}")
    public AjaxResult getInfoCompanyName(@PathVariable("coId") String coId) {
        AjaxResult ajax = AjaxResult.success();
        ajax.put("companiesName", companyService.selectCompanyByComId(Long.parseLong(coId)).getComName());
        return ajax;
    }

    /**
     * 查看企业实名详情
     */
    @ApiOperation("查看企业实名详情")
    @ApiImplicitParam(name = "cId",value = "企业详情id",required = true,dataType ="Long",dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermi('system:details:query')")
    @Log(title = "详情查询", businessType = BusinessType.GRANT)
    @GetMapping("/companies/{cId}")
    public AjaxResult selectCompanies(@PathVariable Long cId)
    {
        return AjaxResult.success(comDetailsService.selectComDetailsByCId(cId));
    }

    /**
     * 获取未实名公司列表
     */
    @ApiOperation("获取未实名公司列表")
    @GetMapping("/companyName")
    public AjaxResult getInfoCompanyName() {
        return AjaxResult.success(companyService.selectCompanyName());
    }
    /**
     * 获取省详情
     */
    @ApiOperation("按照省id获得省信息")
    @ApiImplicitParam(name = "cPro",value = "要查询的省id",required = true,dataType ="String",dataTypeClass = String.class)
    @GetMapping("/proCom/{cPro}")
    public AjaxResult getProCompany(@PathVariable String cPro) {
        AjaxResult ajax = AjaxResult.success();
        ajax.put("provincIds", provincesService.selectProById(cPro));


        return ajax;
    }
    //  2022.1.21 修改  by ly  因为 异步请求 因此一次 返回 省 市 区
    // @ApiOperation("一次返回所有的省市区列表")
    @GetMapping("/proCom/{cPro}/{cCity}/{cArea}")
    public AjaxResult getProCompany(@PathVariable String cPro,@PathVariable String cCity,@PathVariable String cArea) {
        AjaxResult ajax = AjaxResult.success();
        List<String> list = new ArrayList<>();
        list.add(provincesService.selectProById(cPro).getProvinceid());
        list.add(citiesService.selectCityById(cCity).getCityid());
        list.add(areasService.selectAreaById(cArea).getAreaid());


        ajax.put("info",list);


        return ajax;
    }





    // @ApiOperation("按照市id获取市信息")
    @ApiImplicitParam(name = "cCity",value = "市id",required = true,dataType ="String",dataTypeClass = String.class)
    @GetMapping("/proCity/{cCity}")
    public AjaxResult getCityCompany(@PathVariable String cCity) {
        AjaxResult ajax = AjaxResult.success();

        System.out.println("---日日日日---cCity-----"+cCity);
        ajax.put("cityIds", citiesService.selectCityById(cCity));
        return ajax;
    }

    //  @ApiOperation("按照区id获取区信息")
    @ApiImplicitParam(name = "cArea",value = "区id",required = true,dataType ="String",dataTypeClass = String.class)
    @GetMapping("/proArea/{cArea}")
    public AjaxResult getAreaCompany(@PathVariable String cArea) {
        AjaxResult ajax = AjaxResult.success();

        System.out.println("---日日日日---cArea-----"+cArea);
        ajax.put("areaIds", areasService.selectAreaById(cArea));
        return ajax;
    }

    /**
     * 获取省列表
     */
    @GetMapping("/pro")
    @ApiOperation("获取省列表")
    public AjaxResult getInfoPro() {
        AjaxResult ajax = AjaxResult.success();

        //查询省及市
        ajax.put("provincs", provincesService.selectProvincesAndCity());

        // ajax.put("provincs", provincesService.selectProvincesAll());
        return ajax;
    }

    /**
     * 获取市列表
     */
    @ApiOperation("获取市列表")
    @ApiImplicitParam(name = "cTy",value = "省id",required = true,dataType ="String",dataTypeClass = String.class)
    @GetMapping("/cit/{cTy}")
    public AjaxResult getInfoCity(@PathVariable String cTy) {
        AjaxResult ajax = AjaxResult.success();
        ajax.put("citys", citiesService.selectCitiesByProId(cTy));
        return ajax;
    }

    /**
     * 新增企业详情
     */
    @ApiOperation("新增企业详情")
    @PreAuthorize("@ss.hasPermi('system:details:add')")
    @Log(title = "企业详情", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ComDetails comDetails) {
        companyService.updateCompanyStatus(comDetails.getCoId());
        return toAjax(comDetailsService.insertComDetails(comDetails));
    }

    /**
     * 修改企业详情
     */
    @ApiOperation("修改企业详情")
    @PreAuthorize("@ss.hasPermi('system:details:edit')")
    @Log(title = "企业详情", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ComDetails comDetails) {
        return toAjax(comDetailsService.updateComDetails(comDetails));
    }

    /**
     * 删除企业详情
     */
    @ApiOperation("删除企业详情")
    @ApiImplicitParam(name = "cIds",value = "要删除的企业详情id的数组",required = true,dataType ="Long",dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermi('system:details:remove')")
    @Log(title = "企业详情", businessType = BusinessType.DELETE)
    @DeleteMapping("/{cIds}")
    public AjaxResult remove(@PathVariable Long[] cIds) {
        return toAjax(comDetailsService.deleteComDetailsByCIds(cIds));
    }

}