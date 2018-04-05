package com.bqiong.usercenter.httpresponse;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.bqiong.usercenter.exception.BizException;
import com.bqiong.usercenter.util.DateUtil;
import com.bqiong.usercenter.util.HttpCommonUtil;


/**
 * **********************************************************
 *  内容摘要	：<p>
 *
 *  作者	：94841
 *  创建时间	：2016年5月17日 上午11:41:06 
 *  当前版本号：v1.0
 *  历史记录	:
 *  	日期	: 2016年5月17日 上午11:41:06 	修改人：
 *  	描述	:
 ***********************************************************
 */

public class HttpResMapBuilder
{

	private static Logger logger = LoggerFactory.getLogger(HttpResMapBuilder.class);

	@SuppressWarnings("unchecked")
	public static <T> HttpResponseDto<T> parseResponse(String respoData, Class<T> t)
	{
		if (StringUtils.isBlank(respoData))
		{
			logger.error("已收到http响应内容，但内容为空！");
			throw new BizException();
		}

		Map<String, String> responseMap = JSONObject.parseObject(respoData, Map.class);
		if (responseMap == null || responseMap.isEmpty())
		{
			logger.error("解析响应内容，map为空！");
			throw new BizException();
		}

		HttpResponseDto<T> resp = buildResponse(responseMap, t);
		if (resp == null)
		{
			logger.error("解析后HttpResponseDto为null.response=" + respoData);
			throw new BizException();
		}
		return resp;
	}

	@SuppressWarnings("unchecked")
	private static <T> HttpResponseDto<T> buildResponse(Map<String, String> response, Class<T> t)
	{
		String code = String.valueOf(response.get(HttpCommonUtil.RESPONSE_CODE));
		String message = response.get(HttpCommonUtil.ERROR_MESSAGE);
		String data = String.valueOf(response.get(HttpCommonUtil.DATA));
		HttpResponseDto<T> dto = new HttpResponseDto<T>();
		dto.setCode(code);
		dto.setMessage(message);
		if (!HttpCommonUtil.isSuccess(code, message))
		{
			logger.error("返回结果标识为未成功！code=" + code + ",message=" + message + ",data=" + data);
			return dto;
		}
		List<T> list = new ArrayList<T>();
		dto.setList(list);
		try 
		{
			T result = t.newInstance();
			Map<String, String> map = JSONObject.parseObject(data, Map.class);
			transMap2Bean((Map<String, String>) map, result);
			list.add(result);
		} 
		catch (InstantiationException e) 
		{
			logger.error("反射获取实体类异常！" + e);
			throw new BizException();
		}
		catch (IllegalAccessException e)
		{
		    logger.error("反射获取实体类异常！" + e);
            throw new BizException();
        }

		return dto;
	}

	// Map --> Bean 1: 利用Introspector,PropertyDescriptor实现 Map --> Bean
	@SuppressWarnings("rawtypes")
	public static void transMap2Bean(Map<String, String> map, Object obj)
	{
		try
		{
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

			for (PropertyDescriptor property : propertyDescriptors)
			{
				String key = property.getName();

				if (map.containsKey(key) && map.get(key) != null)
				{
					Object value = map.get(key);
					if(property.getPropertyType().isAssignableFrom(Date.class))
					{
						Date date = DateUtil.getDate(String.valueOf(value), DateUtil.DATATIMEF_STR);
						Method setter = property.getWriteMethod();
						setter.invoke(obj, date);
						continue;
					}
					if(property.getPropertyType().isAssignableFrom(List.class))
					{
						List list = JSONObject.parseObject(String.valueOf(value), List.class);
						Method setter = property.getWriteMethod();
						setter.invoke(obj, list);
						continue;
					}
					Method setter = property.getWriteMethod();
					setter.invoke(obj, value.toString());
				}
			}
		} catch (Exception e)
		{
			logger.error("将map解析到bean中时失败.dto=" + obj,e);
			throw new BizException();
		}
	}
}
