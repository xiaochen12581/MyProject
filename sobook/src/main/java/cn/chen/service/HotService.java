package cn.chen.service;

import cn.chen.entity.Hot;

import java.util.List;

public interface HotService {
    //查询全部
    public List<Hot> findAll();
    //添加
    public Hot addHot(Hot hot);
    //删除
    public int delete(int id);
}
