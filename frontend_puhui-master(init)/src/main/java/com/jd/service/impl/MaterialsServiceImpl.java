package com.jd.service.impl;

import com.jd.bean.Materials;
import com.jd.dao.MaterialsMapper;
import com.jd.service.IMaterialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

/**
 * 材料Service业务层处理
 * 
 * @author qst
 * @date 2021-09-23
 */
@Service
public class MaterialsServiceImpl implements IMaterialsService {
    @Autowired
    private MaterialsMapper materialsMapper;

    /**
     * 查询材料
     *
     * @param lisId 材料主键
     * @return 材料
     */
    @Override
    public Materials selectMaterialsByLisId(Long lisId) {
        return materialsMapper.selectMaterialsByLisId(lisId);
    }

    /**
     * 查询材料列表
     *
     * @param materials 材料
     * @return 材料
     */
    @Override
    public List<Materials> selectMaterialsList(Materials materials) {
        return materialsMapper.selectMaterialsList(materials);
    }

    /**
     * 新增材料
     *
     * @param materials 材料
     * @return 结果
     */
    @Override
    public int insertMaterials(Materials materials) {
        return materialsMapper.insertMaterials(materials);
    }

    /**
     * 修改材料
     *
     * @param materials 材料
     * @return 结果
     */
    @Override
    public int updateMaterials(Materials materials) {
        return materialsMapper.updateMaterials(materials);
    }

    /**
     * 批量删除材料
     *
     * @param lisIds 需要删除的材料主键
     * @return 结果
     */
    @Override
    public int deleteMaterialsByLisIds(Long[] lisIds) {
        return materialsMapper.deleteMaterialsByLisIds(lisIds);
    }

    /**
     * 删除材料信息
     *
     * @param lisId 材料主键
     * @return 结果
     */
    @Override
    public int deleteMaterialsByLisId(Long lisId) {
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

    /*public boolean insert(Materials materials, List<MultipartFile> files) {
        for (MultipartFile file : files) {
            //获取图片名
            String fileName = file.getOriginalFilename();
            //根据时间戳生成图片路径,截取后缀名
            String imageName = System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf("."));
            materials.setLisSixRunWater("/profile/upload/"+imageName);
            try (InputStream stream = file.getInputStream()) {
                //根据路径生成文件
                Files.copy(stream, Paths.get("D:/qst/uploadPath/upload/" + imageName), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        materialsMapper.insertMaterials(materials);
        return true;
    }*/

    public boolean insert(Materials materials, List<MultipartFile> files) {
//        for (MultipartFile file : files) {
        for (int i = 0 ; i < files.size(); i++){
            //获取图片名
//            String fileName = file.getOriginalFilename();

            String fileName = files.get(i).getOriginalFilename();
            //根据时间戳生成图片路径
            String imageName = System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf("."));
            if (i == 0){
                materials.setLisSixRunWater("/profile/upload/"+imageName);
            }else if (i == 1){
                materials.setLisWork("/profile/upload/"+imageName);
            }else if (i == 2){
                materials.setLisReport("/profile/upload/"+imageName);
            }else {
                materials.setLisPawn("/profile/upload/"+imageName);
            }
            try (InputStream stream = files.get(i).getInputStream()) {
                //根据路径生成文件
                Files.copy(stream, Paths.get("D:/qst/uploadPath/upload/"+imageName), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        materialsMapper.insertMaterials(materials);
        return true;
    }
}
