package com.qst.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qst.system.mapper.MaterialsMapper;
import com.qst.system.domain.Materials;
import com.qst.system.service.IMaterialsService;

/**
 * 材料Service业务层处理
 *
 * @author zpbao
 * @date 2023-08-26
 */
@Service
public class MaterialsServiceImpl implements IMaterialsService
{
    @Autowired
    private MaterialsMapper materialsMapper;

    /**
     * 查询材料
     *
     * @param lisId 材料主键
     * @return 材料
     */
    @Override
    public Materials selectMaterialsByLisId(Long lisId)
    {
        return materialsMapper.selectMaterialsByLisId(lisId);
    }

    /**
     * 查询材料列表
     *
     * @param materials 材料
     * @return 材料
     */
    @Override
    public List<Materials> selectMaterialsList(Materials materials)
    {
        return materialsMapper.selectMaterialsList(materials);
    }

    /**
     * 新增材料
     *
     * @param materials 材料
     * @return 结果
     */
    @Override
    public int insertMaterials(Materials materials)
    {
        return materialsMapper.insertMaterials(materials);
    }

    /**
     * 修改材料
     *
     * @param materials 材料
     * @return 结果
     */
    @Override
    public int updateMaterials(Materials materials)
    {
        return materialsMapper.updateMaterials(materials);
    }

    /**
     * 批量删除材料
     *
     * @param lisIds 需要删除的材料主键
     * @return 结果
     */
    @Override
    public int deleteMaterialsByLisIds(Long[] lisIds)
    {
        return materialsMapper.deleteMaterialsByLisIds(lisIds);
    }

    /**
     * 删除材料信息
     *
     * @param lisId 材料主键
     * @return 结果
     */
    @Override
    public int deleteMaterialsByLisId(Long lisId)
    {
        return materialsMapper.deleteMaterialsByLisId(lisId);
    }

    /**
     * 查询审批材料数据
     *
     * @param eId
     * @return
     */
    @Override
    public List<Materials> selectMaterialsById(Long eId) {
        return materialsMapper.selectMaterialsById(eId);
    }
}