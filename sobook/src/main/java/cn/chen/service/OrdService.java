package cn.chen.service;

import cn.chen.entity.BookCarts;
import cn.chen.entity.ItemBook;
import cn.chen.entity.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public interface OrdService {
    //购物车转订单
    public int addCart2Order(List<BookCarts> lst);
    //根据订单号查询订单的地址和电话
    public Order findByUidBid(Integer ordiId);
    //付款
    public String UpdateIspay(Order order);
    //用户查看自己的订单
    public List<Order> findAllOrd();
    //订单明细和书本联合查
    public List<ItemBook> findItemBook(Integer ordid);
    //管理员查看全部订单
    public List<Order> findAll(Integer start,Integer num);
}
