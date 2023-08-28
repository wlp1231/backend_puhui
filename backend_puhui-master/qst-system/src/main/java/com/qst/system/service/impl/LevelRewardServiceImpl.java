package com.qst.system.service.impl;

import java.util.List;
import com.qst.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qst.system.mapper.LevelRewardMapper;
import com.qst.system.domain.LevelReward;
import com.qst.system.service.ILevelRewardService;

/**
 * level_rewardService业务层处理
 *
 * @author zpbao
 * @date 2023-08-28
 */
@Service
public class LevelRewardServiceImpl implements ILevelRewardService
{
    @Autowired
    private LevelRewardMapper levelRewardMapper;

    /**
     * 查询level_reward
     *
     * @param rewardId level_reward主键
     * @return level_reward
     */
    @Override
    public LevelReward selectLevelRewardByRewardId(Long rewardId)
    {
        return levelRewardMapper.selectLevelRewardByRewardId(rewardId);
    }

    /**
     * 查询level_reward列表
     *
     * @param levelReward level_reward
     * @return level_reward
     */
    @Override
    public List<LevelReward> selectLevelRewardList(LevelReward levelReward)
    {
        return levelRewardMapper.selectLevelRewardList(levelReward);
    }

    /**
     * 新增level_reward
     *
     * @param levelReward level_reward
     * @return 结果
     */
    @Override
    public int insertLevelReward(LevelReward levelReward)
    {
        return levelRewardMapper.insertLevelReward(levelReward);
    }

    /**
     * 修改level_reward
     *
     * @param levelReward level_reward
     * @return 结果
     */
    @Override
    public int updateLevelReward(LevelReward levelReward)
    {
        levelReward.setUpdateTime(DateUtils.getNowDate());
        return levelRewardMapper.updateLevelReward(levelReward);
    }

    /**
     * 批量删除level_reward
     *
     * @param rewardIds 需要删除的level_reward主键
     * @return 结果
     */
    @Override
    public int deleteLevelRewardByRewardIds(Long[] rewardIds)
    {
        return levelRewardMapper.deleteLevelRewardByRewardIds(rewardIds);
    }

    /**
     * 删除level_reward信息
     *
     * @param rewardId level_reward主键
     * @return 结果
     */
    @Override
    public int deleteLevelRewardByRewardId(Long rewardId)
    {
        return levelRewardMapper.deleteLevelRewardByRewardId(rewardId);
    }
}