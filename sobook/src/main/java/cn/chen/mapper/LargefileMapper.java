package cn.chen.mapper;

import cn.chen.entity.Largefile;

public interface LargefileMapper {
    //添加图片
    public int add(Largefile largefile);
    //删除图片
    public int delete(String id);
    //根据id查找图片
    public Largefile findById(String id);
}
