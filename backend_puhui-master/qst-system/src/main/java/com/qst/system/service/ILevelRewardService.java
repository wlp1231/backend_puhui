package com.qst.system.service;

import java.util.List;
import com.qst.system.domain.LevelReward;

/**
 * level_rewardService接口
 *
 * @author zpbao
 * @date 2023-08-28
 */
public interface ILevelRewardService
{
    /**
     * 查询level_reward
     *
     * @param rewardId level_reward主键
     * @return level_reward
     */
    public LevelReward selectLevelRewardByRewardId(Long rewardId);

    /**
     * 查询level_reward列表
     *
     * @param levelReward level_reward
     * @return level_reward集合
     */
    public List<LevelReward> selectLevelRewardList(LevelReward levelReward);

    /**
     * 新增level_reward
     *
     * @param levelReward level_reward
     * @return 结果
     */
    public int insertLevelReward(LevelReward levelReward);

    /**
     * 修改level_reward
     *
     * @param levelReward level_reward
     * @return 结果
     */
    public int updateLevelReward(LevelReward levelReward);

    /**
     * 批量删除level_reward
     *
     * @param rewardIds 需要删除的level_reward主键集合
     * @return 结果
     */
    public int deleteLevelRewardByRewardIds(Long[] rewardIds);

    /**
     * 删除level_reward信息
     *
     * @param rewardId level_reward主键
     * @return 结果
     */
    public int deleteLevelRewardByRewardId(Long rewardId);
}