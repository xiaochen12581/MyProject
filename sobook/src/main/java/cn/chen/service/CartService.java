package cn.chen.service;

import cn.chen.entity.BookCart;
import cn.chen.entity.Cart;

import java.util.List;
import java.util.Map;

public interface CartService {
    public String add(Cart cart);
    //当前用户查看自己的购物车
    public List<BookCart> bookCartById(Integer uid);
    //根据id删除
    public List<BookCart> delById(Cart cart);
    //购物车转为订单后删除被选中的物品
    public int del(Integer id);

}
