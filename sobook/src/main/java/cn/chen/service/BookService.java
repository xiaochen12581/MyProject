package cn.chen.service;

import cn.chen.entity.Book;

import java.util.List;

public interface BookService {
    //查找全部
    public List<Book> findAll();
    //根据id查书本
    public Book findById(Integer id);
    //分页查询
    public List<Book> findByPage(Integer start,Integer num);
    //添加还是更新书本
    public Book addBook(Book book);
    //查数量
    public int findTotal();
}
