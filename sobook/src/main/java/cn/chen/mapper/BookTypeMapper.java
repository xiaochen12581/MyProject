package cn.chen.mapper;

import cn.chen.entity.BookType;

import java.util.List;

public interface BookTypeMapper {
    //查询全部类型
    public List<BookType> find();
    //根据id查询
    public BookType findById(Integer id);
    //更新
    public int update(BookType bookType);
    //添加
    public int add(BookType bookType);
}
