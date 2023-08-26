package com.qst.system.service;

import java.util.List;
import com.qst.system.domain.Score;

/**
 * 用户积分Service接口
 *
 * @author qst
 * @date 2022-06-27
 */
public interface IScoreService
{
    /**
     * 查询用户积分
     *
     * @param scoreId 用户积分主键
     * @return 用户积分
     */
    public Score selectScoreByScoreId(Long scoreId);

    /**
     * 查询用户积分列表
     *
     * @param score 用户积分
     * @return 用户积分集合
     */
    public List<Score> selectScoreList(Score score);

    /**
     * 新增用户积分
     *
     * @param score 用户积分
     * @return 结果
     */
    public int insertScore(Score score);

    /**
     * 修改用户积分
     *
     * @param score 用户积分
     * @return 结果
     */
    public int updateScore(Score score);

    /**
     * 批量删除用户积分
     *
     * @param scoreIds 需要删除的用户积分主键集合
     * @return 结果
     */
    public int deleteScoreByScoreIds(Long[] scoreIds);

    /**
     * 删除用户积分信息
     *
     * @param scoreId 用户积分主键
     * @return 结果
     */
    public int deleteScoreByScoreId(Long scoreId);
}