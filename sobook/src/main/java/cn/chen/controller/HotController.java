package cn.chen.controller;

import cn.chen.entity.Hot;
import cn.chen.entity.Largefile;
import cn.chen.service.HotService;
import cn.chen.service.LargefileService;
import cn.chen.tool.RespJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@CrossOrigin
@Controller
@RequestMapping("/api/adm/hot")
public class HotController {
    @Autowired
    HotService hotService;
    @Autowired
    LargefileService largefileService;
    @ResponseBody
    @RequestMapping("/findAll")
    public Map<String,Object> findAll(){
        List<Hot> all = hotService.findAll();
        return RespJson.pack(200,"查询成功",all);
    }
    @ResponseBody
    @RequestMapping("/addHot")
    public Map<String,Object> addHot(Hot hot,@RequestParam("file") MultipartFile f) throws Exception{
        Largefile largefile = new Largefile();
            String newname = UUID.randomUUID().toString();
            hot.setPicture(newname);
            largefile.setId(newname);
            largefile.setFilename(f.getOriginalFilename());
            byte[] buf = f.getBytes();//上传文件内容
            largefile.setContent(buf);
            largefileService.add(largefile);//图片放进数据库
            Hot hot1 = hotService.addHot(hot);
            return RespJson.pack(200,"添加成功",hot1);
    }
    @ResponseBody
    @RequestMapping("/delete/{id}")
    public Map<String,Object> delete(@PathVariable("id") Integer id){
        int i = hotService.delete(id);
        if (i>0){
            List<Hot> all = hotService.findAll();
            return RespJson.pack(200,"删除成功",all);
        }else {
            return RespJson.pack(200,"删除失败",null);
        }
    }

}
