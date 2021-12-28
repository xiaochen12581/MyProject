package cn.chen.mapper;

import cn.chen.entity.Book;

import java.util.List;
import java.util.Map;

public interface BookMapper {
    //查询全部书本
    public List<Book> findAll();
    //根据id查书
    public Book findById(Integer id);
    //分页查询
    public List<Book> findByPage(Map map);
    //添加书本
    public int addBook(Book book);
    //更新书本
    public int upBook(Book book);
    //查全部数量
    public int findTotal();
}
