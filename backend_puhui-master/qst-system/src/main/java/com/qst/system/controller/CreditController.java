package com.qst.system.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qst.system.domain.ScoreLevel;
import com.qst.system.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.checkerframework.checker.units.qual.A;
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
import com.qst.system.domain.Credit;
import com.qst.common.utils.poi.ExcelUtil;
import com.qst.common.core.page.TableDataInfo;

/**
 * 平台信用管理Controller
 *
 * @author zpbao
 * @date 2023-08-28
 */
@Api("平台信息控制类")
@RestController
@RequestMapping("/system/credit")
public class CreditController extends BaseController
{
    @Autowired
    private ICreditService creditService;

    @Autowired
    private ICompanyService companyService;

    @Autowired
    private IScoreLevelService scoreLevelService;

    @Autowired
    private ILoansService loansService;

    @Autowired
    private IRefundService refundService;

    @Autowired
    private IOverdueService overdueService;

    /**
     * 更新平台信用管理
     */
    @ApiOperation(value = "更新平台信用管理",notes = "更新平台信用管理")
    @PreAuthorize("@ss.hasPermi('system:credit:edit')")
    @PutMapping
    public void updateall()
    {
        /*1.从企业表中，查询出所有的企业id*/
        List<Integer> comcomIds = companyService.selectAllComIds();
        System.out.println("企业表中所有的企业id："+comcomIds);

        /*2.从平台信用列表中，查询出已经有的企业id*/
        List<Integer> crecomIds = creditService.selectAllComIds();
        //System.out.println("平台信用表中所有的企业id："+crecomIds);

        /*3.找出需要新增加到平台应用列表的企业id*/
        Map<Integer, Integer> map = new HashMap<Integer, Integer>(crecomIds.size());
        List<Integer> listNew = new ArrayList<Integer>();

        for (Integer i : crecomIds) {
            map.put(i,1);
        }

        for (Integer j : comcomIds) {
            if(map.get(j)==null){
                listNew.add(j);
            }
        }

        //System.out.println(listNew);

        /*4.对于已经在平台信用显示过的企业id执行更新操作*/
        for (Integer crecomId : crecomIds) {
            System.out.println(crecomId+" 已存在列表中的值进行update操作");/*将int类型转为Long类型*/
            Long lcom = new Long((long)crecomId);
            /*通过id查询积分值score_num和等级level_detail*/
            ScoreLevel scoreLevel = scoreLevelService.selectScoreLevelByComId(lcom);
            if(scoreLevel==null){
                scoreLevel = new ScoreLevel();
            }
            Long scoreNum = scoreLevel.getScoreNum();
            System.out.println("scoreNum="+scoreNum);
            String levelDetail = scoreLevel.getLevelDetail();
            System.out.println("levelDetail="+levelDetail);

            /*将int类型转为String类型*/
            String strcom = Integer.toString(crecomId);
            /*通过strcom进行三个表的查询*/
            int loanCount = loansService.countLoan(strcom);
            int refundCount = refundService.countRefund(strcom);
            int overdueCount = overdueService.countOverdue(strcom);
            System.out.println("三个值；"+loanCount+" "+refundCount+" "+overdueCount);

            /*信用状态*/
            String  creditState = setCreditState(overdueCount);
            System.out.println("creditState="+creditState);

            Credit credit = new Credit(lcom,scoreNum,levelDetail,loanCount,refundCount,overdueCount,creditState);

            int i = creditService.updateCredit(credit);
            System.out.println("更新成功"+i);
        }

        /*5.对于新的企业id，要进行新增操作*/

        for (Integer lnew : listNew) {
            System.out.println(lnew+" 新值准备insert操作");
            /*将int类型转为Long类型*/
            Long lcom = new Long((long)lnew);
            /*通过id查询积分值score_num和等级level_detail*/
            ScoreLevel scoreLevel = scoreLevelService.selectScoreLevelByComId(lcom);
            Long scoreNum = 0L;
            String levelDetail = "";
            if(scoreLevel != null){
                scoreNum = scoreLevel.getScoreNum();
                levelDetail = scoreLevel.getLevelDetail();
            }
            System.out.println("新增scoreNum="+scoreNum);
            System.out.println("新增levelDetail="+levelDetail);
            /*将int类型转为String类型*/
            String strcom = Integer.toString(lnew);
            /*通过strcom进行三个表的查询*/
            int loanCount = loansService.countLoan(strcom);
            int refundCount = refundService.countRefund(strcom);
            int overdueCount = overdueService.countOverdue(strcom);
            System.out.println("新增三个值；"+loanCount+" "+refundCount+" "+overdueCount);

            /*信用状态*/
            String  creditState = setCreditState(overdueCount);
            System.out.println("新增creditState="+creditState);

            Credit credit = new Credit(lcom,scoreNum,levelDetail,loanCount,refundCount,overdueCount,creditState);

            int i = creditService.insertCredit(credit);
            System.out.println("插入成功"+i);

        }

    }

    @ApiOperation(value = "定义信用状态方法",notes = "定义信用状态方法")
    @ApiImplicitParam(paramType = "query",dataType = "int",name = "odc",required = true)
    /*定义信用状态方法：信用状态（1正常 2警告 3停止）*/
    public String setCreditState(int odc){
        String code="";
        if(odc<=1){
            code = "正常";
        }if(odc>1&&odc<5){
            code = "警告";
        }if(odc>=5){
            code = "停止";
        }
        return code;
    }

    /**
     * 查询平台信用管理列表
     */
    @ApiOperation(value = "平台信用管理列表",notes = "显示全部的台信用数据")
    @ApiImplicitParam(paramType = "query",dataType = "Credit",name = "credit",required = true)
    @PreAuthorize("@ss.hasPermi('system:credit:list')")
    @GetMapping("/list")
    public TableDataInfo list(Credit credit)
    {
        startPage();
        List<Credit> list = creditService.selectCreditList(credit);
        return getDataTable(list);
    }

    /**
     * 导出平台信用管理列表
     */
    @ApiOperation(value = "导出平台信用列表",notes = "导出全部的平台信用信息")
    @ApiImplicitParam(paramType = "query",dataType = "Credit",name = "credit",required = true)
    @PreAuthorize("@ss.hasPermi('system:credit:export')")
    @Log(title = "平台信用管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Credit credit)
    {
        List<Credit> list = creditService.selectCreditList(credit);
        ExcelUtil<Credit> util = new ExcelUtil<Credit>(Credit.class);
        return util.exportExcel(list, "平台信用管理数据");
    }

    /**
     * 获取平台信用管理详细信息
     */
    @ApiOperation(value = "查看平台信用数据",notes = "查看平台信用数据")
    @ApiImplicitParam(paramType = "query",dataType = "Long",name = "comId",value="comId",example = "1",required = true)
    @PreAuthorize("@ss.hasPermi('system:credit:query')")
    @GetMapping(value = "/{comId}")
    public AjaxResult getInfo(@PathVariable("comId") Long comId)
    {
        return AjaxResult.success(creditService.selectCreditByComId(comId));
    }





}