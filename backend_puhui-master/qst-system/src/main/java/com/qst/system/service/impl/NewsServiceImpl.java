package com.qst.system.service.impl;

import java.util.List;
import com.qst.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qst.system.mapper.NewsMapper;
import com.qst.system.domain.News;
import com.qst.system.service.INewsService;

/**
 * 宣传信息管理Service业务层处理
 *
 * @author qst
 * @date 2022-06-16
 */
@Service
public class NewsServiceImpl implements INewsService
{
    @Autowired
    private NewsMapper newsMapper;

    /**
     * 查询宣传信息管理
     *
     * @param nId 宣传信息管理主键
     * @return 宣传信息管理
     */
    @Override
    public News selectNewsByNId(Long nId)
    {
        return newsMapper.selectNewsByNId(nId);
    }

    /**
     * 查询宣传信息管理列表
     *
     * @param news 宣传信息管理
     * @return 宣传信息管理
     */
    @Override
    public List<News> selectNewsList(News news)
    {
        return newsMapper.selectNewsList(news);
    }

    /**
     * 新增宣传信息管理
     *
     * @param news 宣传信息管理
     * @return 结果
     */
    @Override
    public int insertNews(News news)
    {
        return newsMapper.insertNews(news);
    }

    /**
     * 修改宣传信息管理
     *
     * @param news 宣传信息管理
     * @return 结果
     */
    @Override
    public int updateNews(News news)
    {
        news.setUpdateTime(DateUtils.getNowDate());
        return newsMapper.updateNews(news);
    }

    /**
     * 批量删除宣传信息管理
     *
     * @param nIds 需要删除的宣传信息管理主键
     * @return 结果
     */
    @Override
    public int deleteNewsByNIds(Long[] nIds)
    {
        return newsMapper.deleteNewsByNIds(nIds);
    }

    /**
     * 删除宣传信息管理信息
     *
     * @param nId 宣传信息管理主键
     * @return 结果
     */
    @Override
    public int deleteNewsByNId(Long nId)
    {
        return newsMapper.deleteNewsByNId(nId);
    }
}