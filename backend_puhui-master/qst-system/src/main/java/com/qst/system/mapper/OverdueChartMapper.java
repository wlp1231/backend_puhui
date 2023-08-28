package com.qst.system.mapper;

import com.qst.system.domain.Overdue;

import java.util.List;

/**
 * 逾期Mapper接口
 *
 * @author qst
 * @date 2021-09-28
 */
public interface OverdueChartMapper
{
    /**
     * 查询逾期列表
     *
     * @param overdue 逾期
     * @return 逾期集合
     */
    public List<Overdue> selectOverdueList(Overdue overdue);

}
