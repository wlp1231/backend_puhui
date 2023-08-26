package com.qst.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qst.system.mapper.RiskMapper;
import com.qst.system.domain.Risk;
import com.qst.system.service.IRiskService;

/**
 * 风控管理Service业务层处理
 *
 * @author qst
 * @date 2022-01-24
 */
@Service
public class RiskServiceImpl implements IRiskService
{
    @Autowired
    private RiskMapper riskMapper;

    /**
     * 查询风控管理
     *
     * @param riskId 风控管理主键
     * @return 风控管理
     */
    @Override
    public Risk selectRiskByRiskId(Long riskId)
    {
        return riskMapper.selectRiskByRiskId(riskId);
    }

    /**
     * 查询风控管理列表
     *
     * @param risk 风控管理
     * @return 风控管理
     */
    @Override
    public List<Risk> selectRiskList(Risk risk)
    {
        return riskMapper.selectRiskList(risk);
    }

    /**
     * 新增风控管理
     *
     * @param risk 风控管理
     * @return 结果
     */
    @Override
    public int insertRisk(Risk risk)
    {
        return riskMapper.insertRisk(risk);
    }

    /**
     * 修改风控管理
     *
     * @param risk 风控管理
     * @return 结果
     */
    @Override
    public int updateRisk(Risk risk)
    {
        return riskMapper.updateRisk(risk);
    }

    /**
     * 批量删除风控管理
     *
     * @param riskIds 需要删除的风控管理主键
     * @return 结果
     */
    @Override
    public int deleteRiskByRiskIds(Long[] riskIds)
    {
        return riskMapper.deleteRiskByRiskIds(riskIds);
    }

    /**
     * 删除风控管理信息
     *
     * @param riskId 风控管理主键
     * @return 结果
     */
    @Override
    public int deleteRiskByRiskId(Long riskId)
    {
        return riskMapper.deleteRiskByRiskId(riskId);
    }
}