package com.qst.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qst.system.mapper.ShareRuleMapper;
import com.qst.system.domain.ShareRule;
import com.qst.system.service.IShareRuleService;

/**
 * 分享规则管理Service业务层处理
 *
 * @author qst
 * @date 2022-06-14
 */
@Service
public class ShareRuleServiceImpl implements IShareRuleService
{
    @Autowired
    private ShareRuleMapper shareRuleMapper;

    /**
     * 查询分享规则管理
     *
     * @param sId 分享规则管理主键
     * @return 分享规则管理
     */
    @Override
    public ShareRule selectShareRuleBySId(Long sId)
    {
        return shareRuleMapper.selectShareRuleBySId(sId);
    }

    /**
     * 查询分享规则管理列表
     *
     * @param shareRule 分享规则管理
     * @return 分享规则管理
     */
    @Override
    public List<ShareRule> selectShareRuleList(ShareRule shareRule)
    {
        return shareRuleMapper.selectShareRuleList(shareRule);
    }

    /**
     * 新增分享规则管理
     *
     * @param shareRule 分享规则管理
     * @return 结果
     */
    @Override
    public int insertShareRule(ShareRule shareRule)
    {
        return shareRuleMapper.insertShareRule(shareRule);
    }

    /**
     * 修改分享规则管理
     *
     * @param shareRule 分享规则管理
     * @return 结果
     */
    @Override
    public int updateShareRule(ShareRule shareRule)
    {
        return shareRuleMapper.updateShareRule(shareRule);
    }

    /**
     * 批量删除分享规则管理
     *
     * @param sIds 需要删除的分享规则管理主键
     * @return 结果
     */
    @Override
    public int deleteShareRuleBySIds(Long[] sIds)
    {
        return shareRuleMapper.deleteShareRuleBySIds(sIds);
    }

    /**
     * 删除分享规则管理信息
     *
     * @param sId 分享规则管理主键
     * @return 结果
     */
    @Override
    public int deleteShareRuleBySId(Long sId)
    {
        return shareRuleMapper.deleteShareRuleBySId(sId);
    }
}