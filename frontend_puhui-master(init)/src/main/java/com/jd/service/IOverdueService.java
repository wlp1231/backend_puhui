package com.jd.service;

import com.jd.bean.Overdue;

import java.util.List;

/**
 * 逾期Service接口
 *
 * @author qst
 * @date 2021-09-28
 */
public interface IOverdueService
{
    /**
     * 查询逾期
     *
     * @param oveId 逾期主键
     * @return 逾期
     */
    public Overdue selectOverdueByOveId(Long oveId);

    /**
     * 查询逾期列表
     *
     * @param overdue 逾期
     * @return 逾期集合
     */
    public List<Overdue> selectOverdueList(Overdue overdue);

    /**
     * 新增逾期
     *
     * @param overdue 逾期
     * @return 结果
     */
    public int insertOverdue(Overdue overdue);

    /**
     * 修改逾期
     *
     * @param overdue 逾期
     * @return 结果
     */
    public int updateOverdue(Overdue overdue);

    /**
     * 批量删除逾期
     *
     * @param oveIds 需要删除的逾期主键集合
     * @return 结果
     */
    public int deleteOverdueByOveIds(Long[] oveIds);

    /**
     * 删除逾期信息
     *
     * @param oveId 逾期主键
     * @return 结果
     */
    public int deleteOverdueByOveId(Long oveId);

    /**
     * 按公司查询逾期表
     * @param cId
     * @return
     */
    public List<Overdue> selectOverdueComId(String cId);
}