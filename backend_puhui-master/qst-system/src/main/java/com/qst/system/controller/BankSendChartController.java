package com.qst.system.controller;

import com.qst.common.core.controller.BaseController;
import com.qst.common.core.domain.AjaxResult;
import com.qst.common.core.page.TableDataInfo;
import com.qst.system.domain.BankSend;
import com.qst.system.service.IBankSendChartService;
import com.qst.system.service.ILoansService;
import com.qst.system.service.IRefundService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 放款Controller
 *
 * @author qst
 * @date 2021-09-27
 */
@Api("放款图表控制类")
@RestController
@RequestMapping("/system/sendchart")
public class BankSendChartController extends BaseController
{
    @Autowired
    private IBankSendChartService bankSendService;

    @Autowired
    private ILoansService loansService;

    @Autowired
    private IRefundService refundService;

    /**
     * 查询放款列表
     */
    @ApiOperation(value = "放款数据统计列表",notes = "显示放款数据信息")
    @ApiImplicitParam(paramType = "query",dataType = "BankSend",name = "bankSend",required = true)
    @PreAuthorize("@ss.hasPermi('system:send:list')")
    @GetMapping("/list")
    public TableDataInfo list(BankSend bankSend)
    {
        startPage();
        List<BankSend> list = bankSendService.selectBankSendList(bankSend);
        return getDataTable(list);
    }
    /**
     * 查询放款 数据 -用折线图显示
     */
    @ApiOperation("查询放款 数据 -用折线图显示")
    @PreAuthorize("@ss.hasPermi('system:send:list')")
    @GetMapping("/chart")
    public AjaxResult chart()
    {
        Map<String,Object> map = new HashMap<>();
        List<String> names = new ArrayList<>();
        List<String> mons = new ArrayList<>();
        List<BankSend> list = bankSendService.selectBankSendList(null);
        if(list !=null && list.size()>0){
            for (int i = 0; i < list.size(); i++) {
                BankSend bs = list.get(i);
                names.add(bs.getCompany().getComName());
                mons.add(bs.getSenMoney());
            }
        }
        map.put("expectedData",names);
        map.put("actualData",mons);
        return AjaxResult.success(map);
    }

}