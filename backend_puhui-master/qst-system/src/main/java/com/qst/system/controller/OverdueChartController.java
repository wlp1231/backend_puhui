package com.qst.system.controller;

import com.qst.common.core.controller.BaseController;
import com.qst.common.core.page.TableDataInfo;
import com.qst.system.domain.Overdue;
import com.qst.system.service.IOverdueChartService;
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
@Api("逾期图表控制类")
@RestController
@RequestMapping("/system/overduechart")
public class OverdueChartController extends BaseController
{
    @Autowired
    private IOverdueChartService overdueService;

    /**
     * 查询逾期列表
     */
    @ApiOperation(value = "逾期数据统计列表",notes = "显示逾期数据信息")
    @ApiImplicitParam(paramType = "query",dataType = "Overdue",name = "overdue",required = true)
    @PreAuthorize("@ss.hasPermi('system:overdue:list')")
    @GetMapping("/list")
    public TableDataInfo list(Overdue overdue)
    {
        startPage();
        List<Overdue> list = overdueService.selectOverdueList(overdue);
        return getDataTable(list);
    }

}
