package com.qst.system.controller;

import java.util.Date;
import java.util.List;

import com.qst.system.domain.Overdue;
import com.qst.system.service.IOverdueService;
import com.qst.system.util.ConstUtil;
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
import com.qst.system.domain.Refund;
import com.qst.system.service.IRefundService;
import com.qst.common.utils.poi.ExcelUtil;
import com.qst.common.core.page.TableDataInfo;

/**
 * 还款Controller
 *
 * @author qst
 * @date 2021-09-27
 */
@Api("还款控制类")
@RestController
@RequestMapping("/system/refund")
public class RefundController extends BaseController
{
    @Autowired
    private IRefundService refundService;


    @Autowired
    private IOverdueService overdueService;

    /**
     * 查询还款列表
     */
    @ApiOperation("查询还款列表")
    @PreAuthorize("@ss.hasPermi('system:refund:list')")
    @GetMapping("/list")
    public TableDataInfo list(Refund refund)
    {
        startPage();
        List<Refund> list = refundService.selectRefundList(refund);
        return getDataTable(list);
    }

    /**
     * 导出还款列表
     */
    @ApiOperation("导出还款列表")
    @PreAuthorize("@ss.hasPermi('system:refund:export')")
    @Log(title = "还款", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Refund refund)
    {
        List<Refund> list = refundService.selectRefundList(refund);
        ExcelUtil<Refund> util = new ExcelUtil<Refund>(Refund.class);
        return util.exportExcel(list, "还款数据");
    }

    /**
     * 获取还款详细信息
     */
    @ApiOperation("获取还款详细信息")
    @ApiImplicitParam(name = "refId",value = "要查询的记录id",required = true,dataType ="Long",dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermi('system:refund:query')")
    @GetMapping(value = "/{refId}")
    public AjaxResult getInfo(@PathVariable("refId") Long refId)
    {
        return AjaxResult.success(refundService.selectRefundByRefId(refId));
    }

    /**
     * 新增还款
     */
    @ApiOperation("新增还款")
    @PreAuthorize("@ss.hasPermi('system:refund:add')")
    @Log(title = "还款", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Refund refund)
    {
        return toAjax(refundService.insertRefund(refund));
    }

    /**
     * 修改还款
     */
    @ApiOperation("修改还款")
    @PreAuthorize("@ss.hasPermi('system:refund:edit')")
    @Log(title = "还款", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Refund refund)
    {
        // 2022.7.14 按照客户要求修改, 手动已逾期后 需要向逾期表增加一天记录
        // 如果 修改为 已逾期 ,需要向 overdue中增加记录 ,从前台接受 类型如果为3 表示 已逾期
        if("3".equals(refund.getRefState())){
            // 为逾期表 增加 记录
            Overdue due = new Overdue();
            due.setOveComNo(refund.getRefComNo());
            due.setOveLoaId(refund.getRefLoId());
            due.setOveMoney(refund.getRefMoney());
            due.setOveStartTime(new Date());
            due.setOveCutCount(ConstUtil.COUNT);
            due.setOveDel(ConstUtil.DEL_SIGN);
            due.setOveAddInt(ConstUtil.RATE); // 逾期利息
            overdueService.insertOverdue(due);
        }

        return toAjax(refundService.updateRefund(refund));
    }

    /**
     * 删除还款
     */
    @ApiOperation("删除还款")
    @ApiImplicitParam(name = "refIds",value = "要删除的还款id的数组",required = true,dataType ="Long",dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermi('system:refund:remove')")
    @Log(title = "还款", businessType = BusinessType.DELETE)
    @DeleteMapping("/{refIds}")
    public AjaxResult remove(@PathVariable Long[] refIds)
    {
        return toAjax(refundService.deleteRefundByRefIds(refIds));
    }
}