package com.qbao.store.mapper.log;

import org.apache.ibatis.annotations.Param;

import com.qbao.store.entity.log.UserOperationLogEntity;

public interface UserOperationLogEntityMapper {
    /**
     * 通过id物理删除user_operation_log的数据.
     */
    int deleteById(Integer id);

    /**
     * 向表user_operation_log中插入数据.
     * @param tableName TODO
     */
    int insert(@Param("entity")UserOperationLogEntity record, @Param("tableName")String tableName) throws Exception;

    /**
     * 通过id查询表user_operation_log.
     */
    UserOperationLogEntity selectById(Integer id);

    /**
     * 通过id修改表user_operation_log.
     */
    int updateById(UserOperationLogEntity record);
}