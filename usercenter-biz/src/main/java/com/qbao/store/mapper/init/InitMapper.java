package com.qbao.store.mapper.init;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.qbao.store.entity.init.InitEntity;

@Repository
public interface InitMapper {
    /**
     * 通过id物理删除usercenter_init的数据.
     */
    int deleteById(Integer id);

    /**
     * 向表usercenter_init中插入数据.
     */
    int insert(InitEntity record);

    /**
     * 通过id查询表usercenter_init.
     */
    InitEntity selectById(Integer id);

    /**
     * 通过id修改表usercenter_init.
     */
    int updateById(InitEntity record);
    
    /**
     * 
     *  函数名称 : selectAll
     *  功能描述 :  
     *  参数及返回值说明：
     * @param name TODO
     * @param buId TODO
     *  	@return
     *
     *  修改记录：
     *  	日期 ：2016年8月3日 下午2:56:00	修改人：  niuzan
     *  	描述	：
     *
     */
    List<InitEntity> select(@Param("name") String name, @Param("buId")String buId);
}