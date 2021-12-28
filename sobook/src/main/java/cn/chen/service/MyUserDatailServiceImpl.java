package cn.chen.service;

import cn.chen.entity.SysUser;
import cn.chen.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Primary
@Service
public  class MyUserDatailServiceImpl implements UserDetailsService {
    @Autowired(required = false)
    SysUserMapper sysUserMapper;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println("sysUserMapper = " + sysUserMapper);
        SysUser u = sysUserMapper.findUserByAccount(s);
        System.out.println("u = " + u);
        return u;
    }
}
