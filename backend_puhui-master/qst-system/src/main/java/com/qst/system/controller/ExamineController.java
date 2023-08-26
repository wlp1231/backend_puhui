package com.qst.system.controller;

import java.util.List;

import com.qst.common.core.domain.entity.SysUser;
import com.qst.common.core.domain.model.LoginUser;
import com.qst.system.service.ILoansService;
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
import com.qst.system.domain.Examine;
import com.qst.system.service.IExamineService;
import com.qst.common.utils.poi.ExcelUtil;
import com.qst.common.core.page.TableDataInfo;

/**
 * 审批Controller
 *
 * @author qst
 * @date 2021-09-23
 */
@Api("审批控制类")
@RestController
@RequestMapping("/system/examine")
public class ExamineController extends BaseController
{
    @Autowired
    private IExamineService examineService;

    @Autowired
    private ILoansService loansService;


    /**
     * 查询审批列表
     */
    @ApiOperation("查询审批列表")
    @PreAuthorize("@ss.hasPermi('system:examine:list')")
    @GetMapping("/list")
    public TableDataInfo list(Examine examine)
    {
        startPage();
        List<Examine> list = examineService.selectExamineList(examine);
        return getDataTable(list);
    }



    /**
     * 导出审批列表
     */
    @ApiOperation("导出审批列表")
    @PreAuthorize("@ss.hasPermi('system:examine:export')")
    @Log(title = "审批", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Examine examine)
    {
        List<Examine> list = examineService.selectExamineList(examine);
        ExcelUtil<Examine> util = new ExcelUtil<Examine>(Examine.class);
        return util.exportExcel(list, "审批数据");
    }

    /**
     * 获取审批详细信息
     */
    @ApiOperation("获取审批详细信息")
    @ApiImplicitParam(name = "exaId",value = "要查询的审批id",required = true,dataType ="Long",dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermi('system:examine:query')")
    @GetMapping(value = "/{exaId}")
    public AjaxResult getInfo(@PathVariable("exaId") Long exaId)
    {
        return AjaxResult.success(examineService.selectExamineByExaId(exaId));
    }

    /**
     * 通过审批
     */
    @ApiOperation("通过审批")
    @PreAuthorize("@ss.hasPermi('system:examine:add')")
    @Log(title = "审批", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Examine examine)
    {
        //查询登录日志的最后一条数据，获得用户名
        LoginUser loginUser = getLoginUser();
        SysUser user = loginUser.getUser();
        examine.setExaName(user.getUserName());
        examine.setExaState("0");
        loansService.updateLoansById(examine.getExaLoaId());
        return toAjax(examineService.insertExamine(examine));
    }

    /**
     * 驳回审批
     */
    @ApiOperation("驳回审批")
    @PreAuthorize("@ss.hasPermi('system:examine:add')")
    @Log(title = "审批", businessType = BusinessType.INSERT)
    @PostMapping("/no")
    public AjaxResult addNoPass(@RequestBody Examine examine)
    {
        //查询登录日志的最后一条数据，获得用户名
        LoginUser loginUser = getLoginUser();
        SysUser user = loginUser.getUser();
        examine.setExaName(user.getUserName());
        loansService.updateLoansNoById(examine.getExaLoaId());
        examine.setExaState("1");
        return toAjax(examineService.insertExamine(examine));
    }

    /**
     * 通过审批
     */
    @ApiOperation("银行通过审批")
    @PreAuthorize("@ss.hasPermi('system:examine:add')")
    @Log(title = "审批", businessType = BusinessType.INSERT)
    @PostMapping("/bank")
    public AjaxResult addBankLoan(@RequestBody Examine examine)
    {
        LoginUser loginUser = getLoginUser();
        SysUser user = loginUser.getUser();
        examine.setExaName(user.getUserName());
        examine.setExaState("3");
        loansService.updateBankLoansById(examine.getExaLoaId());
        return toAjax(examineService.insertExamine(examine));
    }

    /**
     * 驳回审批
     */
    @ApiOperation("银行驳回审批")
    @PreAuthorize("@ss.hasPermi('system:examine:add')")
    @Log(title = "审批", businessType = BusinessType.INSERT)
    @PostMapping("/bankNo")
    public AjaxResult addBankNoPass(@RequestBody Examine examine)
    {
        //查询登录日志的最后一条数据，获得用户名
        LoginUser loginUser = getLoginUser();
        SysUser user = loginUser.getUser();
        examine.setExaName(user.getUserName());
        loansService.updateBankLoansNoById(examine.getExaLoaId());
        examine.setExaState("4");
        return toAjax(examineService.insertExamine(examine));
    }

    /**
     * 修改审批
     */
    @ApiOperation("修改审批")
    @PreAuthorize("@ss.hasPermi('system:examine:edit')")
    @Log(title = "审批", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Examine examine)
    {
        return toAjax(examineService.updateExamine(examine));
    }

    /**
     * 删除审批
     */
    @ApiOperation("删除审批")
    @ApiImplicitParam(name = "exaIds",value = "要删除的审批id的数组",required = true,dataType ="Long",dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermi('system:examine:remove')")
    @Log(title = "审批", businessType = BusinessType.DELETE)
    @DeleteMapping("/{exaIds}")
    public AjaxResult remove(@PathVariable Long[] exaIds)
    {
        return toAjax(examineService.deleteExamineByExaIds(exaIds));
    }
}