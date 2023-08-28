package com.qst.system.controller;

import com.qst.common.core.controller.BaseController;
import com.qst.common.core.page.TableDataInfo;
import com.qst.system.domain.Refund;
import com.qst.system.service.IRefundService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 还款Controller
 *
 * @author qst
 * @date 2021-09-27
 */
@Api("还款图表控制类")
@RestController
@RequestMapping("/system/refundchart")
public class RefundChartController extends BaseController
{
    @Autowired
    private IRefundService refundService;

    /**
     * 查询还款列表
     */
    @ApiOperation(value = "还款数据统计列表",notes = "显示还款数据信息")
    @ApiImplicitParam(paramType = "query",dataType = "Refund",name = "refund",required = true)
    @PreAuthorize("@ss.hasPermi('system:refund:list')")
    @GetMapping("/list")
    public TableDataInfo list(Refund refund)
    {
        startPage();
        List<Refund> list = refundService.selectRefundList(refund);
        return getDataTable(list);
    }

}