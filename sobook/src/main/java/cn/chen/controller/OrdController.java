package cn.chen.controller;

import cn.chen.entity.BookCarts;
import cn.chen.entity.ItemBook;
import cn.chen.entity.Order;
import cn.chen.service.OrdService;
import cn.chen.tool.RespJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@Controller
@RequestMapping("/api/fore/cart")
public class OrdController {
    @Autowired
    OrdService ordService;
    @ResponseBody
    @PostMapping("/addCart2Order")
    //购物车转订单，进行t_order的单条插入和t_orditem表的一条或多条插入操作，返回待确认订单详情
    public Map<String, Object> addCart2Order(@RequestBody List<BookCarts> lst) {
        int ordid = ordService.addCart2Order(lst);
        return RespJson.pack(200,"转订单成功",ordid);
    }
    @ResponseBody
    @GetMapping("/findOrdByOid/{ordid}")
    public Map<String,Object> findOrdByOid(@PathVariable Integer ordid){
        Order byUidBid = ordService.findByUidBid(ordid);
        return RespJson.pack(200,"查询成功",byUidBid);
    }
    @ResponseBody
    @PostMapping("/UpdateIspay")
    public Map<String,Object> UpdateIspay(@RequestBody Order order){
        String s = ordService.UpdateIspay(order);
        return RespJson.pack(200,"付款成功",s);
    }
    @ResponseBody
    @GetMapping("/findAllOrd")
    public Map<String,Object> findAllOrd(){
        List<Order> allOrd = ordService.findAllOrd();
        return RespJson.pack(200,"查询成功",allOrd);
    }
    @ResponseBody
    @GetMapping("/findItemBook/{ordid}")
    public Map<String,Object> findItemBook(@PathVariable Integer ordid){
        List<ItemBook> itemBook = ordService.findItemBook(ordid);
        return RespJson.pack(200,"查询成功",itemBook);
    }


}
