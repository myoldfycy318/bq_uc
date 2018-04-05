package com.qbao.store.filter;

import com.alibaba.fastjson.JSONObject;
import com.qbao.store.entity.request.RequestData;
import com.qbao.store.util.UserCenterContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * **********************************************************
 *  内容摘要	：<p>
 *
 *  作者	：94841
 *  创建时间	：2016年4月26日 下午5:15:43 
 *  当前版本号：v1.0
 *  历史记录	:
 *  	日期	: 2016年8月26日 下午5:15:43 	修改人：niuzan
 *  	描述	:
 ***********************************************************
 */
public class RequestFilter extends HttpServlet implements Filter
{

	private static final long serialVersionUID = 8440219491928138602L;


	private static Logger logger = LoggerFactory.getLogger(RequestFilter.class);

	
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException,
			ServletException
	{
		try
		{
			RequestData requestData = new RequestData();
			UserCenterContext.setRequestData(requestData);
			UserCenterContext.buildRequestData((HttpServletRequest) servletRequest);
			HttpServletRequest request = (HttpServletRequest) servletRequest;
			logger.info("请求路径：{},请求参数:{}", request.getRequestURI(), JSONObject.toJSONString(request.getParameterMap()));
			chain.doFilter(servletRequest, servletResponse);
		} catch (Exception e)
		{
			logger.error("组装请求信息失败！", e);
		} 
	}


	@Override
	public void init(FilterConfig filterConfig) throws ServletException
	{
		
	}
}
