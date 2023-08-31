package com.jd.service.impl;

import com.jd.bean.SysUser;
import com.jd.common.service.MailService;
import com.jd.dao.SysUserMapper;
import com.jd.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户信息Service业务层处理
 *
 * @author qst
 * @date 2021-09-04
 */
@Service
public class SysUserServiceImpl implements ISysUserService {
    @Resource
    private SysUserMapper sysUserMapper;

    @Autowired
    private MailService mailService;

    /**
     * 根据激活码查询用户信息
     *
     * @param remark 激活码
     * @return 用户信息
     */
    @Override
    public SysUser selectUserByActiveCode(String remark) {
        return sysUserMapper.selectUserByActiveCode(remark);
    }

    /**
     * 查询用户信息
     *
     * @param userId 用户信息主键
     * @return 用户信息
     */
    @Override
    public SysUser selectSysUserByUserId(Long userId) {
        return sysUserMapper.selectSysUserByUserId(userId);
    }

    /**
     * 查询用户信息列表
     *
     * @param sysUser 用户信息
     * @return 用户信息
     */
    @Override
    public List<SysUser> selectSysUserList(SysUser sysUser) {
        return sysUserMapper.selectSysUserList(sysUser);
    }

    /**
     * 注册用户信息
     *
     * @param sysUser 用户信息
     * @return 结果
     */
    @Override
    public int insertSysUser(SysUser sysUser) {
        int i = sysUserMapper.insertSysUser(sysUser);
        //获取激活码
        String code = sysUser.getRemark();
        //定义主题
        String subject = "来自普惠金融的注册激活邮件";
        //激活链接
        String conText = "<h5>欢迎注册普惠金融账号，请点击下方链接进行邮箱账号激活</h5><a href=\"http://10.1.252.99:9090/user/checkCode?code=" + code + "\" >激活请点击:" + code + "</a>";
        //发送激活邮件
        mailService.sendMimeMail(sysUser.getEmail(), subject, conText);
        return i;
    }

    /**
     * 修改用户信息
     *
     * @param sysUser 用户信息
     * @return 结果
     */
    @Override
    public int updateSysUser(SysUser sysUser) {
        return sysUserMapper.updateSysUser(sysUser);
    }

    /**
     * 批量删除用户信息
     *
     * @param userIds 需要删除的用户信息主键
     * @return 结果
     */
    @Override
    public int deleteSysUserByUserIds(Long[] userIds) {
        return sysUserMapper.deleteSysUserByUserIds(userIds);
    }

    /**
     * 删除用户信息信息
     *
     * @param userId 用户信息主键
     * @return 结果
     */
    @Override
    public int deleteSysUserByUserId(Long userId) {
        return sysUserMapper.deleteSysUserByUserId(userId);
    }

    /**
     * 查询电话号码
     *
     * @param phone
     * @return
     */
    @Override
    public int selectByPhone(String phone) {
        return sysUserMapper.selectByPhone(phone);
    }

    /**
     * 登录验证
     *
     * @param user
     * @return
     */
    @Override
    public SysUser findByUsername(SysUser user) {
        return sysUserMapper.findByUsername(user);
    }

    /**
     * 登录验证
     *
     * @param phone
     * @return
     */
    @Override
    public String findByPhone(String phone) {
        return sysUserMapper.findByPhone(phone);
    }
}
