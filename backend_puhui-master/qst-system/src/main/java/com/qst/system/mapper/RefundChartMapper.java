package com.qst.system.mapper;

import com.qst.system.domain.Refund;

import java.util.List;

/**
 * 还款Mapper接口
 *
 * @author qst
 * @date 2021-09-27
 */
public interface RefundChartMapper
{
    /**
     * 查询还款列表
     *
     * @param refund 还款
     * @return 还款集合
     */
    public List<Refund> selectRefundList(Refund refund);

}