package com.qst.system.service;

import java.util.List;
import com.qst.system.domain.Links;

/**
 * 友情链接管理Service接口
 *
 * @author qst
 * @date 2022-06-16
 */
public interface ILinksService
{
    /**
     * 查询友情链接管理
     *
     * @param linkId 友情链接管理主键
     * @return 友情链接管理
     */
    public Links selectLinksByLinkId(Long linkId);

    /**
     * 查询友情链接管理列表
     *
     * @param links 友情链接管理
     * @return 友情链接管理集合
     */
    public List<Links> selectLinksList(Links links);

    /**
     * 新增友情链接管理
     *
     * @param links 友情链接管理
     * @return 结果
     */
    public int insertLinks(Links links);

    /**
     * 修改友情链接管理
     *
     * @param links 友情链接管理
     * @return 结果
     */
    public int updateLinks(Links links);

    /**
     * 批量删除友情链接管理
     *
     * @param linkIds 需要删除的友情链接管理主键集合
     * @return 结果
     */
    public int deleteLinksByLinkIds(Long[] linkIds);

    /**
     * 删除友情链接管理信息
     *
     * @param linkId 友情链接管理主键
     * @return 结果
     */
    public int deleteLinksByLinkId(Long linkId);
}