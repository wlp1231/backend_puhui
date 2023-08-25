package com.qst.system.mapper;

import com.qst.common.core.domain.entity.Provinces;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author qst
 * @date 2021-09-15
 */
public interface ProvincesMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public Provinces selectProvincesById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param provinces 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<Provinces> selectProvincesList(Provinces provinces);

    /**
     * 新增【请填写功能名称】
     * 
     * @param provinces 【请填写功能名称】
     * @return 结果
     */
    public int insertProvinces(Provinces provinces);

    /**
     * 修改【请填写功能名称】
     * 
     * @param provinces 【请填写功能名称】
     * @return 结果
     */
    public int updateProvinces(Provinces provinces);

    /**
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteProvincesById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProvincesByIds(Long[] ids);

    /**
     * 查询省
     * @return
     */
    List<Provinces> selectProvincesAll();
    /**
     * 按省ID查询
     * @param cPro 省的Id
     * @return
     */
    Provinces selectProById(String cPro);



    /**
     * 查询全部省及市
     * @return
     */
    List<Provinces> selectProvincesAndCity();


}
