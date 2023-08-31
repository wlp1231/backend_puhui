package com.jd.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class UploadUtils {
    /**
     * 公用的文件上传方法
     *
     * @param multipartFile        request中的文件流对象
     * @param objBean              实体类参数【保存上传文件的相对路径】
     * @param beanFilePropertyName 实体类的属性名【保存上传文件相对路径的属性名】
     */
    public static void doFileUpload(MultipartFile multipartFile, Object objBean, String beanFilePropertyName) {
        System.out.println("上传文件对象：");
        System.out.println(multipartFile.getOriginalFilename());
        // 上传文件的命名规则
        //获取图片名
        String fileName = multipartFile.getOriginalFilename();
        //根据时间戳生成图片路径,截取后缀名
        String imageName = System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf("."));
        try (InputStream stream = multipartFile.getInputStream()) {
            //根据路径生成文件
            Files.copy(stream, Paths.get("D:/qst/uploadPath/upload/" + imageName), StandardCopyOption.REPLACE_EXISTING);
            // 这里使用了反射，但是是借助于apache的commons的BeanUtils工具进行实体类的反射处理
            // 文件上传成功以后，将新上传的文件的服务器的相对路径保存到参数实体类的指定的属性字段上
            BeanUtils.setProperty(objBean, beanFilePropertyName, "/profile/upload/" + imageName);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            System.out.println("文件上传失败：" + e.getMessage());
        }
    }
}
