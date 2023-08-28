package com.qst.system.service.impl;

import com.qst.system.domain.Overdue;
import com.qst.system.mapper.OverdueChartMapper;
import com.qst.system.mapper.OverdueMapper;
import com.qst.system.service.IOverdueChartService;
import com.qst.system.service.IOverdueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 逾期Service业务层处理
 *
 * @author qst
 * @date 2021-09-28
 */
@Service
public class OverdueChartServiceImpl implements IOverdueChartService
{
    @Autowired
    private OverdueChartMapper overdueMapper;


    /**
     * 查询逾期列表
     *
     * @param overdue 逾期
     * @return 逾期
     */
    @Override
    public List<Overdue> selectOverdueList(Overdue overdue)
    {
        return overdueMapper.selectOverdueList(overdue);
    }
}
