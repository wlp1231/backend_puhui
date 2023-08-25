package com.qst.system.service.impl;

import java.util.List;

import com.qst.common.core.domain.entity.Areas;
import com.qst.system.mapper.AreasMapper;
import com.qst.system.service.IAreasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 区Service业务层处理
 * 
 * @author qst
 * @date 2021-09-22
 */
@Service
public class AreasServiceImpl implements IAreasService
{
    @Autowired
    private AreasMapper areasMapper;

    /**
     * 查询区
     * 
     * @param id 区主键
     * @return 区
     */
    @Override
    public Areas selectAreasById(Long id)
    {
        return areasMapper.selectAreasById(id);
    }

    /**
     * 查询区列表
     * 
     * @param areas 区
     * @return 区
     */
    @Override
    public List<Areas> selectAreasList(Areas areas)
    {
        return areasMapper.selectAreasList(areas);
    }

    /**
     * 新增区
     * 
     * @param areas 区
     * @return 结果
     */
    @Override
    public int insertAreas(Areas areas)
    {
        return areasMapper.insertAreas(areas);
    }

    /**
     * 修改区
     * 
     * @param areas 区
     * @return 结果
     */
    @Override
    public int updateAreas(Areas areas)
    {
        return areasMapper.updateAreas(areas);
    }

    /**
     * 批量删除区
     * 
     * @param ids 需要删除的区主键
     * @return 结果
     */
    @Override
    public int deleteAreasByIds(Long[] ids)
    {
        return areasMapper.deleteAreasByIds(ids);
    }

    /**
     * 删除区信息
     * 
     * @param id 区主键
     * @return 结果
     */
    @Override
    public int deleteAreasById(Long id)
    {
        return areasMapper.deleteAreasById(id);
    }

    /**
     * 按市区ID查询
     *
     * @param cArea
     * @return
     */
    @Override
    public Areas selectAreaById(String cArea) {
        return areasMapper.selectAreaById(cArea);
    }
}
