package cn.chen.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/info")
public class InfoController {
    
    //通过token_id获取当前的用户是谁
    @GetMapping("/getUser")
    public Object getUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("principal = " + principal);
        if (principal == null) return null;
        if (principal instanceof UserDetails){
            return (UserDetails) principal;
        }else {
            return String.valueOf(principal);
        }
    }
}
