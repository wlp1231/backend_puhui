package com.qst.system.controller;

import java.util.List;

import com.qst.system.domain.*;
import com.qst.system.service.*;
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
import com.qst.common.utils.poi.ExcelUtil;
import com.qst.common.core.page.TableDataInfo;

/**
 * 贷款Controller
 *
 * @author qst
 * @date 2021-09-23
 */
@Api("贷款控制类")
@RestController
@RequestMapping("/system/loans")
public class LoansController extends BaseController
{
    @Autowired
    private ILoansService loansService;

    @Autowired
    private IMaterialsService materialsService;

    @Autowired
    private IAptitudeService aptitudeService;

    @Autowired
    private IBankService bankService;

    @Autowired
    private IBankSendService bankSendService;

    /**
     * 查询贷款列表
     */
    @ApiOperation("查询贷款列表")
    @PreAuthorize("@ss.hasPermi('system:loans:list')")
    @GetMapping("/list")
    public TableDataInfo list(Loans loans)
    {
        startPage();
        List<Loans> list = loansService.selectLoansList(loans);
        return getDataTable(list);
    }

    /**
     * 查询银行贷款列表
     */
    @ApiOperation("查询银行贷款列表")
    @PreAuthorize("@ss.hasPermi('system:loans:list')")
    @GetMapping("/bankLoans")
    public TableDataInfo bankLoans(Loans loans)
    {
        startPage();
        List<Loans> list = loansService.selectBankLoansList(loans);
        return getDataTable(list);
    }

    /**
     * 导出贷款列表
     */
    @ApiOperation("导出贷款列表")
    @PreAuthorize("@ss.hasPermi('system:loans:export')")
    @Log(title = "贷款", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Loans loans)
    {
        List<Loans> list = loansService.selectLoansList(loans);
        ExcelUtil<Loans> util = new ExcelUtil<Loans>(Loans.class);
        return util.exportExcel(list, "贷款数据");
    }

    /**
     * 查询审批贷款列表
     */
    @ApiOperation("查询审批贷款列表")
    @PreAuthorize("@ss.hasPermi('system:loans:loan')")
    @GetMapping("/loansById/{eId}")
    public TableDataInfo loanById(@PathVariable Long eId)
    {
        startPage();
        List<Loans> list = loansService.selectLoansById(eId);
        return getDataTable(list);
    }

    /**
     * 查询银行列表
     */
    @ApiOperation("查询银行列表")
    @GetMapping("/bankName")
    public AjaxResult listComName()
    {
        return AjaxResult.success(bankService.selectBankNames());
    }
    /**
     * 查询贷款列表
     */
    @ApiOperation("查询贷款列表")
    @GetMapping("/loansNo")
    public AjaxResult listLoansNo()
    {
        return AjaxResult.success(loansService.selectLoansNoAll());
    }

    /**
     * 查询审批材料列表
     */
    @ApiOperation("查询审批材料列表")
    @PreAuthorize("@ss.hasPermi('system:loans:loan')")
    @GetMapping("/materialsById/{eId}")
    public TableDataInfo materialsById(@PathVariable Long eId)
    {
        startPage();
        List<Materials> list = materialsService.selectMaterialsById(eId);
        return getDataTable(list);
    }
    /**
     * 查询审批资质列表
     */
    @ApiOperation("查询审批资质列表")
    @ApiImplicitParam(name = "eId",value = "要查询的记录的id",required = true,dataType ="Long",dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermi('system:loans:loan')")
    @GetMapping("/aptitudeById/{eId}")
    public TableDataInfo aptitudeById(@PathVariable Long eId)
    {
        startPage();
        List<Aptitude> list = aptitudeService.selectAptitudeById(eId);
        return getDataTable(list);
    }

    /**
     * 获取贷款详细信息
     */
    @ApiOperation("获取贷款详细信息")
    @ApiImplicitParam(name = "loaId",value = "要查询的贷款id",required = true,dataType ="Long",dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermi('system:loans:query')")
    @GetMapping(value = "/{loaId}")
    public AjaxResult getInfo(@PathVariable("loaId") Long loaId)
    {
        return AjaxResult.success(loansService.selectLoansByLoaId(loaId));
    }

    /**
     * 新增贷款
     */
    @ApiOperation("新增贷款")
    @PreAuthorize("@ss.hasPermi('system:loans:add')")
    @Log(title = "贷款", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Loans loans)
    {
        return toAjax(loansService.insertLoans(loans));
    }

    /**
     * 修改贷款
     */
    @ApiOperation("修改贷款")
    @PreAuthorize("@ss.hasPermi('system:loans:edit')")
    @Log(title = "贷款", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Loans loans) {

        // 待放款----2022.1.22 添加
        if("6".equals(loans.getLoaState())){
            //  为banksend 添加一条记录,添加 放款记录
            BankSend bs = new BankSend();

            //组装数据- 放款时间为 null
            bs.setSenBankId(loans.getLoaBankId());//银行id
            bs.setSenLoaId(loans.getLoaId());//贷款id
            bs.setSenComId(Long.valueOf(loans.getLoaComNo()));//公司id
            bs.setSenMoney(loans.getLoaMoney().toString());// 贷款金额
            bs.setSenStatus(ConstUtil.SEND_STATUS); //未发放
            bs.setSenMode(ConstUtil.SEND_MODE); // 无
            bs.setSenDel(ConstUtil.SEND_SIGN);// 设置 正常标识 0

            // 设置放款信息-----------等着loans 添加字段
            bs.setSenAccount(loans.getbAccountNo()); //账号
            bs.setSenUname(loans.getbName()); // 名字
            bs.setSenUid(loans.getbIdCard()); //身份证号


            bankSendService.insertBankSend(bs);

        }
        return toAjax(loansService.updateLoans(loans));
    }

    /**
     * 删除贷款
     */
    @ApiOperation("删除贷款")
    @ApiImplicitParam(name = "loaIds",value = "要删除的贷款id的数组",required = true,dataType ="Long",dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermi('system:loans:remove')")
    @Log(title = "贷款", businessType = BusinessType.DELETE)
    @DeleteMapping("/{loaIds}")
    public AjaxResult remove(@PathVariable Long[] loaIds)
    {
        return toAjax(loansService.deleteLoansByLoaIds(loaIds));
    }
}