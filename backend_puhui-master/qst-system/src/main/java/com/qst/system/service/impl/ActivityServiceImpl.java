package com.qst.system.service.impl;

import java.util.List;
import com.qst.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qst.system.mapper.ActivityMapper;
import com.qst.system.domain.Activity;
import com.qst.system.service.IActivityService;

/**
 * 活动管理Service业务层处理
 *
 * @author zpbao
 * @date 2023-08-28
 */
@Service
public class ActivityServiceImpl implements IActivityService
{
    @Autowired
    private ActivityMapper activityMapper;

    /**
     * 查询活动管理
     *
     * @param aId 活动管理主键
     * @return 活动管理
     */
    @Override
    public Activity selectActivityByAId(Long aId)
    {
        return activityMapper.selectActivityByAId(aId);
    }

    /**
     * 查询活动管理列表
     *
     * @param activity 活动管理
     * @return 活动管理
     */
    @Override
    public List<Activity> selectActivityList(Activity activity)
    {
        return activityMapper.selectActivityList(activity);
    }

    /**
     * 新增活动管理
     *
     * @param activity 活动管理
     * @return 结果
     */
    @Override
    public int insertActivity(Activity activity)
    {
        return activityMapper.insertActivity(activity);
    }

    /**
     * 修改活动管理
     *
     * @param activity 活动管理
     * @return 结果
     */
    @Override
    public int updateActivity(Activity activity)
    {
        activity.setUpdateTime(DateUtils.getNowDate());
        return activityMapper.updateActivity(activity);
    }

    /**
     * 批量删除活动管理
     *
     * @param aIds 需要删除的活动管理主键
     * @return 结果
     */
    @Override
    public int deleteActivityByAIds(Long[] aIds)
    {
        return activityMapper.deleteActivityByAIds(aIds);
    }

    /**
     * 删除活动管理信息
     *
     * @param aId 活动管理主键
     * @return 结果
     */
    @Override
    public int deleteActivityByAId(Long aId)
    {
        return activityMapper.deleteActivityByAId(aId);
    }
}