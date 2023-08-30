package com.qst.system.controller;

import com.qst.common.core.controller.BaseController;
import com.qst.common.core.page.TableDataInfo;
import com.qst.system.domain.Examine;
import com.qst.system.service.IExamineChartService;
import com.qst.system.service.ILoansService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 审批Controller
 *
 * @author qst
 * @date 2021-09-23
 */
@Api("审批-图表控制类")
@RestController
@RequestMapping("/system/examinechart")
public class ExamineChartController extends BaseController
{
    @Autowired
    private IExamineChartService examinechartService;

    @Autowired
    private ILoansService loansService;


    /**
     * 查询审批列表
     */
    @ApiOperation(value = "贷款审批统计列表",notes = "显示贷款审批数据信息")
    @ApiImplicitParam(paramType = "query",dataType = "Examine",name = "examine",required = true)
    @PreAuthorize("@ss.hasPermi('system:examine:list')")
    @GetMapping("/list")
    public TableDataInfo list(Examine examine)
    {
        startPage();
        List<Examine> list = examinechartService.selectExamineList(examine);
        return getDataTable(list);
    }

}