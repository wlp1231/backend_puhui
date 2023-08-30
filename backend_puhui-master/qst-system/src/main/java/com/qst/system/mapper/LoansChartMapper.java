package com.qst.system.mapper;

import com.qst.system.domain.Loans;

import java.util.List;

/**
 * 贷款Mapper接口
 *
 * @author qst
 * @date 2021-09-23
 */
public interface LoansChartMapper
{
    /**
     * 查询贷款列表
     *
     * @param loans 贷款
     * @return 贷款集合
     */
    public List<Loans> selectLoansList(Loans loans);

}