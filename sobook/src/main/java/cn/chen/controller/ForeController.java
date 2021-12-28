package cn.chen.controller;

import cn.chen.entity.BookCart;
import cn.chen.entity.Cart;
import cn.chen.entity.SysUser;
import cn.chen.service.CartService;
import cn.chen.tool.RespJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@CrossOrigin
@Controller
@RequestMapping("/api/fore/ord")
public class ForeController {
    @Autowired
    CartService cartService;
    //点击按钮添加到购物车
    @ResponseBody
    @PostMapping("/addCart")
    public Map<String,Object> addCart(Cart cart){
        SysUser user = RespJson.getUser();
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        cart.setTcreate(sqlDate);
        cart.setUid(user.getId());
        cart.setNum(1);
        String add = cartService.add(cart);
        return RespJson.pack(200,add,null);
    }
    //当前用户查看自己的购物车
    @ResponseBody
    @GetMapping("/bookCartById")
    public Map<String,Object> bookCartById(){
        SysUser user = RespJson.getUser();
        List<BookCart> bookCarts = cartService.bookCartById(user.getId());
        System.out.println("bookCarts = " + bookCarts);
        return RespJson.pack(200,"查询成功",bookCarts);
    }
    @ResponseBody
    @PostMapping("/delById")
    public Map<String,Object> delById(Cart cart){
        SysUser user = RespJson.getUser();
        cart.setUid(user.getId());
        List<BookCart> bookCarts = cartService.delById(cart);
        if (bookCarts != null){
            return RespJson.pack(200,"删除成功,已将书本从购物车中移除",bookCarts);
        }else {
            return RespJson.pack(200,"删除失败,请重新删除",null);
        }
    }
    @ResponseBody
    @GetMapping("/del/{id}")
    public Map<String,Object> del(@PathVariable Integer id){
        int del = cartService.del(id);
        return RespJson.pack(200,"删除成功",del);
    }
}
