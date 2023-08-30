package com.jd.service.impl;

import com.jd.bean.ComDetails;
import com.jd.dao.ComDetailsMapper;
import com.jd.service.IComDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

/**
 * 企业详情Service业务层处理
 *
 * @author zpbao
 * @date 2023-08-30
 */
@Service
public class ComDetailsServiceImpl implements IComDetailsService
{
    @Resource
    private ComDetailsMapper comDetailsMapper;

    /**
     * 查询企业详情
     *
     * @param cId 企业详情主键
     * @return 企业详情
     */
    @Override
    public ComDetails selectComDetailsByCId(Long cId)
    {
        return comDetailsMapper.selectComDetailsByCId(cId);
    }

    /**
     * 查询企业详情列表
     *
     * @param comDetails 企业详情
     * @return 企业详情
     */
    @Override
    public List<ComDetails> selectComDetailsList(ComDetails comDetails)
    {
        return comDetailsMapper.selectComDetailsList(comDetails);
    }

    /**
     * 新增企业详情
     *
     * @param comDetails 企业详情
     * @return 结果
     */
    @Override
    public int insertComDetails(ComDetails comDetails)
    {
        return comDetailsMapper.insertComDetails(comDetails);
    }

    /**
     * 修改企业详情
     *
     * @param comDetails 企业详情
     * @return 结果
     */
    @Override
    public int updateComDetails(ComDetails comDetails)
    {
        return comDetailsMapper.updateComDetails(comDetails);
    }

    /**
     * 批量删除企业详情
     *
     * @param cIds 需要删除的企业详情主键
     * @return 结果
     */
    @Override
    public int deleteComDetailsByCIds(Long[] cIds)
    {
        return comDetailsMapper.deleteComDetailsByCIds(cIds);
    }

    /**
     * 删除企业详情信息
     *
     * @param cId 企业详情主键
     * @return 结果
     */
    @Override
    public int deleteComDetailsByCId(Long cId)
    {
        return comDetailsMapper.deleteComDetailsByCId(cId);
    }

    /**
     * 实名认证
     *
     * @param comDetails
     * @param files
     * @return
     */
    @Override
    public boolean attCompanies(ComDetails comDetails, List<MultipartFile> files) {
        for (int i = 0 ; i < files.size(); i++){
            //获取图片名
//            String fileName = file.getOriginalFilename();

            String fileName = files.get(i).getOriginalFilename();
            //根据时间戳生成图片路径
            String imageName = IDUtils.getUUID() + fileName.substring(fileName.lastIndexOf("."));
//            String imageName = System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf("."));

            comDetails.setcLicense("/profile/upload/att/"+imageName);

            try (InputStream stream = files.get(i).getInputStream()) {
                File f = new File("D:/qst/uploadPath/upload/att/");
                if (!f.exists()){
                    f.mkdirs();
                    //根据路径生成文件
                    Files.copy(stream, Paths.get("D:/qst/uploadPath/upload/att/"+imageName), StandardCopyOption.REPLACE_EXISTING);
                }else {
                    //根据路径生成文件
                    Files.copy(stream, Paths.get("D:/qst/uploadPath/upload/att/"+imageName), StandardCopyOption.REPLACE_EXISTING);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        comDetailsMapper.insertComDetails(comDetails);
        return true;
    }
}