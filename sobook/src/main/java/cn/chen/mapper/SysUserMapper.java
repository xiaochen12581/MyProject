package cn.chen.mapper;

import cn.chen.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserMapper {
    //用户查看登录
    public SysUser findUserByAccount(String s);
    //查询全部用户
    public List<SysUser> AllUser();
    //根据用户id删除用户
    public int delById(int id);
    //根据id查用户
    public SysUser findById(int id);
    //用户注册
    public int addUser(SysUser user);
}
