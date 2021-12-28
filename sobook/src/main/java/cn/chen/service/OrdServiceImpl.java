package cn.chen.service;

import cn.chen.entity.*;
import cn.chen.mapper.OrdMapper;
import cn.chen.tool.RespJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class OrdServiceImpl implements OrdService {
    @Autowired(required = false)
    OrdMapper ordMapper;
    @Override
    @Transactional
    //购物车转订单
    public int addCart2Order(List<BookCarts> lst) {
        //先t_order
        Order od = new Order();
        SysUser user = RespJson.getUser();
        od.setUid(user.getId());
        Integer amount = lst.get(0).getAmount();
        double v = amount.doubleValue();
        od.setAmount(v);
        od.setTcreate(lst.get(0).getTcreate());
        od.setAddr(lst.get(0).getAddr());
        od.setPhone(lst.get(0).getPhone());
        od.setIspay(0);
        System.out.println("od = " + od);
        int i1 = ordMapper.addOrd(od);
        System.out.println("i1 = " + i1);
        if (i1>0){
            Integer ordid = od.getOrdid();
            for (int i = 0; i < lst.size(); i++) {
                Orditem orditem = new Orditem();
                orditem.setBookid(lst.get(i).getBookid());
                orditem.setNum(lst.get(i).getNum());
                orditem.setPrice(lst.get(i).getPrice());
                orditem.setOrdid(ordid);
                int i2 = ordMapper.addOrdItem(orditem);
            }
        }
        Integer ordid = od.getOrdid();
        return ordid;
    }

    @Override
    public Order findByUidBid(Integer ordiId) {
        Order byOrdId = ordMapper.findByOrdId(ordiId);
        return byOrdId;
    }

    @Override
    @Transactional
    public String UpdateIspay(Order order) {
        int i = ordMapper.UpdateIspay(order);
        if (i>0){
            return "付款成功";
        }else {
            return "付款失败";
        }
    }

    @Override
    public List<Order> findAllOrd() {
        SysUser user = RespJson.getUser();
        List<Order> allOrd = ordMapper.findAllOrd(user.getId());
        return allOrd;
    }

    @Override
    public List<ItemBook> findItemBook(Integer ordid) {
        List<ItemBook> itemBook = ordMapper.findItemBook(ordid);
        return itemBook;
    }

    @Override
    public List<Order> findAll(Integer start, Integer num) {
        Map map = new HashMap();
        map.put("start",start);
        map.put("num",num);
        List<Order> all = ordMapper.findAll(map);
        return all;
    }
}
