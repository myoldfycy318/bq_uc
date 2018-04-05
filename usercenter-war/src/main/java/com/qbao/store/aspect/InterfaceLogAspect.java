package com.qbao.store.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Enumeration;

@Aspect
@Component("interfaceLogAspect")
@Order(4)//执行权重 最小最先执行
public class InterfaceLogAspect
{
	
	private final static Logger log = LoggerFactory.getLogger(InterfaceLogAspect.class);
	
	@Pointcut("execution(* com.qbao.store.controller..*.*(..)) && !within(com.qbao.store.controller.healthcheck.*)")
	private void requestLogPointCut(){}
	
	@Before(value = "requestLogPointCut()")
	public void doBefore(JoinPoint point)
	{
		log.info("\n");
		log.info("----------------------------------request start:");
		Object[] args = point.getArgs();
    	for (int i = 0; i < args.length; i++) 
    	{
			if (args[i] instanceof HttpServletRequest) 
			{
				log.info(buildLogString((HttpServletRequest)args[i]).toString());
			}
			else
			{
				continue;
			}
		}
	}
	
	@SuppressWarnings("rawtypes")
	private static StringBuilder buildLogString(HttpServletRequest request)
	{
		StringBuilder sb = new StringBuilder();
		// request method
		String method = request.getMethod();

		sb.append("请求接口为：" + request.getRequestURI() + ",请求参数打印：");

		if ("GET".equals(method))
		{
			sb.append(request.getQueryString() == null ? "" : request.getQueryString());
		}
		else
		{
			Enumeration enumeration = request.getParameterNames();
			while (enumeration.hasMoreElements())
			{
				String paramName = (String) enumeration.nextElement();
				String value = request.getParameter(paramName);
				try
				{
					value = URLEncoder.encode(value, "UTF-8");
				} catch (UnsupportedEncodingException e)
				{
					log.error(e.getMessage(), e);
				}
				sb.append(paramName).append("=").append(value).append("&");
			}
		
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb;
	}
}
