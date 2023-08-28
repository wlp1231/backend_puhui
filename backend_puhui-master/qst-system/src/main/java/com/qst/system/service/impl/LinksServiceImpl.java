package com.qst.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qst.system.mapper.LinksMapper;
import com.qst.system.domain.Links;
import com.qst.system.service.ILinksService;

/**
 * 友情链接管理Service业务层处理
 *
 * @author qst
 * @date 2022-06-16
 */
@Service
public class LinksServiceImpl implements ILinksService
{
    @Autowired
    private LinksMapper linksMapper;

    /**
     * 查询友情链接管理
     *
     * @param linkId 友情链接管理主键
     * @return 友情链接管理
     */
    @Override
    public Links selectLinksByLinkId(Long linkId)
    {
        return linksMapper.selectLinksByLinkId(linkId);
    }

    /**
     * 查询友情链接管理列表
     *
     * @param links 友情链接管理
     * @return 友情链接管理
     */
    @Override
    public List<Links> selectLinksList(Links links)
    {
        return linksMapper.selectLinksList(links);
    }

    /**
     * 新增友情链接管理
     *
     * @param links 友情链接管理
     * @return 结果
     */
    @Override
    public int insertLinks(Links links)
    {
        return linksMapper.insertLinks(links);
    }

    /**
     * 修改友情链接管理
     *
     * @param links 友情链接管理
     * @return 结果
     */
    @Override
    public int updateLinks(Links links)
    {
        return linksMapper.updateLinks(links);
    }

    /**
     * 批量删除友情链接管理
     *
     * @param linkIds 需要删除的友情链接管理主键
     * @return 结果
     */
    @Override
    public int deleteLinksByLinkIds(Long[] linkIds)
    {
        return linksMapper.deleteLinksByLinkIds(linkIds);
    }

    /**
     * 删除友情链接管理信息
     *
     * @param linkId 友情链接管理主键
     * @return 结果
     */
    @Override
    public int deleteLinksByLinkId(Long linkId)
    {
        return linksMapper.deleteLinksByLinkId(linkId);
    }
}