package com.qst.system.mapper;

import java.util.List;
import com.qst.system.domain.Examine;

/**
 * 审批Mapper接口
 *
 * @author qst
 * @date 2021-09-23
 */
public interface ExamineMapper
{
    /**
     * 查询审批
     *
     * @param exaId 审批主键
     * @return 审批
     */
    public Examine selectExamineByExaId(Long exaId);

    /**
     * 查询审批
     *
     * @param eId 审批主键
     * @return 审批
     */
    List<Examine> selectExamineById(Long eId);

    /**
     * 查询审批列表
     *
     * @param examine 审批
     * @return 审批集合
     */
    public List<Examine> selectExamineList(Examine examine);

    /**
     * 新增审批
     *
     * @param examine 审批
     * @return 结果
     */
    public int insertExamine(Examine examine);

    /**
     * 修改审批
     *
     * @param examine 审批
     * @return 结果
     */
    public int updateExamine(Examine examine);

    /**
     * 删除审批
     *
     * @param exaId 审批主键
     * @return 结果
     */
    public int deleteExamineByExaId(Long exaId);

    /**
     * 批量删除审批
     *
     * @param exaIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteExamineByExaIds(Long[] exaIds);
}