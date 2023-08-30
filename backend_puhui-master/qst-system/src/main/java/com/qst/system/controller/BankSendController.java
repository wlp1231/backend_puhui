package com.qst.system.controller;

import java.util.Date;
import java.util.List;
import com.qst.system.domain.Bank;
import com.qst.system.domain.Refund;
import com.qst.system.service.IBankService;
import com.qst.system.service.ILoansService;
import com.qst.system.service.IRefundService;
import com.qst.system.util.ConstUtil;
import com.qst.system.util.MyDateUtils;
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
import com.qst.system.domain.BankSend;
import com.qst.system.service.IBankSendService;
import com.qst.common.utils.poi.ExcelUtil;
import com.qst.common.core.page.TableDataInfo;

/**
 * 放款Controller
 *
 * @author qst
 * @date 2021-09-27
 */
@Api("放款控制类")
@RestController
@RequestMapping("/system/send")
public class BankSendController extends BaseController
{
    @Autowired
    private IBankSendService bankSendService;

    @Autowired
    private ILoansService loansService;

    @Autowired
    private IRefundService refundService;

    @Autowired
    private IBankService bankService; // 2022.1.21 add by ly



    /**
     * 查询放款列表
     */
    @ApiOperation("查询放款列表")
    @PreAuthorize("@ss.hasPermi('system:send:list')")
    @GetMapping("/list")
    public TableDataInfo list(BankSend bankSend)
    {
        startPage();
        List<BankSend> list = bankSendService.selectBankSendList(bankSend);
        return getDataTable(list);
    }

    /**
     * 导出放款列表
     */
    @ApiOperation("导出放款列表")
    @PreAuthorize("@ss.hasPermi('system:send:export')")
    @Log(title = "放款", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BankSend bankSend)
    {
        List<BankSend> list = bankSendService.selectBankSendList(bankSend);
        ExcelUtil<BankSend> util = new ExcelUtil<BankSend>(BankSend.class);
        return util.exportExcel(list, "放款数据");
    }

    /**
     * 获取放款详细信息
     */
    @ApiOperation("获取放款详细信息")
    @ApiImplicitParam(name = "senId",value = "要查询的放款id",required = true,dataType ="Long",dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermi('system:send:query')")
    @GetMapping(value = "/{senId}")
    public AjaxResult getInfo(@PathVariable("senId") Long senId)
    {
        return AjaxResult.success(bankSendService.selectBankSendBySenId(senId));
    }

    /**
     * 新增放款
     */
    @ApiOperation("新增放款")
    @PreAuthorize("@ss.hasPermi('system:send:add')")
    @Log(title = "放款", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BankSend bankSend)
    {
        return toAjax(bankSendService.insertBankSend(bankSend));
    }

    /**
     * 修改放款
     */
    @ApiOperation("修改放款")
    @PreAuthorize("@ss.hasPermi('system:send:edit')")
    @Log(title = "放款", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BankSend bankSend)
    {
        return toAjax(bankSendService.updateBankSend(bankSend));
    }

    /**
     * 删除放款
     */
    @ApiOperation("删除放款")
    @ApiImplicitParam(name = "senIds",value = "要查询的放款id的数组",required = true,dataType ="Long",dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermi('system:send:remove')")
    @Log(title = "放款", businessType = BusinessType.DELETE)
    @DeleteMapping("/{senIds}")
    public AjaxResult remove(@PathVariable Long[] senIds)
    {
        return toAjax(bankSendService.deleteBankSendBySenIds(senIds));
    }


    /**
     * 状态修改
     */
    @ApiOperation("状态修改")
    @PreAuthorize("@ss.hasPermi('system:send:edit')")
    @Log(title = "放款管理", businessType = BusinessType.UPDATE)
    @PutMapping("/changeSendStatus")
    public AjaxResult changeStatus(@RequestBody BankSend bankSend)
    {

        // bankSend.getSenStatus() 2 放款,   1:停止放款
        Date date = new Date();
        if("2".equals(bankSend.getSenStatus())){
            // 放款
            Refund refund = new Refund();
            refund.setRefBankId(bankSend.getSenBankId());
            refund.setRefLoId(bankSend.getSenLoaId());
            refund.setRefComNo(bankSend.getSenComId());

            Bank bank = bankService.selectBankByBankId(bankSend.getSenBankId()); // 获得银行, 从而得到 放款周期
            String month = bank.getBankLength(); // 放款周期

            Date d = MyDateUtils.change(date,month);
            refund.setRefTime(d);
            // 获得贷款金额
            Long money = Long.valueOf(bankSendService.selectBankSendBySenId(bankSend.getSenId()).getSenMoney());
            refund.setRefMoney(money);
            refund.setRefMode(ConstUtil.REFUND_MODE); //还款方式为  无
            refundService.insertRefund(refund);// 添加还款记录,
        }
        loansService.updateLoaSenState(bankSend.getSenLoaId());
        bankSend.setUpdateBy(getUsername());
        bankSend.setSenTime(date); // 修改放款时间
        return toAjax(bankSendService.updateBankSend(bankSend));
    }




}