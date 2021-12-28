package cn.chen.service;

import cn.chen.entity.BookType;

import java.util.List;

public interface BookTypeService {
    public List<BookType> findAll();
    public BookType findById(Integer id);
    public List<BookType> add(BookType bookType);
}
