package com.qst.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qst.system.mapper.MessageMapper;
import com.qst.system.domain.Message;
import com.qst.system.service.IMessageService;

/**
 * 留言管理Service业务层处理
 *
 * @author qst
 * @date 2022-06-14
 */
@Service
public class MessageServiceImpl implements IMessageService
{
    @Autowired
    private MessageMapper messageMapper;

    /**
     * 查询留言管理
     *
     * @param mId 留言管理主键
     * @return 留言管理
     */
    @Override
    public Message selectMessageByMId(Long mId)
    {
        return messageMapper.selectMessageByMId(mId);
    }

    /**
     * 查询留言管理列表
     *
     * @param message 留言管理
     * @return 留言管理
     */
    @Override
    public List<Message> selectMessageList(Message message)
    {
        return messageMapper.selectMessageList(message);
    }

    /**
     * 新增留言管理
     *
     * @param message 留言管理
     * @return 结果
     */
    @Override
    public int insertMessage(Message message)
    {
        return messageMapper.insertMessage(message);
    }

    /**
     * 修改留言管理
     *
     * @param message 留言管理
     * @return 结果
     */
    @Override
    public int updateMessage(Message message)
    {
        return messageMapper.updateMessage(message);
    }

    /**
     * 批量删除留言管理
     *
     * @param mIds 需要删除的留言管理主键
     * @return 结果
     */
    @Override
    public int deleteMessageByMIds(Long[] mIds)
    {
        return messageMapper.deleteMessageByMIds(mIds);
    }

    /**
     * 删除留言管理信息
     *
     * @param mId 留言管理主键
     * @return 结果
     */
    @Override
    public int deleteMessageByMId(Long mId)
    {
        return messageMapper.deleteMessageByMId(mId);
    }
}