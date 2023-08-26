package com.qst.system.service.impl;

import java.util.List;
import com.qst.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qst.system.mapper.ScoreMapper;
import com.qst.system.domain.Score;
import com.qst.system.service.IScoreService;

/**
 * 用户积分Service业务层处理
 *
 * @author qst
 * @date 2022-06-27
 */
@Service
public class ScoreServiceImpl implements IScoreService
{
    @Autowired
    private ScoreMapper scoreMapper;

    /**
     * 查询用户积分
     *
     * @param scoreId 用户积分主键
     * @return 用户积分
     */
    @Override
    public Score selectScoreByScoreId(Long scoreId)
    {
        return scoreMapper.selectScoreByScoreId(scoreId);
    }

    /**
     * 查询用户积分列表
     *
     * @param score 用户积分
     * @return 用户积分
     */
    @Override
    public List<Score> selectScoreList(Score score)
    {
        return scoreMapper.selectScoreList(score);
    }

    /**
     * 新增用户积分
     *
     * @param score 用户积分
     * @return 结果
     */
    @Override
    public int insertScore(Score score)
    {
        return scoreMapper.insertScore(score);
    }

    /**
     * 修改用户积分
     *
     * @param score 用户积分
     * @return 结果
     */
    @Override
    public int updateScore(Score score)
    {
        score.setUpdateTime(DateUtils.getNowDate());
        return scoreMapper.updateScore(score);
    }

    /**
     * 批量删除用户积分
     *
     * @param scoreIds 需要删除的用户积分主键
     * @return 结果
     */
    @Override
    public int deleteScoreByScoreIds(Long[] scoreIds)
    {
        return scoreMapper.deleteScoreByScoreIds(scoreIds);
    }

    /**
     * 删除用户积分信息
     *
     * @param scoreId 用户积分主键
     * @return 结果
     */
    @Override
    public int deleteScoreByScoreId(Long scoreId)
    {
        return scoreMapper.deleteScoreByScoreId(scoreId);
    }
}