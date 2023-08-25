package com.qst.system.service;

import com.qst.common.core.domain.entity.Areas;

import java.util.List;

/**
 * 区Service接口
 * 
 * @author qst
 * @date 2021-09-22
 */
public interface IAreasService 
{
    /**
     * 查询区
     * 
     * @param id 区主键
     * @return 区
     */
    public Areas selectAreasById(Long id);

    /**
     * 查询区列表
     * 
     * @param areas 区
     * @return 区集合
     */
    public List<Areas> selectAreasList(Areas areas);

    /**
     * 新增区
     * 
     * @param areas 区
     * @return 结果
     */
    public int insertAreas(Areas areas);

    /**
     * 修改区
     * 
     * @param areas 区
     * @return 结果
     */
    public int updateAreas(Areas areas);

    /**
     * 批量删除区
     * 
     * @param ids 需要删除的区主键集合
     * @return 结果
     */
    public int deleteAreasByIds(Long[] ids);

    /**
     * 删除区信息
     * 
     * @param id 区主键
     * @return 结果
     */
    public int deleteAreasById(Long id);

    /**
     * 按市区ID查询
     * @param cArea
     * @return
     */
    Areas selectAreaById(String cArea);
}
