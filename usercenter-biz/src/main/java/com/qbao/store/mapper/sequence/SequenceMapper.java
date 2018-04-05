package com.qbao.store.mapper.sequence;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface SequenceMapper 
{
	/**
	 * 
	 *  函数名称 : getNextVal
	 *  功能描述 :  
	 *  参数及返回值说明：
	 *  	@param sequenceName
	 *  	@return
	 *
	 *  修改记录：
	 *  	日期 ：2016年4月19日 下午2:41:45	修改人：  niuzan
	 *  	描述	：
	 *
	 */
	public Integer queryNextVal(@Param("sequenceName") String sequenceName);
	
}