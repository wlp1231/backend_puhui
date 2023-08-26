package com.qst.system.controller;

import java.util.List;

import com.qst.system.service.ICreditInvestService;
import com.qst.system.service.ILoansService;
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
import com.qst.system.domain.Risk;
import com.qst.system.service.IRiskService;
import com.qst.common.core.page.TableDataInfo;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * 风控管理Controller
 *
 * @author qst
 * @date 2022-01-24
 */
@Api(tags = "风控数据和风控查询接口")
@RestController
@RequestMapping("/system/risk")
public class RiskController extends BaseController
{
    @Autowired
    private IRiskService riskService;

    @Resource
    private RestTemplate restTemplate;

    @Autowired
    private ILoansService loansService;

    @Autowired
    private ICreditInvestService creditInvestService;

    /**
     * 查询风控管理列表
     */
    @ApiOperation(value = "风控数据和风控查询列表",notes = "显示全部的风控数据信息")
    @ApiImplicitParam(paramType = "query",dataType = "Risk",name = "risk",required = true)
    @PreAuthorize("@ss.hasPermi('system:risk:list')")
    @GetMapping("/list")
    public TableDataInfo list(Risk risk)
    {
        startPage();
        List<Risk> list = riskService.selectRiskList(risk);
        System.out.println(list.toString());
        return getDataTable(list);
    }

    /**
     * 计算额度
     */
    @ApiOperation(value = "风控数据计算额度",notes = "调用微服务计算额度")
    @ApiImplicitParam(paramType = "query",dataType = "Long",name = "riskId",value="risk id",example = "1",required = true)
    @PreAuthorize("@ss.hasPermi('system:risk:calc')")
    @Log(title = "风控管理", businessType = BusinessType.UPDATE)
    @GetMapping("/calc/{riskId}")
    public Risk calc(@PathVariable Long riskId)
    {
        String url = "http://risk-service-provider/risk/"+riskId;
        Risk risk = restTemplate.getForObject(url, Risk.class);
        return risk;
    }

    /**
     * 查询征信
     */
    @ApiOperation(value = "查询征信状态",notes = "查询征信状态")
    @ApiImplicitParam(paramType = "query",dataType = "Long",name = "riskId",value="risk id",example = "1",required = true)
    @PreAuthorize("@ss.hasPermi('system:risk:invest')")
    @Log(title = "风控管理", businessType = BusinessType.UPDATE)
    @GetMapping("/invest/{riskId}")
    public void invest(@PathVariable Long riskId)
    {
        /*通过riskId找到贷款编号*/
        Risk risk = riskService.selectRiskByRiskId(riskId);
        String loaNo = risk.getLoaNo();
        /*通过贷款编号找到企业编号*/
        Long lcn = loansService.selectLoaComNo(loaNo);
        /*Long类型转为String类型*/
        String loaComNo = String.valueOf(lcn);
        /*通过企业编号找到征信状态*/
        String creInvesState = creditInvestService.selectCreInvesState(loaComNo);
        /*将征信状态字段的数据存入风控表中*/
        if(creInvesState== null){
            creInvesState = "";
        }
        Risk risknew = new Risk(riskId,creInvesState);
        edit(risknew);


    }


    /**
     * 获取风控管理详细信息
     */
    @ApiOperation(value = "查看风控信息",notes = "查看风控信息")
    @ApiImplicitParam(paramType = "query",dataType = "Long",name = "riskId",value="risk id",example = "1",required = true)
    @PreAuthorize("@ss.hasPermi('system:risk:query')")
    @GetMapping(value = "/{riskId}")
    public AjaxResult getInfo(@PathVariable("riskId") Long riskId)
    {
        return AjaxResult.success(riskService.selectRiskByRiskId(riskId));
    }

    /**
     * 新增风控管理
     */
    @ApiOperation(value = "新增风控数据",notes = "新增风控数据")
    @ApiImplicitParam(paramType = "query",dataType = "Risk",name = "risk",required = true)
    @PreAuthorize("@ss.hasPermi('system:risk:add')")
    @Log(title = "风控管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Risk risk)
    {
        return toAjax(riskService.insertRisk(risk));
    }

    /**
     * 修改风控管理
     */
    @ApiOperation(value = "修改风控数据",notes = "修改风控数据")
    @ApiImplicitParam(paramType = "query",dataType = "Risk",name = "risk",required = true)
    @PreAuthorize("@ss.hasPermi('system:risk:edit')")
    @Log(title = "风控管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Risk risk)
    {

        return toAjax(riskService.updateRisk(risk));

    }

    /**
     * 删除风控管理
     */
    @ApiOperation(value = "删除风控数据",notes = "删除风控数据")
    @ApiImplicitParam(paramType = "query",dataType = "Long",name = "riskId",value="risk id",example = "1",required = true)
    @PreAuthorize("@ss.hasPermi('system:risk:remove')")
    @Log(title = "风控管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{riskIds}")
    public AjaxResult remove(@PathVariable Long[] riskIds)
    {
        return toAjax(riskService.deleteRiskByRiskIds(riskIds));
    }
}