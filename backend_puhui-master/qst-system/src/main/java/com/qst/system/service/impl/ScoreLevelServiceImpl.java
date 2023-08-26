package com.qst.system.service.impl;

import java.util.List;
import com.qst.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qst.system.mapper.ScoreLevelMapper;
import com.qst.system.domain.ScoreLevel;
import com.qst.system.service.IScoreLevelService;

/**
 * 用户等级Service业务层处理
 *
 * @author qst
 * @date 2022-06-27
 */
@Service
public class ScoreLevelServiceImpl implements IScoreLevelService
{
    @Autowired
    private ScoreLevelMapper scoreLevelMapper;

    /**
     * 查询用户等级
     *
     * @param scoreId 用户等级主键
     * @return 用户等级
     */
    @Override
    public ScoreLevel selectScoreLevelByScoreId(Long scoreId)
    {
        return scoreLevelMapper.selectScoreLevelByScoreId(scoreId);
    }


    /**
     * 查询用户等级表
     *
     * @param comId 公司id
     * @return 用户等级表
     */
    @Override
    public ScoreLevel selectScoreLevelByComId(Long comId)
    {
        return scoreLevelMapper.selectScoreLevelByComId(comId);
    }

    /**
     * 查询用户等级列表
     *
     * @param scoreLevel 用户等级
     * @return 用户等级
     */
    @Override
    public List<ScoreLevel> selectScoreLevelList(ScoreLevel scoreLevel)
    {
        return scoreLevelMapper.selectScoreLevelList(scoreLevel);
    }

    /**
     * 新增用户等级
     *
     * @param scoreLevel 用户等级
     * @return 结果
     */
    @Override
    public int insertScoreLevel(ScoreLevel scoreLevel)
    {
        return scoreLevelMapper.insertScoreLevel(scoreLevel);
    }

    /**
     * 修改用户等级
     *
     * @param scoreLevel 用户等级
     * @return 结果
     */
    @Override
    public int updateScoreLevel(ScoreLevel scoreLevel)
    {
        scoreLevel.setUpdateTime(DateUtils.getNowDate());
        return scoreLevelMapper.updateScoreLevel(scoreLevel);
    }

    /**
     * 批量删除用户等级
     *
     * @param scoreIds 需要删除的用户等级主键
     * @return 结果
     */
    @Override
    public int deleteScoreLevelByScoreIds(Long[] scoreIds)
    {
        return scoreLevelMapper.deleteScoreLevelByScoreIds(scoreIds);
    }

    /**
     * 删除用户等级信息
     *
     * @param scoreId 用户等级主键
     * @return 结果
     */
    @Override
    public int deleteScoreLevelByScoreId(Long scoreId)
    {
        return scoreLevelMapper.deleteScoreLevelByScoreId(scoreId);
    }
}