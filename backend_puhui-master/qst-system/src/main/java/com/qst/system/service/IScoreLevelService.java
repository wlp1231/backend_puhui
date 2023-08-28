package com.qst.system.service;

import java.util.List;
import com.qst.system.domain.ScoreLevel;

/**
 * 用户等级Service接口
 *
 * @author qst
 * @date 2022-06-27
 */
public interface IScoreLevelService
{
    /**
     * 查询用户等级
     *
     * @param scoreId 用户等级主键
     * @return 用户等级
     */
    public ScoreLevel selectScoreLevelByScoreId(Long scoreId);

    /**
     * 查询用户等级表
     *
     * @param comId 公司id
     * @return 用户等级表
     */
    public ScoreLevel selectScoreLevelByComId(Long comId);

    /**
     * 查询用户等级列表
     *
     * @param scoreLevel 用户等级
     * @return 用户等级集合
     */
    public List<ScoreLevel> selectScoreLevelList(ScoreLevel scoreLevel);

    /**
     * 新增用户等级
     *
     * @param scoreLevel 用户等级
     * @return 结果
     */
    public int insertScoreLevel(ScoreLevel scoreLevel);

    /**
     * 修改用户等级
     *
     * @param scoreLevel 用户等级
     * @return 结果
     */
    public int updateScoreLevel(ScoreLevel scoreLevel);

    /**
     * 批量删除用户等级
     *
     * @param scoreIds 需要删除的用户等级主键集合
     * @return 结果
     */
    public int deleteScoreLevelByScoreIds(Long[] scoreIds);

    /**
     * 删除用户等级信息
     *
     * @param scoreId 用户等级主键
     * @return 结果
     */
    public int deleteScoreLevelByScoreId(Long scoreId);
}