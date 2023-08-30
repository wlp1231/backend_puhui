package com.qst.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qst.system.mapper.OverdueMapper;
import com.qst.system.domain.Overdue;
import com.qst.system.service.IOverdueService;

/**
 * 逾期Service业务层处理
 *
 * @author qst
 * @date 2021-09-28
 */
@Service
public class OverdueServiceImpl implements IOverdueService
{
    @Autowired
    private OverdueMapper overdueMapper;

    @Override
    public int countOverdue(String oveComNo) {
        return overdueMapper.countOverdue(oveComNo);
    }

    /**
     * 查询逾期
     *
     * @param oveId 逾期主键
     * @return 逾期
     */
    @Override
    public Overdue selectOverdueByOveId(Long oveId)
    {
        return overdueMapper.selectOverdueByOveId(oveId);
    }

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

    /**
     * 新增逾期
     *
     * @param overdue 逾期
     * @return 结果
     */
    @Override
    public int insertOverdue(Overdue overdue)
    {
        return overdueMapper.insertOverdue(overdue);
    }

    /**
     * 修改逾期
     *
     * @param overdue 逾期
     * @return 结果
     */
    @Override
    public int updateOverdue(Overdue overdue)
    {
        return overdueMapper.updateOverdue(overdue);
    }

    /**
     * 批量删除逾期
     *
     * @param oveIds 需要删除的逾期主键
     * @return 结果
     */
    @Override
    public int deleteOverdueByOveIds(Long[] oveIds)
    {
        return overdueMapper.deleteOverdueByOveIds(oveIds);
    }

    /**
     * 删除逾期信息
     *
     * @param oveId 逾期主键
     * @return 结果
     */
    @Override
    public int deleteOverdueByOveId(Long oveId)
    {
        return overdueMapper.deleteOverdueByOveId(oveId);
    }
}