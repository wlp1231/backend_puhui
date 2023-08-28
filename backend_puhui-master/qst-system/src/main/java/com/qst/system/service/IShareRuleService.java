package com.qst.system.service;

import java.util.List;
import com.qst.system.domain.ShareRule;

/**
 * 分享规则管理Service接口
 *
 * @author qst
 * @date 2022-06-14
 */
public interface IShareRuleService
{
    /**
     * 查询分享规则管理
     *
     * @param sId 分享规则管理主键
     * @return 分享规则管理
     */
    public ShareRule selectShareRuleBySId(Long sId);

    /**
     * 查询分享规则管理列表
     *
     * @param shareRule 分享规则管理
     * @return 分享规则管理集合
     */
    public List<ShareRule> selectShareRuleList(ShareRule shareRule);

    /**
     * 新增分享规则管理
     *
     * @param shareRule 分享规则管理
     * @return 结果
     */
    public int insertShareRule(ShareRule shareRule);

    /**
     * 修改分享规则管理
     *
     * @param shareRule 分享规则管理
     * @return 结果
     */
    public int updateShareRule(ShareRule shareRule);

    /**
     * 批量删除分享规则管理
     *
     * @param sIds 需要删除的分享规则管理主键集合
     * @return 结果
     */
    public int deleteShareRuleBySIds(Long[] sIds);

    /**
     * 删除分享规则管理信息
     *
     * @param sId 分享规则管理主键
     * @return 结果
     */
    public int deleteShareRuleBySId(Long sId);
}