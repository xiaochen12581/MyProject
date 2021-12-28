package cn.chen.service;

import cn.chen.entity.Hot;
import cn.chen.mapper.HotMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class HotServiceImpl implements HotService {
    @Autowired(required = false)
    HotMapper hotMapper;
    @Override
    public List<Hot> findAll() {
        return hotMapper.findAll();
    }

    @Override
    public Hot addHot(Hot hot) {
        hotMapper.addHot(hot);
        Hot ht = hotMapper.findByPic(hot.getPicture());
        return ht;
    }

    @Override
    public int delete(int id) {
        int i = hotMapper.delete(id);
        return i;
    }
}
