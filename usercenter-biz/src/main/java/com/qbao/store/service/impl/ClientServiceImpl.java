package com.qbao.store.service.impl;

import java.util.List;

import org.apache.commons.httpclient.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bqiong.usercenter.constants.ErrorCode;
import com.bqiong.usercenter.exception.BizException;
import com.bqiong.usercenter.http.HttpClientService;
import com.bqiong.usercenter.http.HttpRequest;
import com.bqiong.usercenter.http.HttpRequest.HttpMethodType;
import com.bqiong.usercenter.http.HttpResponse;
import com.bqiong.usercenter.httpresponse.HttpResMapBuilder;
import com.bqiong.usercenter.httpresponse.HttpResponseDto;
import com.bqiong.usercenter.util.HttpCommonUtil;
import com.qbao.store.entity.ClientEntity;
import com.qbao.store.mapper.ClientMapper;
import com.qbao.store.service.ClientService;

/**
 * **********************************************************
 *  内容摘要	：<p>
 *
 *  作者	：94841
 *  创建时间	：2016年4月28日 下午4:46:35 
 *  当前版本号：v1.0
 *  历史记录	:
 *  	日期	: 2016年4月28日 下午4:46:35 	修改人：nz
 *  	描述	:
 ***********************************************************
 */
@Service("clientService")
public class ClientServiceImpl implements ClientService 
{
	private static final Logger log = LoggerFactory.getLogger(ClientServiceImpl.class);
	
	@Autowired
	ClientMapper clientMapper;
	
	@Autowired
	HttpClientService httpClientService;
	
	
	/**
	 * 
	 *  函数名称 ：insert
	 *  功能描述 ：  
	 *  参数说明 ：
	 *  	@param entity
	 *  	@return
	 *  返回值：
	 *  	
	 *  修改记录：
	 *  日期 ：2016年4月28日 下午4:47:56	修改人： nz 
	 *  描述 ：
	 *
	 */
	@Override
	public ClientEntity insert(ClientEntity entity) 
	{
		return null;
	}

	@Override
	public ClientEntity queryById(String clientId) 
	{
		String uri = HttpCommonUtil.CLIENTID_QUERY_URI + clientId;
		String url = String.format(HttpCommonUtil.FORMAT, HttpCommonUtil.getBaUrl(), uri); 
		HttpRequest httpRequest = new HttpRequest(HttpMethodType.GET, url, null, HttpCommonUtil.DEF_CONTENT_TYPE,
				HttpCommonUtil.DEF_ENCODING);
		try 
		{
			HttpResponse httpResponse = httpClientService.execute(httpRequest);
			if (HttpStatus.SC_OK != httpResponse.getStatus())
			{
				throw new BizException(String.valueOf(httpResponse.getStatus()), httpResponse.getResponseBody());
			}
			String respoData = httpResponse.getResponseBody();
			HttpResponseDto<ClientEntity> ret = HttpResMapBuilder.parseResponse(respoData,ClientEntity.class);
			if(ret.getList() == null)
			{
				log.error("第三方不存在" + ",clientId=" + clientId);
				throw new BizException(ErrorCode.SYSTEM_EXCEPTION.getCode());
			}
			return ret.getList().get(0);
		} catch (Exception e)
		{
			log.error("请求参数配置系统失败！", e);
			return null;
		}
	}

	@Override
	public int update(ClientEntity entity)
	{
		return 0;
	}

	@Override
	public void deleteById(String clientId)
	{
		
	}

	@Override
	public List<ClientEntity> queryAll()
	{
		return clientMapper.queryAll();
	}

}
