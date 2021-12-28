package cn.chen.service;

import cn.chen.entity.Book;
import cn.chen.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class BookServiceImpl implements BookService {
    @Autowired(required = false)
    BookMapper bookMapper;
    @Override
    public List<Book> findAll() {
        return bookMapper.findAll();
    }

    @Override
    public Book findById(Integer id) {
        return bookMapper.findById(id);
    }

    @Override
    public List<Book> findByPage(Integer start, Integer num) {
        Map map = new HashMap();
        map.put("start",start*num - num);
        map.put("num",num);
        return bookMapper.findByPage(map);
    }

    @Override
    public Book addBook(Book book) {
        if (book.getBookid()!=null && book.getBookid()>0){
            bookMapper.upBook(book);
            Book byId = bookMapper.findById(book.getBookid());
            return byId;
        }else {
            bookMapper.addBook(book);
            Book byId = bookMapper.findById(book.getBookid());
            return byId;
        }
    }

    @Override
    public int findTotal() {
        return bookMapper.findTotal();
    }
}
