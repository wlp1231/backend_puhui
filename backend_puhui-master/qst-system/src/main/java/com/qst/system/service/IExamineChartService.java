package com.qst.system.service;

import com.qst.system.domain.Examine;

import java.util.List;

/**
 * 审批Service接口
 *
 * @author qst
 * @date 2021-09-23
 */
public interface IExamineChartService
{
    /**
     * 查询审批列表
     *
     * @param examine 审批
     * @return 审批集合
     */
    public List<Examine> selectExamineList(Examine examine);

}