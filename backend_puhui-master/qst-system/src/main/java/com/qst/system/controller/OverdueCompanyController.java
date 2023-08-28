package com.qst.system.controller;

import com.qst.common.annotation.Log;
import com.qst.common.core.controller.BaseController;
import com.qst.common.core.domain.AjaxResult;
import com.qst.common.core.page.TableDataInfo;
import com.qst.common.enums.BusinessType;
import com.qst.common.utils.poi.ExcelUtil;
import com.qst.system.domain.Overdue;
import com.qst.system.domain.OverdueCompany;
import com.qst.system.service.IOverdueCompanyService;
import com.qst.system.service.IOverdueService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 逾期Controller
 *
 * @author qst
 * @date 2021-09-28
 */
@Api("逾期控制类")
@RestController
@RequestMapping("/system/overduecompany")
public class OverdueCompanyController extends BaseController
{
    @Autowired
    private IOverdueCompanyService overdueCompanyService;

    /**
     * 查询逾期企业列表
     */
    @ApiOperation("查询逾期企业列表")
    @PreAuthorize("@ss.hasPermi('system:overduecompany:list')")
    @GetMapping("/list")
    public TableDataInfo list(OverdueCompany overdueCompany)
    {
        startPage();
        List<OverdueCompany> list = overdueCompanyService.selectOverdueCompanyList(overdueCompany);
        return getDataTable(list);
    }

}
