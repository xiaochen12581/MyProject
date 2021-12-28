package cn.chen.mapper;
import cn.chen.entity.Hot;

import java.util.List;

public interface HotMapper {
    //查询全部
    public List<Hot> findAll();
    //添加
    public int addHot(Hot hot);
    //删除
    public int delete(int id);
    //根据uuid查询
    public Hot findByPic(String picture);
}
