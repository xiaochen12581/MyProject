package cn.chen.mapper;

import cn.chen.entity.ItemBook;
import cn.chen.entity.Order;
import cn.chen.entity.Orditem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrdMapper {
    //购物车转订单
    public int addOrd(Order order);
    //订单生成订单明细
    public int addOrdItem(Orditem orditem);
    //根据订单号查询
    public Order findByOrdId(Integer ordid);
    //付款
    public int UpdateIspay(Order order);
    //用户查询自己的全部订单
    public List<Order> findAllOrd(Integer uid);
    //书本和订单号联合查询,明细查询
    public List<ItemBook> findItemBook(Integer ordid);
    //管理员查看全部订单
    public List<Order> findAll(Map map);
}
