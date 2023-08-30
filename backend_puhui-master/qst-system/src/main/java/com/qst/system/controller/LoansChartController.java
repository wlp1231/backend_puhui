package com.qst.system.controller;

import com.qst.common.core.controller.BaseController;
import com.qst.common.core.page.TableDataInfo;
import com.qst.system.domain.Loans;
import com.qst.system.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 贷款Controller
 *
 * @author qst
 * @date 2021-09-23
 */
@Api(tags = "贷款申请统计类")
@RestController
@RequestMapping("/system/loanschart")
public class LoansChartController extends BaseController
{
    @Autowired
    private ILoansChartService loansService;
    /**
     * 查询贷款列表
     */
    @ApiOperation(value = "贷款申请统计列表",notes = "显示贷款申请数据信息")
    @ApiImplicitParam(paramType = "query",dataType = "Loans",name = "loans",required = true)
    @PreAuthorize("@ss.hasPermi('system:loans:list')")
    @GetMapping("/list")
    public TableDataInfo list(Loans loans)
    {
        startPage();
        List<Loans> list = loansService.selectLoansList(loans);
        System.err.println(list);
        return getDataTable(list);
    }

}