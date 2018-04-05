package com.qbao.store.util;

import java.util.Date;

import com.bqiong.usercenter.constants.PassportType;
import com.bqiong.usercenter.constants.UserCenterConstants;
import com.qbao.store.entity.openPlatform.OpenPlatformBasicEntity;
import com.qbao.store.entity.thirdpart.BindEntity;
import com.qbao.store.entity.user.UserBasicEntity;
import com.qbao.store.entity.user.UserDetailEntity;

/**
 * **********************************************************
 *  内容摘要	：<p>
 *
 *  作者	：94841
 *  创建时间	：2016年4月29日 下午3:27:52 
 *  当前版本号：v1.0
 *  历史记录	:
 *  	日期	: 2016年4月29日 下午3:27:52 	修改人：
 *  	描述	:
 ***********************************************************
 */
public class CommonUtil 
{
	/**
	 * 
	 *  函数名称 : buildEntity
	 *  功能描述 :  
	 *  参数及返回值说明：
	 *  	@param userId
	 * @param userName
	 * @param countryCode TODO
	 * @param passport
	 * @param password
	 * @param salt
	 * @return
	 *
	 *  修改记录：
	 *  	日期 ：2016年4月29日 下午3:33:54	修改人：  
	 *  	描述	：
	 *
	 */
	public static UserBasicEntity buildUserBasicEntity(String userId, String userName, String countryCode, 
			String passport, String password, Integer status, String salt, String buId) 
	{
		UserBasicEntity basicEntity = new UserBasicEntity();
		basicEntity.setStatus(status);
		basicEntity.setUserId(userId);
		basicEntity.setUserName(userName);
		if(PassportType.mobile == PassportType.validatePassport(null,passport))
		{
			basicEntity.setCountryCode(countryCode);
			basicEntity.setMobile(passport);
		}
		else if(PassportType.email == PassportType.getPassportType(passport))
		{
			basicEntity.setEmail(passport);
			//basicEntity.setEmailStatus(UserCenterConstants.EMAIL_STATUS_NOT_VERIFY);
		}
		//basicEntity.setMobile(passport);
		basicEntity.setPassword(password);
		basicEntity.setSalt(salt);
		basicEntity.setBuId(buId);
		return basicEntity;
	}
	
	/**
	 * 
	 *  函数名称 : buildUserDetailEntity
	 *  功能描述 :  
	 *  参数及返回值说明：
	 *  	@param userId
	 *  	@param gender
	 *  	@param profile
	 *  	@param avatar
	 *  	@param birthday
	 *  	@param province
	 *  	@param city
	 *  	@param district
	 *  	@param street
	 *  	@param addressDetail
	 *  	@param allowThirdModify
	 *  	@return
	 *
	 *  修改记录：
	 *  	日期 ：2016年5月19日 下午5:48:10	修改人：  
	 *  	描述	：
	 *
	 */
	public static UserDetailEntity buildUserDetailEntity(String userId, String gender, String profile, String avatar, Date birthday,
			String province, String city, String district, String street, String addressDetail, String allowThirdModify) 
	{
		UserDetailEntity entity = new UserDetailEntity(userId);
		entity.setGender(gender);
		entity.setProfile(profile);
		entity.setAvatar(avatar);
		entity.setBirthday(birthday);
		entity.setProvince(province);
		entity.setCity(city);
		entity.setDistrict(district);
		entity.setStreet(street);
		entity.setAddressDetail(addressDetail);
		entity.setAllowThirdModify(allowThirdModify);
		return entity;
	}
	
	/**
	 * 
	 *  函数名称 : buildUserBasicEntity
	 *  功能描述 :  
	 *  参数及返回值说明：
	 *  	@param userId
	 * @param userName
	 * @param passport
	 * @param password
	 * @param status
	 * @param salt
	 *  	@return
	 *
	 *  修改记录：
	 *  	日期 ：2016年5月9日 下午5:46:50	修改人：  
	 *  	描述	：
	 *
	 */
	public static OpenPlatformBasicEntity buildOpenPlatformUserBasicEntity(String userId, String userName, String passport, 
			String password, Integer status, String salt) 
	{
		OpenPlatformBasicEntity basicEntity = new OpenPlatformBasicEntity();
		basicEntity.setStatus(status);
		basicEntity.setUserId(userId);
		basicEntity.setUserName(userName);
		if(PassportType.mobile == PassportType.validatePassport(null, passport))
		{
			basicEntity.setMobile(passport);
		}
		else if(PassportType.email == PassportType.getPassportType(passport))
		{
			basicEntity.setEmail(passport);
			basicEntity.setEmailStatus(UserCenterConstants.EMAIL_STATUS_NOT_VERIFY);
		}
		basicEntity.setPassword(password);
		basicEntity.setSalt(salt);
		return basicEntity;
	}
	
	/**
	 * 
	 *  函数名称 : buildBindEntity
	 *  功能描述 :  
	 *  参数及返回值说明：
	 *  	@param thirdId
	 *  	@param openId
	 *  	@param userId
	 *  	@param status
	 *  	@return
	 *
	 *  修改记录：
	 *  	日期 ：2016年5月18日 上午11:55:03	修改人：  niuzan
	 *  	描述	：
	 *
	 */
	public static BindEntity buildBindEntity(String thirdId, String openId, String userId, Integer status) 
    {
        BindEntity bindEntity = new BindEntity();
        bindEntity.setStatus(status);
        bindEntity.setUserId(userId);
        bindEntity.setThirdId(thirdId);
        bindEntity.setOpenId(openId);
        return bindEntity;
    }
	
}
