package com.qst.system.mapper;

import com.qst.system.domain.Examine;

import java.util.List;

/**
 * 审批Mapper接口
 *
 * @author qst
 * @date 2021-09-23
 */
public interface ExamineChartMapper
{


    /**
     * 查询审批列表
     *
     * @param examine 审批
     * @return 审批集合
     */
    public List<Examine> selectExamineList(Examine examine);

}