package com.jd.dao;


import com.jd.bean.Materials;

import java.util.List;

/**
 * 材料Mapper接口
 * 
 * @author qst
 * @date 2021-09-23
 */
public interface MaterialsMapper 
{
    /**
     * 查询材料
     * 
     * @param lisId 材料主键
     * @return 材料
     */
    public Materials selectMaterialsByLisId(Long lisId);

    /**
     * 查询材料列表
     * 
     * @param materials 材料
     * @return 材料集合
     */
    public List<Materials> selectMaterialsList(Materials materials);

    /**
     * 新增材料
     * 
     * @param materials 材料
     * @return 结果
     */
    public int insertMaterials(Materials materials);

    /**
     * 修改材料
     * 
     * @param materials 材料
     * @return 结果
     */
    public int updateMaterials(Materials materials);

    /**
     * 删除材料
     * 
     * @param lisId 材料主键
     * @return 结果
     */
    public int deleteMaterialsByLisId(Long lisId);

    /**
     * 批量删除材料
     * 
     * @param lisIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMaterialsByLisIds(Long[] lisIds);

    /**
     * 查询审批材料数据
     * @param eId
     * @return
     */
    List<Materials> selectMaterialsById(Long eId);
}
