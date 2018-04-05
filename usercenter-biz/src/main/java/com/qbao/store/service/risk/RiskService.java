package com.qbao.store.service.risk;

import java.util.Map;

import com.bqiong.usercenter.constants.ErrorCode;

public interface RiskService 
{
	/**
	 * 
	 *  函数名称 : risk
	 *  功能描述 :  
	 *  参数及返回值说明：
	 * @param errorCode TODO
	 *  	@return
	 *
	 *  修改记录：
	 *  	日期 ：2016年8月31日 上午9:57:38	修改人：  
	 *  	描述	：风控逻辑处理
	 *
	 */
	public Map<String, String> risk(ErrorCode errorCode);
	
	/**
	 * 
	 *  函数名称 : clearRisk
	 *  功能描述 :  
	 *  参数及返回值说明：
	 *
	 *  修改记录：
	 *  	日期 ：2016年8月31日 上午9:57:48	修改人：  
	 *  	描述	：清除风控
	 *
	 */
	public void clearRisk();
	
	/**
	 * 
	 *  函数名称 : getRiskedRecord
	 *  功能描述 :  
	 *  参数及返回值说明：
	 *  	@return
	 *
	 *  修改记录：
	 *  	日期 ：2016年8月31日 上午9:58:00	修改人：  
	 *  	描述	：获取已风控记录
	 *
	 */
	public Map<String, Boolean> getRiskedRecord();
	
	public boolean isNeedBuildRiskData(ErrorCode errorCode);
	
	public boolean isNeedRiskValidation();
	
	public void validateRisk();
	
}
