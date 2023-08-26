package com.qst.system.mapper;

import java.util.List;
import com.qst.system.domain.Risk;

/**
 * 风控管理Mapper接口
 *
 * @author qst
 * @date 2022-01-24
 */
public interface RiskMapper
{
    /**
     * 查询风控管理
     *
     * @param riskId 风控管理主键
     * @return 风控管理
     */
    public Risk selectRiskByRiskId(Long riskId);

    /**
     * 查询风控管理列表
     *
     * @param risk 风控管理
     * @return 风控管理集合
     */
    public List<Risk> selectRiskList(Risk risk);

    /**
     * 新增风控管理
     *
     * @param risk 风控管理
     * @return 结果
     */
    public int insertRisk(Risk risk);

    /**
     * 修改风控管理
     *
     * @param risk 风控管理
     * @return 结果
     */
    public int updateRisk(Risk risk);

    /**
     * 删除风控管理
     *
     * @param riskId 风控管理主键
     * @return 结果
     */
    public int deleteRiskByRiskId(Long riskId);

    /**
     * 批量删除风控管理
     *
     * @param riskIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteRiskByRiskIds(Long[] riskIds);
}