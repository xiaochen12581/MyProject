package cn.chen.service;

import cn.chen.entity.BookType;
import cn.chen.mapper.BookTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BooKTypeServiceImpl implements BookTypeService {
    @Autowired(required = false)
    BookTypeMapper bookTypeMapper;
    @Override
    public List<BookType> findAll() {
        return bookTypeMapper.find();
    }

    @Override
    public BookType findById(Integer id) {
        return bookTypeMapper.findById(id);
    }

    @Override
    public List<BookType> add(BookType bookType) {
        if (bookType.getId()>0){
            bookTypeMapper.update(bookType);
            return bookTypeMapper.find();
        }else {
            bookTypeMapper.add(bookType);
            return bookTypeMapper.find();
        }
    }

}
