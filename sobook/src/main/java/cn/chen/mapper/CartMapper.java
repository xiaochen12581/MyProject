package cn.chen.mapper;

import cn.chen.entity.BookCart;
import cn.chen.entity.Cart;

import java.util.List;

public interface CartMapper {
    //添加到购物车
    public int add(Cart cart);
    //查看购物车内是否有这本书
    public Cart findByUidBid(Cart cart);
    //用户查看自己的购物车
    public List<BookCart> bookCartById(Integer uid);
    //根据id删除购物车内的物品
    public int delById(Integer id);
}
