package com.qbao.store.helper;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bqiong.usercenter.constants.ErrorCode;
import com.bqiong.usercenter.constants.PassportType;
import com.bqiong.usercenter.constants.ThirdConstants;
import com.bqiong.usercenter.constants.ThirdPartyEnum;
import com.bqiong.usercenter.constants.UserCenterConstants;
import com.bqiong.usercenter.exception.BizException;

/**
 * **********************************************************
 *  内容摘要	：<p>
 *
 *  作者	：94841
 *  创建时间	：2016年4月15日 下午12:28:08 
 *  当前版本号：v1.0
 *  历史记录	:
 *  	日期	: 2016年4月15日 下午12:28:08 	修改人：niuzan
 *  	描述	:
 ***********************************************************
 */
public class ShardHelper 
{
	
	private static final Logger log = LoggerFactory.getLogger(ShardHelper.class);
	/**
	 * 
	 *  函数名称 : getIndexTableName
	 *  功能描述 :  根据passport获取分表名称，手机号码按照最后两位数字来分表00-99，邮箱取@前面的最后一位字符转换成数字后取最后一位分表00-99、
	 *  参数及返回值说明：
	 *  	@param passport
	 *  	@param passportType
	 *  	@return
	 *
	 *  修改记录：
	 *  	日期 ：2016年4月15日 上午10:44:14	修改人：  niuzan
	 *  	描述	：该方法不做参数格式校验，需要调用方做好。
	 *
	 */
	public static String getIndexTableName(String passport, PassportType passportType) 
	{
		if(PassportType.mobile == passportType)
		{
			Integer lastNum = Integer.parseInt(passport.substring(passport.length()-2, passport.length()));
			Integer suffix = lastNum % UserCenterConstants.SHARD_NUMBER;
			if(String.valueOf(suffix).length() != UserCenterConstants.TABLE_SUFFIX_LENGTH)
			{
				String suffixStr = "0" + suffix;
				return UserCenterConstants.INDEX_MOBILE_PREFIX + suffixStr;
			}
			return UserCenterConstants.INDEX_MOBILE_PREFIX + suffix;
		}
		if(PassportType.email == passportType)
		{
			/*int index = passport.indexOf("@");
			Integer charNum = (int)passport.substring(index - 1, index).charAt(0);
			int lastNum = Integer.parseInt(charNum.toString().substring(passport.length()-2, passport.length()));
			int suffix = lastNum % UserCenterConstants.SHARD_NUMBER;
			if(String.valueOf(suffix).length() != UserCenterConstants.TABLE_SUFFIX_LENGTH)
			{
				String suffixStr = "0" + suffix;
				return UserCenterConstants.INDEX_EMAIL_PREFIX + suffixStr;
			}
			return UserCenterConstants.INDEX_EMAIL_PREFIX + suffix;*/
			//只需要一个email的index表就够了
			return UserCenterConstants.INDEX_EMAIL_PREFIX;
		}
		return null;
	}
	
	/**
	 * 
	 *  函数名称 : getUserTableName
	 *  功能描述 :  根据userId获取表名
	 *  参数及返回值说明：
	 *  	@param userId
	 *  	@return
	 *
	 *  修改记录：
	 *  	日期 ：2016年4月15日 下午12:08:47	修改人：  niuzan
	 *  	描述	：
	 *
	 */
	public static String getUserBasicTableName(String userId) 
	{
		String idSuffix = userId.substring(userId.length()-2, userId.length());
		if(!StringUtils.isNumeric(idSuffix))
		{
			throw new BizException(ErrorCode.USERID_ILLEGLE.getCode());
		}
		int lastNum = Integer.parseInt(idSuffix);
		int suffix = lastNum % UserCenterConstants.SHARD_NUMBER;
		if(String.valueOf(suffix).length() != UserCenterConstants.TABLE_SUFFIX_LENGTH)
		{
			return UserCenterConstants.USER_BASIC_PREFIX + "0" + suffix;
		}
		else
		{
		    return UserCenterConstants.USER_BASIC_PREFIX + suffix;
		}
	}
	
	/**
	 * userExtra表的分表
	 * @param userId
	 * @return
	 */
	public static String getUserExtraTableName(String userId) 
	{
		String idSuffix = userId.substring(userId.length()-1, userId.length());
		if(!StringUtils.isNumeric(idSuffix))
		{
			log.error("illegle userid when getting shard table name 4 user extra!");
			throw new BizException(ErrorCode.USERID_ILLEGLE.getCode());
		}
		int lastNum = Integer.parseInt(idSuffix);
		int suffix = lastNum % UserCenterConstants.SHARD_NUMBER_4_EXTRA;
		return UserCenterConstants.USER_REGISTER_EXTRA_PREFIX + suffix; 
	}
	
	/**
	 * 
	 *  函数名称 : getUserDetailTableName
	 *  功能描述 :  
	 *  参数及返回值说明：
	 *  	@param userId
	 *  	@return
	 *
	 *  修改记录：
	 *  	日期 ：2016年5月19日 下午12:16:03	修改人：  NIUZAN
	 *  	描述	：
	 *
	 */
	public static String getUserDetailTableName(String userId) 
	{
		String idSuffix = userId.substring(userId.length()-2, userId.length());
		if(!StringUtils.isNumeric(idSuffix))
		{
			throw new BizException(ErrorCode.USERID_ILLEGLE.getCode());
		}
		int lastNum = Integer.parseInt(idSuffix);
		int suffix = lastNum % UserCenterConstants.SHARD_NUMBER;
		if(String.valueOf(suffix).length() != UserCenterConstants.TABLE_SUFFIX_LENGTH)
		{
			return UserCenterConstants.USER_DETAIL_PREFIX + "0" + suffix;
		}
		else
		{
		    return UserCenterConstants.USER_DETAIL_PREFIX + suffix;
		}
	}
	
	
	/**
	 * 
	 *  函数名称 : getTableNameByThird
	 *  功能描述 :  
	 *  参数及返回值说明：
	 *  	@param thirdEnum
	 *  	@param openUid
	 *  	@return
	 *
	 *  修改记录：
	 *  	日期 ：2016年5月17日 下午6:17:58	修改人：  niuzan
	 *  	描述	：
	 *
	 */
	public static String getBindTableByThird(ThirdPartyEnum thirdEnum, String openUid) 
    {
       if(ThirdPartyEnum.QBAO == thirdEnum)
       {
    	   int lastNum = Integer.parseInt(openUid.substring(openUid.length()-2, openUid.length()));
           int suffix = lastNum % UserCenterConstants.SHARD_NUMBER;
           if(String.valueOf(suffix).length() != UserCenterConstants.TABLE_SUFFIX_LENGTH)
           {
               return ThirdConstants.BIND_TABLE_QBAOID_PREFIX + "0" + suffix;
           }
           else
           {
               return ThirdConstants.BIND_TABLE_QBAOID_PREFIX + suffix;
           }
       }
           
        if(ThirdPartyEnum.FACEBOOK == thirdEnum)
        {
        	return ThirdConstants.BIND_TABLE_FACEBOOK;
        }
        return null;
    }
	
	
	/**
	 * 
	 *  函数名称 : getBindTableByDomeUid
	 *  功能描述 :  
	 *  参数及返回值说明：
	 *  	@param userId
	 *  	@return
	 *
	 *  修改记录：
	 *  	日期 ：2016年5月18日 下午2:57:13	修改人：  niuzan
	 *  	描述	：
	 *
	 */
	public static String getBindTableByDomeUid(String userId)
    {
		String idSuffix = userId.substring(userId.length()-2, userId.length());
		if(!StringUtils.isNumeric(idSuffix))
		{
			throw new BizException(ErrorCode.USERID_ILLEGLE.getCode());
		}
		int lastNum = Integer.parseInt(idSuffix);
        int suffix = lastNum % UserCenterConstants.SHARD_NUMBER;
        if(String.valueOf(suffix).length() != UserCenterConstants.TABLE_SUFFIX_LENGTH)
        {
            return ThirdConstants.BIND_TABLE_DOMEID_PREFIX + "0" + suffix;
        }
        else
        {
            return ThirdConstants.BIND_TABLE_DOMEID_PREFIX + suffix;
        }
    }
	
	public static String getLogTableName(Date date) 
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
		String timeString = formatter.format(date);
		return UserCenterConstants.LOG_PREFIX + timeString;
	}
	
	public static String getRandomDetailTableName() 
	{
		int random = RandomUtils.nextInt(100);
		if(String.valueOf(random).length() == 1)
		{
			return UserCenterConstants.USER_DETAIL_PREFIX + "0" + String.valueOf(random);
		}
		return UserCenterConstants.USER_DETAIL_PREFIX + String.valueOf(random);
	}
	
}
