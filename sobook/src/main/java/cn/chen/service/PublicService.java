package cn.chen.service;

import cn.chen.entity.Book;
import cn.chen.entity.BookType;
import cn.chen.entity.Hot;

import java.util.List;
import java.util.Map;

public interface PublicService {
    //查哪总类型和几张
    public List<Hot> findHot(Integer num,Integer type);
    //模糊查询
    public List<Book> findIf(Map map);
    public int getCountByIf(String key);
    //分页查找书本信息可根据类型查询
    public List<Book> findBookByPage(Map map);
    //点赞加分
    public int addRate(Integer bookid);
    //获得点赞前五的商品
    public List<Book> findRate5();
    //获得销量前五的商品
    public List<Book> findSale5();
    //添加到购物车前五
    public List<Book> findFav5();
    //分页查询分页查找书本信息并按照点赞从高到低排序, 并且可按照类型(typeid==0 时, 类型查询不生效)
    public List<Book> findBookByPageRate(Map map);
    //分页查找书本信息并按照购物车从高到低, 并且可按照类型
    public List<Book> findBookByPageFav(Map map);
    //分页查找书本信息并按照销量从高到低排序, 并且可按照类型
    public List<Book> findBookByPageSale(Map map);

    public int getCount(Integer typeid);
}
