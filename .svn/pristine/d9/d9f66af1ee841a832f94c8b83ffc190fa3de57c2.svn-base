package com.qbao.store.service.impl.init;

import com.bqiong.usercenter.annotation.DefDataSource;
import com.qbao.store.entity.init.InitEntity;
import com.qbao.store.mapper.init.InitMapper;
import com.qbao.store.service.InitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * **********************************************************
 * 内容摘要	：<p>
 * <p/>
 * 作者	：94841
 * 创建时间	：2016年4月21日 下午3:38:59
 * 当前版本号：v1.0
 * 历史记录	:
 * 日期	: 2016年4月21日 下午3:38:59 	修改人：
 * 描述	:
 * **********************************************************
 */
@Service("initService")
public class InitServiceImpl implements InitService {
    @Autowired
    private InitMapper initMapper;


    @DefDataSource
    @Override
    public List<InitEntity> query(String name, String buId) {
        return initMapper.select(name, buId);
    }

    @DefDataSource
    @Override
    public void add(InitEntity record) {
        initMapper.insert(record);
    }

    @DefDataSource
    @Override
    public void modify(InitEntity record) {
        initMapper.updateById(record);
    }

    @DefDataSource
    @Override
    public InitEntity queryById(Integer id) {
        return initMapper.selectById(id);
    }
}
