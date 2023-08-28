package com.qst.system.service;

import java.util.List;
import com.qst.system.domain.News;

/**
 * 宣传信息管理Service接口
 *
 * @author qst
 * @date 2022-06-16
 */
public interface INewsService
{
    /**
     * 查询宣传信息管理
     *
     * @param nId 宣传信息管理主键
     * @return 宣传信息管理
     */
    public News selectNewsByNId(Long nId);

    /**
     * 查询宣传信息管理列表
     *
     * @param news 宣传信息管理
     * @return 宣传信息管理集合
     */
    public List<News> selectNewsList(News news);

    /**
     * 新增宣传信息管理
     *
     * @param news 宣传信息管理
     * @return 结果
     */
    public int insertNews(News news);

    /**
     * 修改宣传信息管理
     *
     * @param news 宣传信息管理
     * @return 结果
     */
    public int updateNews(News news);

    /**
     * 批量删除宣传信息管理
     *
     * @param nIds 需要删除的宣传信息管理主键集合
     * @return 结果
     */
    public int deleteNewsByNIds(Long[] nIds);

    /**
     * 删除宣传信息管理信息
     *
     * @param nId 宣传信息管理主键
     * @return 结果
     */
    public int deleteNewsByNId(Long nId);
}