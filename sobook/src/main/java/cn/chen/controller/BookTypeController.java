package cn.chen.controller;

import cn.chen.entity.BookType;
import cn.chen.service.BookTypeService;
import cn.chen.tool.RespJson;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
//允许跨域访问
@CrossOrigin
@Controller
@RequestMapping("/api/adm/type")
public class BookTypeController {
    @Autowired
    BookTypeService bookTypeService;
    @ResponseBody
    @RequestMapping("/findAll")
    public Map<String,Object> findAll(){
        List<BookType> all = bookTypeService.findAll();
        return RespJson.pack(200,"OK",all);
    }
    @ResponseBody
    @RequestMapping("/findById/{id}")
    public Map<String,Object> findById(@PathVariable("id") Integer id){
        System.out.println("id = " + id);
        BookType byId = bookTypeService.findById(id);
        return RespJson.pack(200,"单个查询成功",byId);
    }
    @ResponseBody
    @RequestMapping("/add")
    public Map<String,Object> add(@RequestBody BookType bookType){
        List<BookType> add = bookTypeService.add(bookType);
        return RespJson.pack(200,"添加成功",add);
    }

}
