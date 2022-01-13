package cn.chen.controller;

import cn.chen.entity.Book;
import cn.chen.entity.Largefile;
import cn.chen.service.BookService;
import cn.chen.service.LargefileService;
import cn.chen.tool.RespJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin
@Controller
@RequestMapping("/api/adm/book")
public class BookController {
    @Autowired
    BookService bookService;
    @Autowired
    LargefileService largefileService;
    @ResponseBody
    @RequestMapping("/findAll")
    public Map<String,Object> findAll(){
        List<Book> all = bookService.findAll();
        System.out.println(all);
        if (all != null){
            return RespJson.pack(200,"查询成功",all);
        }else {
            return RespJson.pack(200,"查询失败",null);
        }
    }
    @ResponseBody
    @RequestMapping("/findById/{bookid}")
    public Map<String,Object> findById(@PathVariable("bookid") Integer id){
        Book byId = bookService.findById(id);
        if (byId!=null){
            return RespJson.pack(200,"查询成功",byId);
        }else {
            return RespJson.pack(200,"查询失败",null);
        }
    }
    @ResponseBody
    @RequestMapping("/findByPage/{start}/{num}")
    public Map<String,Object> findByPage(@PathVariable("start") Integer start,@PathVariable("num") Integer num){
        List<Book> books = bookService.findByPage(start, num);
        int total = bookService.findTotal();
        Map map = new HashMap();
        map.put("book",books);
        map.put("total",total);
        if (books!=null){
            return RespJson.pack(200,"查询成功",map);
        }else {
            return RespJson.pack(200,"查询失败",null);
        }
    }
    @ResponseBody
    @RequestMapping("/addBook")
    public Map<String,Object> addBook(Book book,@RequestParam("file") MultipartFile f) throws Exception{
            Largefile largefile = new Largefile();
            String newname = UUID.randomUUID().toString();
            System.out.println("newname = " + newname);
            largefile.setId(newname);
            largefile.setFilename(f.getOriginalFilename());
            byte[] buf = f.getBytes();//上传文件内容
            largefile.setContent(buf);
            largefileService.add(largefile);//图片放进数据库
            book.setPicture(newname);
            System.out.println("largefile = " + largefile);
            Book book1 = bookService.addBook(book);
            return RespJson.pack(200,"添加成功",book1);
    }
    @ResponseBody
    @RequestMapping("/show/{id}")
    public void show(@PathVariable("id") String id,HttpServletResponse response){
        Largefile byId = largefileService.findById(id);
        try{
            Object obj = byId.getContent();//通过get方法拿到文件
            byte[] buf = (byte[]) obj;//向下转型
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(buf);
            outputStream.flush();//排出流中所有数据
            outputStream.close();//关闭输出流
        }catch (Exception e){
            System.out.println("e = " + e);
        }
    }
    //日期处理
    @InitBinder
    public void initBinder(WebDataBinder binder){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class,new CustomDateEditor(sdf,true));
    }

}
