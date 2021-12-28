package cn.chen.service;

import cn.chen.entity.SysUser;
import cn.chen.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired(required = false)
    SysUserMapper sysUserMapper;
    @Override
    public List<SysUser> AllUser() {
        List<SysUser> sysUsers = sysUserMapper.AllUser();
        return sysUsers;
    }

    @Override
    public SysUser findById(int id) {
        SysUser byId = sysUserMapper.findById(id);
        return byId;
    }

    @Override
    public int delById(int id) {
        int i = sysUserMapper.delById(id);
        return i;
    }

    @Override
    public int addUser(SysUser user) {
        user.setRole("P");
        user.setClosed(0);
        int i = sysUserMapper.addUser(user);
        return i;
    }

    @Override
    public int addAdm(SysUser user) {
        user.setRole("G");
        user.setClosed(0);
        int i = sysUserMapper.addUser(user);
        return i;
    }
}
