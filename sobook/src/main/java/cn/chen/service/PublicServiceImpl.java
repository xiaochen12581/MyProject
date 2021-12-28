package cn.chen.service;

import cn.chen.entity.Book;
import cn.chen.entity.Hot;
import cn.chen.mapper.PublicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class PublicServiceImpl implements PublicService {
    @Autowired(required = false)
    PublicMapper publicMapper;
    @Override
    public List<Hot> findHot(Integer num,Integer type) {
        Map map = new HashMap();
        map.put("num",num);
        map.put("type",type);
        List<Hot> hot = publicMapper.findHot(map);
        return hot;
    }

    @Override
    public List<Book> findIf(Map map) {
        List<Book> anIf = publicMapper.findIf(map);
        return anIf;
    }

    @Override
    public List<Book> findBookByPage(Map map) {
        List<Book> bookByPage = publicMapper.findBookByPage(map);
        return bookByPage;
    }

    @Override
    public int addRate(Integer bookid) {
        int i = publicMapper.addRate(bookid);
        return i;
    }

    @Override
    public List<Book> findRate5() {
        List<Book> rate5 = publicMapper.findRate5();
        return rate5;
    }

    @Override
    public List<Book> findSale5() {
        List<Book> sale5 = publicMapper.findSale5();
        return sale5;
    }

    @Override
    public List<Book> findFav5() {
        List<Book> fav5 = publicMapper.findFav5();
        return fav5;
    }

    @Override
    public List<Book> findBookByPageSale(Map map) {
        List<Book> bookByPageSale = publicMapper.findBookByPageSale(map);
        return bookByPageSale;
    }

    @Override
    public List<Book> findBookByPageRate(Map map) {
        List<Book> bookByPageRate = publicMapper.findBookByPageRate(map);
        return bookByPageRate;
    }

    @Override
    public List<Book> findBookByPageFav(Map map) {
        List<Book> bookByPageFav = publicMapper.findBookByPageFav(map);
        return bookByPageFav;
    }

    @Override
    public int getCount(Integer typeid) {
        int count = publicMapper.getCount(typeid);
        return count;
    }

    @Override
    public int getCountByIf(String key) {
        int countByIf = publicMapper.getCountByIf(key);
        return countByIf;
    }
}
