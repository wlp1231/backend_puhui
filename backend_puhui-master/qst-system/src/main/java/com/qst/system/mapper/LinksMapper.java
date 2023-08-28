package com.qst.system.mapper;

import java.util.List;
import com.qst.system.domain.Links;

/**
 * 友情链接管理Mapper接口
 *
 * @author qst
 * @date 2022-06-16
 */
public interface LinksMapper
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
     * 删除友情链接管理
     *
     * @param linkId 友情链接管理主键
     * @return 结果
     */
    public int deleteLinksByLinkId(Long linkId);

    /**
     * 批量删除友情链接管理
     *
     * @param linkIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteLinksByLinkIds(Long[] linkIds);
}