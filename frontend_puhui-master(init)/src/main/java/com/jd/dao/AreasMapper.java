package com.jd.dao;

import com.jd.bean.Areas;

import java.util.List;

/**
 * 区Mapper接口
 * 
 * @author qst
 * @date 2021-09-22
 */
public interface AreasMapper 
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
     * 删除区
     * 
     * @param id 区主键
     * @return 结果
     */
    public int deleteAreasById(Long id);

    /**
     * 批量删除区
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAreasByIds(Long[] ids);

    /**
     * 按市区ID查询
     * @param cArea
     * @return
     */
    List<Areas> selectAreaById(String cArea);
}
