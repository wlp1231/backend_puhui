package com.qst.system.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qst.common.annotation.Excel;
import com.qst.common.core.domain.BaseEntity;

/**
 * 平台信用管理对象 credit
 *
 * @author zpbao
 * @date 2023-08-28
 */
@ApiModel(description = "平台信用管理对象")
public class Credit extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 企业ID */
    @ApiModelProperty(value = "企业ID")
    private Long comId;

    /** 企业名称 */
    @ApiModelProperty(value = "企业名称")
    private String comName;

    /** 积分值 */
    @ApiModelProperty(value = "积分值")
    @Excel(name = "积分值")
    private Long scoreNum;

    /** 等级 */
    @ApiModelProperty(value = "等级")
    @Excel(name = "等级")
    private String levelDetail;

    /** 贷款总次数 */
    @ApiModelProperty(value = "贷款总次数")
    @Excel(name = "贷款总次数")
    private Integer loanCount;

    /** 还款次数 */
    @ApiModelProperty(value = "还款次数")
    @Excel(name = "还款次数")
    private Integer refundCount;

    /** 逾期次数 */
    @ApiModelProperty(value = "逾期次数")
    @Excel(name = "逾期次数")
    private Integer overdueCount;

    /** 信用状态（1正常、2警告、3停止） */
    @ApiModelProperty(value = "信用状态（1正常、2警告、3停止）")
    @Excel(name = "信用状态", readConverterExp = "1=正常、2警告、3停止")
    private String creditState;

    public void setComId(Long comId)
    {
        this.comId = comId;
    }

    public Long getComId()
    {
        return comId;
    }
    public void setScoreNum(Long scoreNum)
    {
        this.scoreNum = scoreNum;
    }

    public Long getScoreNum()
    {
        return scoreNum;
    }
    public void setLevelDetail(String levelDetail)
    {
        this.levelDetail = levelDetail;
    }

    public String getLevelDetail()
    {
        return levelDetail;
    }
    public void setLoanCount(Integer loanCount)
    {
        this.loanCount = loanCount;
    }

    public Integer getLoanCount()
    {
        return loanCount;
    }
    public void setRefundCount(Integer refundCount)
    {
        this.refundCount = refundCount;
    }

    public Integer getRefundCount()
    {
        return refundCount;
    }
    public void setOverdueCount(Integer overdueCount)
    {
        this.overdueCount = overdueCount;
    }

    public Integer getOverdueCount()
    {
        return overdueCount;
    }
    public void setCreditState(String creditState)
    {
        this.creditState = creditState;
    }

    public String getCreditState()
    {
        return creditState;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public Credit(Long scoreNum, String levelDetail, Integer loanCount, Integer refundCount, Integer overdueCount, String creditState) {
        this.scoreNum = scoreNum;
        this.levelDetail = levelDetail;
        this.loanCount = loanCount;
        this.refundCount = refundCount;
        this.overdueCount = overdueCount;
        this.creditState = creditState;
    }

    public Credit() {
    }

    public Credit(Long comId, Long scoreNum, String levelDetail, Integer loanCount, Integer refundCount, Integer overdueCount, String creditState) {
        this.comId = comId;
        this.scoreNum = scoreNum;
        this.levelDetail = levelDetail;
        this.loanCount = loanCount;
        this.refundCount = refundCount;
        this.overdueCount = overdueCount;
        this.creditState = creditState;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("comId", getComId())
                .append("scoreNum", getScoreNum())
                .append("levelDetail", getLevelDetail())
                .append("loanCount", getLoanCount())
                .append("refundCount", getRefundCount())
                .append("overdueCount", getOverdueCount())
                .append("updateTime", getUpdateTime())
                .append("creditState", getCreditState())
                .toString();
    }
}