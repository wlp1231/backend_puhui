package com.qst.system.service;

import java.util.List;
import com.qst.system.domain.Activity;

/**
 * 活动管理Service接口
 *
 * @author zpbao
 * @date 2023-08-28
 */
public interface IActivityService
{
    /**
     * 查询活动管理
     *
     * @param aId 活动管理主键
     * @return 活动管理
     */
    public Activity selectActivityByAId(Long aId);

    /**
     * 查询活动管理列表
     *
     * @param activity 活动管理
     * @return 活动管理集合
     */
    public List<Activity> selectActivityList(Activity activity);

    /**
     * 新增活动管理
     *
     * @param activity 活动管理
     * @return 结果
     */
    public int insertActivity(Activity activity);

    /**
     * 修改活动管理
     *
     * @param activity 活动管理
     * @return 结果
     */
    public int updateActivity(Activity activity);

    /**
     * 批量删除活动管理
     *
     * @param aIds 需要删除的活动管理主键集合
     * @return 结果
     */
    public int deleteActivityByAIds(Long[] aIds);

    /**
     * 删除活动管理信息
     *
     * @param aId 活动管理主键
     * @return 结果
     */
    public int deleteActivityByAId(Long aId);
}