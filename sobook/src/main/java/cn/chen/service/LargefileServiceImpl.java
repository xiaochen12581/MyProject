package cn.chen.service;

import cn.chen.entity.Largefile;
import cn.chen.mapper.LargefileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LargefileServiceImpl implements LargefileService {
    @Autowired(required = false)
    LargefileMapper largMapper;
    @Override
    public int add(Largefile largefile) {
        int i = largMapper.add(largefile);
        return i;
    }

    @Override
    public int delete(String id) {
        int i = largMapper.delete(id);
        return i;
    }

    @Override
    public Largefile findById(String id) {
        Largefile byId = largMapper.findById(id);
        return byId;
    }
}
