package cn.chen.controller;

import cn.chen.entity.ItemBook;
import cn.chen.entity.Largefile;
import cn.chen.entity.Order;
import cn.chen.entity.SysUser;
import cn.chen.service.LargefileService;
import cn.chen.service.OrdService;
import cn.chen.service.UserService;
import cn.chen.tool.RespJson;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@CrossOrigin
@Controller
@RequestMapping("/api/adm")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    LargefileService largefileService;
    @Autowired
    OrdService ordService;
    @ResponseBody
    @GetMapping("/user/AllUser")
    public Map<String,Object> AllUser(){
        List<SysUser> sysUsers = userService.AllUser();
        if (sysUsers != null){
            return RespJson.pack(200,"查询成功",sysUsers);
        }else {
            return RespJson.pack(200,"查询失败",null);
        }
    }
    @ResponseBody
    @GetMapping("/user/delById/{id}")
    public Map<String,Object> delById(@PathVariable("id") Integer id){
        SysUser byId = userService.findById(id);
        if (byId.getImg()!=null){
            int delete = largefileService.delete(byId.getImg());
            if (delete>0){
                System.out.println("图片删除成功");
            }
        }else {
            System.out.println("未找到相关用户的照片.........");
        }
        int i = userService.delById(id);
        if (i>0){
            List<SysUser> sysUsers = userService.AllUser();
            return RespJson.pack(200,"删除用户成功",sysUsers);
        }else {
            return RespJson.pack(200,"删除用户失败，请重新删除",null);
        }
    }
    @ResponseBody
    @PostMapping("/user/addAdm")
    //添加管理员
    public Map<String,Object> addAdm(SysUser user, @RequestParam("file")MultipartFile f) throws Exception{
        PasswordEncoder pe = new BCryptPasswordEncoder();
        user.setPassword(pe.encode(user.getPassword()));
        Largefile largefile = new Largefile();
        String name = UUID.randomUUID().toString();
        largefile.setId(name);
        user.setImg(name);
        largefile.setFilename(f.getOriginalFilename());
        byte[] bytes = f.getBytes();
        largefile.setContent(bytes);
        int add = largefileService.add(largefile);
        if (add>0){
            System.out.println("照片添加成功");
        }
        int i = userService.addAdm(user);
        if (i>0){
            return RespJson.pack(200,"管理员注册成功",null);
        }else {
            return RespJson.pack(200,"管理员注册失败",null);
        }
    }
    @ResponseBody
    @GetMapping("/ord/findAll/{start}/{num}")
    public Map<String,Object> findAll(@PathVariable Integer start,@PathVariable Integer num){
        List<Order> all = ordService.findAll((start-1) * num, num);
        return RespJson.pack(200,"查询成功",all);
    }
    @ResponseBody
    @GetMapping("/ord/findByOid/{ordid}")
    public Map<String,Object> findByOid(@PathVariable Integer ordid){
        List<ItemBook> itemBook = ordService.findItemBook(ordid);
        return RespJson.pack(200,"查询成功",itemBook);
    }
}
