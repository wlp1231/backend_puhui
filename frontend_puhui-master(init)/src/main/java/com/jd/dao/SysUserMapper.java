package com.jd.dao;

import com.jd.bean.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户信息Mapper接口
 * @author qst
 * @date 2021-09-04
 */
@Mapper
public interface SysUserMapper 
{
    /**
     * 登录验证
     * @param phone
     * @return
     */
    String findByPhone(String phone);

    /**
     * 登录验证
     * @param user
     * @return
     */
    SysUser findByUsername(SysUser user);

    /**
     * 查询电话号码
     * @param phone
     * @return
     */
    int selectByPhone(String phone);
    /**
     * 根据激活码查询用户信息
     *
     * @param remark 激活码
     * @return 用户信息
     */
    SysUser selectUserByActiveCode(String remark);

    /**
     * 查询用户信息
     * 
     * @param userId 用户信息主键
     * @return 用户信息
     */
    public SysUser selectSysUserByUserId(Long userId);

    /**
     * 查询用户信息列表
     * 
     * @param sysUser 用户信息
     * @return 用户信息集合
     */
    public List<SysUser> selectSysUserList(SysUser sysUser);

    /**
     * 新增用户信息
     * 
     * @param sysUser 用户信息
     * @return 结果
     */
    public int insertSysUser(SysUser sysUser);

    /**
     * 修改用户信息
     * 
     * @param sysUser 用户信息
     * @return 结果
     */
    public int updateSysUser(SysUser sysUser);

    /**
     * 删除用户信息
     * 
     * @param userId 用户信息主键
     * @return 结果
     */
    public int deleteSysUserByUserId(Long userId);

    /**
     * 批量删除用户信息
     * 
     * @param userIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysUserByUserIds(Long[] userIds);
}
