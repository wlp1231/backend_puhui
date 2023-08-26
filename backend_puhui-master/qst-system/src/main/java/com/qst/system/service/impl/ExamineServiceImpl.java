package com.qst.system.service.impl;


import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qst.system.mapper.ExamineMapper;
import com.qst.system.domain.Examine;
import com.qst.system.service.IExamineService;

/**
 * 审批Service业务层处理
 *
 * @author qst
 * @date 2021-09-23
 */
@Service
public class ExamineServiceImpl implements IExamineService
{
    @Autowired
    private ExamineMapper examineMapper;

    /**
     * 查询审批
     *
     * @param exaId 审批主键
     * @return 审批
     */
    @Override
    public Examine selectExamineByExaId(Long exaId)
    {
        return examineMapper.selectExamineByExaId(exaId);
    }

    /**
     * 查询审批
     *
     * @param eId 审批主键
     * @return 审批
     */
    @Override
    public List<Examine> selectExamineById(Long eId) {
        return examineMapper.selectExamineById(eId);
    }

    /**
     * 查询审批列表
     *
     * @param examine 审批
     * @return 审批
     */
    @Override
    public List<Examine> selectExamineList(Examine examine)
    {
        return examineMapper.selectExamineList(examine);
    }

    /**
     * 通过新增审批
     *
     * @param examine 审批
     * @return 结果
     */
    @Override
    public int insertExamine(Examine examine)
    {
        Date date = new Date();
        examine.setExaTime(date);
        return examineMapper.insertExamine(examine);
    }

    /**
     * 修改审批
     *
     * @param examine 审批
     * @return 结果
     */
    @Override
    public int updateExamine(Examine examine)
    {
        return examineMapper.updateExamine(examine);
    }

    /**
     * 批量删除审批
     *
     * @param exaIds 需要删除的审批主键
     * @return 结果
     */
    @Override
    public int deleteExamineByExaIds(Long[] exaIds)
    {
        return examineMapper.deleteExamineByExaIds(exaIds);
    }

    /**
     * 删除审批信息
     *
     * @param exaId 审批主键
     * @return 结果
     */
    @Override
    public int deleteExamineByExaId(Long exaId)
    {
        return examineMapper.deleteExamineByExaId(exaId);
    }
}