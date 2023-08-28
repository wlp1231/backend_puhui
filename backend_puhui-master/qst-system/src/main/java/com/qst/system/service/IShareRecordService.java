package com.qst.system.service;

import java.util.List;
import com.qst.system.domain.ShareRecord;

/**
 * 分享数据管理Service接口
 *
 * @author qst
 * @date 2022-06-14
 */
public interface IShareRecordService
{
    /**
     * 查询分享数据管理
     *
     * @param rId 分享数据管理主键
     * @return 分享数据管理
     */
    public ShareRecord selectShareRecordByRId(Long rId);

    /**
     * 查询分享数据管理列表
     *
     * @param shareRecord 分享数据管理
     * @return 分享数据管理集合
     */
    public List<ShareRecord> selectShareRecordList(ShareRecord shareRecord);

    /**
     * 新增分享数据管理
     *
     * @param shareRecord 分享数据管理
     * @return 结果
     */
    public int insertShareRecord(ShareRecord shareRecord);

    /**
     * 修改分享数据管理
     *
     * @param shareRecord 分享数据管理
     * @return 结果
     */
    public int updateShareRecord(ShareRecord shareRecord);

    /**
     * 批量删除分享数据管理
     *
     * @param rIds 需要删除的分享数据管理主键集合
     * @return 结果
     */
    public int deleteShareRecordByRIds(Long[] rIds);

    /**
     * 删除分享数据管理信息
     *
     * @param rId 分享数据管理主键
     * @return 结果
     */
    public int deleteShareRecordByRId(Long rId);
}