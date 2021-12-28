package cn.chen.tool;

import cn.chen.entity.SysUser;
import cn.chen.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class RespJson {
    public static Map<String,Object> pack(Integer code, String msg, Object obj){
        Map<String,Object> map = new HashMap();
        map.put("code",code);
        map.put("msg",msg);
        map.put("data",obj);
        return map;
    }
    public static SysUser getUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SysUser sysUser = (SysUser) principal;
        return sysUser;
    }
}
