package com.qst.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qst.system.mapper.LevelMapper;
import com.qst.system.domain.Level;
import com.qst.system.service.ILevelService;

/**
 * 等级设置Service业务层处理
 *
 * @author qst
 * @date 2022-06-27
 */
@Service
public class LevelServiceImpl implements ILevelService
{
    @Autowired
    private LevelMapper levelMapper;

    /**
     * 查询等级设置
     *
     * @param levelId 等级设置主键
     * @return 等级设置
     */
    @Override
    public Level selectLevelByLevelId(Long levelId)
    {
        return levelMapper.selectLevelByLevelId(levelId);
    }

    /**
     * 查询等级设置列表
     *
     * @param level 等级设置
     * @return 等级设置
     */
    @Override
    public List<Level> selectLevelList(Level level)
    {
        return levelMapper.selectLevelList(level);
    }

    /**
     * 新增等级设置
     *
     * @param level 等级设置
     * @return 结果
     */
    @Override
    public int insertLevel(Level level)
    {
        return levelMapper.insertLevel(level);
    }

    /**
     * 修改等级设置
     *
     * @param level 等级设置
     * @return 结果
     */
    @Override
    public int updateLevel(Level level)
    {
        return levelMapper.updateLevel(level);
    }

    /**
     * 批量删除等级设置
     *
     * @param levelIds 需要删除的等级设置主键
     * @return 结果
     */
    @Override
    public int deleteLevelByLevelIds(Long[] levelIds)
    {
        return levelMapper.deleteLevelByLevelIds(levelIds);
    }

    /**
     * 删除等级设置信息
     *
     * @param levelId 等级设置主键
     * @return 结果
     */
    @Override
    public int deleteLevelByLevelId(Long levelId)
    {
        return levelMapper.deleteLevelByLevelId(levelId);
    }
}