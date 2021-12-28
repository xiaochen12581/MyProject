package cn.chen.service;

import cn.chen.entity.BookCart;
import cn.chen.entity.Cart;
import cn.chen.mapper.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CartServiceImpl implements CartService {
    @Autowired(required = false)
    CartMapper cartMapper;
    @Override
    public String add(Cart cart) {
        Cart byUidBid = cartMapper.findByUidBid(cart);
        if (byUidBid == null){
            int add = cartMapper.add(cart);
            return "成功将书本添加进购物车";
        }else {
            return "购物车内已经有该书本,请勿重复添加";
        }
    }

    @Override
    public List<BookCart> bookCartById(Integer uid) {
        List<BookCart> bookCarts = cartMapper.bookCartById(uid);
        return bookCarts;
    }

    @Override
    public List<BookCart> delById(Cart cart) {
        int i = cartMapper.delById(cart.getId());
        if (i>0){
            List<BookCart> bookCarts = cartMapper.bookCartById(cart.getUid());
            return bookCarts;
        }else {
            return null;
        }
    }

    @Override
    public int del(Integer id) {
        int i = cartMapper.delById(id);
        return i;
    }
}
