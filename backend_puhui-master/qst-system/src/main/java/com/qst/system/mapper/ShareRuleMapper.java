package com.qst.system.mapper;

import java.util.List;
import com.qst.system.domain.ShareRule;

/**
 * 分享规则管理Mapper接口
 *
 * @author qst
 * @date 2022-06-14
 */
public interface ShareRuleMapper
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
     * 删除分享规则管理
     *
     * @param sId 分享规则管理主键
     * @return 结果
     */
    public int deleteShareRuleBySId(Long sId);

    /**
     * 批量删除分享规则管理
     *
     * @param sIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteShareRuleBySIds(Long[] sIds);
}