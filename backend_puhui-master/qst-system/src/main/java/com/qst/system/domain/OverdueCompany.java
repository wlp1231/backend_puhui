package com.qst.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qst.common.annotation.Excel;
import com.qst.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 逾期企业对象 overduecompany
 *
 * @author qst
 * @date 2021-09-28
 */
@ApiModel("逾期企业对象")
public class OverdueCompany extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 企业编号 */
    @ApiModelProperty("企业编号")
    private String oveComNo;

    /** 企业名称 */
    @ApiModelProperty("企业名称")
    private String oveComName;


    /** 逾期总金额 */
    @ApiModelProperty("逾期总金额")
    private Long oveAllMoney;

    /** 逾期总次数 */
    @ApiModelProperty("逾期总次数")
    private Long oveAllCount;

    /** 逾期已还款次数 */
    @ApiModelProperty("逾期已还款次数")
    private Long oveEndCount;

    /** 逾期未还款次数 */
    @ApiModelProperty("逾期未还款次数")
    private Long ovedueCount;

    public String getOveComNo() {
        return oveComNo;
    }

    public void setOveComNo(String oveComNo) {
        this.oveComNo = oveComNo;
    }

    public String getOveComName() {
        return oveComName;
    }

    public void setOveComName(String oveComName) {
        this.oveComName = oveComName;
    }

    public Long getOveAllMoney() {
        return oveAllMoney;
    }

    public void setOveAllMoney(Long oveAllMoney) {
        this.oveAllMoney = oveAllMoney;
    }

    public Long getOveAllCount() {
        return oveAllCount;
    }

    public void setOveAllCount(Long oveAllCount) {
        this.oveAllCount = oveAllCount;
    }

    public Long getOveEndCount() {
        return oveEndCount;
    }

    public void setOveEndCount(Long oveEndCount) {
        this.oveEndCount = oveEndCount;
    }

    public Long getOvedueCount() {
        return ovedueCount;
    }

    public void setOvedueCount(Long ovedueCount) {
        this.ovedueCount = ovedueCount;
    }


}
