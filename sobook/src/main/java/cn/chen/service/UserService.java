package cn.chen.service;

import cn.chen.entity.SysUser;

import java.util.List;

public interface UserService {
    //查全部用户
    public List<SysUser> AllUser();
    //根据id查用户
    public SysUser findById(int id);
    //根据id删除用户
    public int delById(int id);
    //用户注册
    public int addUser(SysUser user);
    //管理员注册
    public int addAdm(SysUser user);
}
