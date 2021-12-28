package cn.chen.controller;

import cn.chen.entity.*;
import cn.chen.service.*;
import cn.chen.tool.RespJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@CrossOrigin
@Controller
@RequestMapping("/api/public")
public class PublicController {
    @Autowired
    BookTypeService bookTypeService;
    @Autowired
    PublicService publicService;
    @Autowired
    LargefileService largefileService;
    @Autowired
    BookService bookService;
    @Autowired
    UserService userService;
    @ResponseBody
    @RequestMapping("/findType")
    public Map<String,Object> findType(){
        List<BookType> all = bookTypeService.findAll();
        return RespJson.pack(200,"查询成功",all);
    }
    @ResponseBody
    @RequestMapping("/findAll/{start}/{num}")
    public Map<String,Object> findAll(@PathVariable("start") Integer start, @PathVariable("num") Integer num){
        List<Book> books = bookService.findByPage(start, num);
        int total = bookService.findTotal();
        Map<String,Object> map = new HashMap<>();
        map.put("total",total);
        map.put("books",books);
        return RespJson.pack(200,"查询成功",map);
    }
    @ResponseBody
    @RequestMapping("/findHot/{num}/{type}")
    public Map<String,Object> findHot(@PathVariable("num") Integer num, @PathVariable("type") Integer type){
        List<Hot> hot = publicService.findHot(num, type);
        return RespJson.pack(200,"查询成功",hot);
    }
    @ResponseBody
    @RequestMapping("/book/findIf/{start}/{num}")
    public Map<String,Object> findIf(@PathVariable("start") Integer start,@PathVariable("num") Integer num,String key){
        Map<Object, Object> map = new HashMap<>();
        map.put("start",start*num);
        map.put("num",num);
        map.put("key",key);
        List<Book> books = publicService.findIf(map);
        for (int i=0;i<books.size();i++){
            String name = books.get(i).getName().replace(key, "<span style='color:red; font-size: 20px;'>" + key + "</span>");
            books.get(i).setName(name);
            String author = books.get(i).getAuthor().replace(key, "<span style='color:red; font-size: 20px;'>" + key + "</span>");
            books.get(i).setAuthor(author);
            String publisher = books.get(i).getPublisher().replace(key, "<span style='color:red; font-size: 20px;'>" + key + "</span>");
            books.get(i).setPublisher(publisher);
        }
        int i = publicService.getCountByIf(key);
        Map<Object, Object> map2 = new HashMap<>();
        map2.put("total",i);
        map2.put("books",books);
        return RespJson.pack(200,"查询成功",map2);
    }

    @ResponseBody
    @RequestMapping("/findByPage/{start}/{num}/{typeid}")
    public Map<String,Object> findBookByPage(@PathVariable("start") Integer start, @PathVariable("num") Integer num, @PathVariable("typeid") Integer typeid){
        Map<Object, Object> map = new HashMap<>();
        map.put("start",start*num);
        map.put("num",num);
        map.put("typeid",typeid);
        List<Book> books = publicService.findBookByPage(map);
        Map<Object, Object> map2 = new HashMap<>();
        int count = publicService.getCount(typeid);
        System.out.println("count = " + count);
        map2.put("books",books);
        map2.put("total",count);
        return RespJson.pack(200,"查询成功",map2);
    }

    @ResponseBody
    @RequestMapping("/book/addRate/{bookid}")
    public Map<String,Object> addRate(@PathVariable("bookid") Integer bookid){
        publicService.addRate(bookid);
        return RespJson.pack(200,"点赞加分",null);
    }

    @ResponseBody
    @RequestMapping("/book/findRate5")
    public Map<String,Object> addRate() {   // 点赞 前五 的 商品
        List<Book> books = publicService.findRate5();
        return RespJson.pack(200,"查询成功",books);
    }

    @ResponseBody
    @RequestMapping("/book/findSale5")
    public Map<String,Object> findSale5() { //销量前五
        List<Book> books = publicService.findSale5();
        return RespJson.pack(200,"查询成功",books);
    }

    @ResponseBody
    @RequestMapping("/book/findFav5")
    public Map<String,Object> findFav5() {
        List<Book> books = publicService.findFav5();
        return RespJson.pack(200,"查询成功",books);
    }

    @ResponseBody
    @RequestMapping("/findBookByPageSale/{start}/{num}/{typeid}")
    public  Map<String,Object> findBookByPageSale(@PathVariable("start")Integer start,@PathVariable("num")Integer num, @PathVariable("typeid") Integer typeid){
        Map<Object, Object> map = new HashMap<>();
        map.put("start",start*num);
        map.put("num",num);
        map.put("typeid",typeid);
        List<Book> books = publicService.findBookByPageSale(map);
        Map<Object, Object> map2 = new HashMap<>();
        int count = publicService.getCount(typeid);
        map2.put("books",books);
        map2.put("total",count);
        return RespJson.pack(200,"查询成功",map2);
    }


    @ResponseBody
    @RequestMapping("/findBookByPageRate/{start}/{num}/{typeid}")
    public  Map<String,Object> findBookByPageRate(@PathVariable("start")Integer start,@PathVariable("num")Integer num, @PathVariable("typeid") Integer typeid){
        Map<Object, Object> map = new HashMap<>();
        map.put("start",start*num);
        map.put("num",num);
        map.put("typeid",typeid);
        List<Book> books = publicService.findBookByPageRate(map);
        Map<Object, Object> map2 = new HashMap<>();
        int count = publicService.getCount(typeid);
        map2.put("books",books);
        map2.put("total",count);
        return RespJson.pack(200,"查询成功",map2);
    }

    @ResponseBody
    @RequestMapping("/findBookByPageFav/{start}/{num}/{typeid}")
    public  Map<String,Object> findBookByPageFav(@PathVariable("start")Integer start, @PathVariable("num")Integer num,@PathVariable("typeid") Integer typeid){
        Map<Object, Object> map1 = new HashMap<>();
        map1.put("start",start*num);
        map1.put("num",num);
        map1.put("typeid",typeid);
        List<Book> books = publicService.findBookByPageFav(map1);
        Map<Object, Object> map2 = new HashMap<>();
        int ct = publicService.getCount(typeid);
        map2.put("books",books);
        map2.put("total",ct);
        return RespJson.pack(200,"查询成功",map2);
    }
    //用户注册
    @ResponseBody
    @RequestMapping("/addUser")
    public Map<String,Object> addUser(SysUser user,@RequestParam("file") MultipartFile f)throws Exception{
        PasswordEncoder pe = new BCryptPasswordEncoder();
        String encode = pe.encode(user.getPassword());
        user.setPassword(encode);
        Largefile largefile = new Largefile();
        String name = UUID.randomUUID().toString();
        largefile.setId(name);
        user.setImg(name);
        largefile.setFilename(f.getOriginalFilename());
        byte[] bytes = f.getBytes();
        largefile.setContent(bytes);
        int add = largefileService.add(largefile);
        if (add>0){
            System.out.println("照片添加成功");
        }
        int i = userService.addUser(user);
        if (i>0){
           return RespJson.pack(200,"用户注册成功",null);
        }else {
           return RespJson.pack(200,"注册失败",null);
        }
    }


    @ResponseBody
    @RequestMapping("/showImg/{id}")
    public void showImg(@PathVariable("id") String id, HttpServletResponse response){
        try{
            Largefile largeFile = largefileService.findById(id);
            if (id ==null && largeFile == null)return;
            Object obj = largeFile.getContent();
            byte[] buf = (byte[]) obj;
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(buf);
            outputStream.flush();
            outputStream.close();
        }catch (Exception e){
            System.out.println("e = " + e);
        }
    }



}
