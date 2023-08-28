package com.qst.system.service.impl;

import java.util.List;
import com.qst.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qst.system.mapper.ShareRecordMapper;
import com.qst.system.domain.ShareRecord;
import com.qst.system.service.IShareRecordService;

/**
 * 分享数据管理Service业务层处理
 *
 * @author qst
 * @date 2022-06-14
 */
@Service
public class ShareRecordServiceImpl implements IShareRecordService
{
    @Autowired
    private ShareRecordMapper shareRecordMapper;

    /**
     * 查询分享数据管理
     *
     * @param rId 分享数据管理主键
     * @return 分享数据管理
     */
    @Override
    public ShareRecord selectShareRecordByRId(Long rId)
    {
        return shareRecordMapper.selectShareRecordByRId(rId);
    }

    /**
     * 查询分享数据管理列表
     *
     * @param shareRecord 分享数据管理
     * @return 分享数据管理
     */
    @Override
    public List<ShareRecord> selectShareRecordList(ShareRecord shareRecord)
    {
        return shareRecordMapper.selectShareRecordList(shareRecord);
    }

    /**
     * 新增分享数据管理
     *
     * @param shareRecord 分享数据管理
     * @return 结果
     */
    @Override
    public int insertShareRecord(ShareRecord shareRecord)
    {
        return shareRecordMapper.insertShareRecord(shareRecord);
    }

    /**
     * 修改分享数据管理
     *
     * @param shareRecord 分享数据管理
     * @return 结果
     */
    @Override
    public int updateShareRecord(ShareRecord shareRecord)
    {
        shareRecord.setUpdateTime(DateUtils.getNowDate());
        return shareRecordMapper.updateShareRecord(shareRecord);
    }

    /**
     * 批量删除分享数据管理
     *
     * @param rIds 需要删除的分享数据管理主键
     * @return 结果
     */
    @Override
    public int deleteShareRecordByRIds(Long[] rIds)
    {
        return shareRecordMapper.deleteShareRecordByRIds(rIds);
    }

    /**
     * 删除分享数据管理信息
     *
     * @param rId 分享数据管理主键
     * @return 结果
     */
    @Override
    public int deleteShareRecordByRId(Long rId)
    {
        return shareRecordMapper.deleteShareRecordByRId(rId);
    }
}