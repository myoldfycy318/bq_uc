package com.qbao.store.mapper.index;

import org.springframework.stereotype.Repository;

import com.qbao.store.entity.index.ImsiIndexEntity;

@Repository
public interface ImsiIndexEntityMapper {
    /**
     * 通过id物理删除user_index_imsi的数据.
     */
    int deleteById(String imsi);

    /**
     * 向表user_index_imsi中插入数据.
     */
    int insert(ImsiIndexEntity record);
    
    /**
     * 
     * @param id
     * @return
     */
    ImsiIndexEntity selectById(Integer id);

    /**
     * 通过id查询表user_index_imsi.
     */
    ImsiIndexEntity selectByImsi(String imsi);

    /**
     * 通过id修改表user_index_imsi.
     */
    int updateById(ImsiIndexEntity record);
    
    /**
     * 更新绑定状态
     * @param record
     * @return
     */
    int updateBindStatus(ImsiIndexEntity record);
}