package com.qbao.store.filter;

import com.alibaba.fastjson.JSONObject;
import com.bqiong.usercenter.constants.BizType;
import com.bqiong.usercenter.constants.RequestDataHelper;
import com.bqiong.usercenter.httpresponse.BaseResponse;
import com.bqiong.usercenter.util.IPUtil;
import com.bqiong.usercenter.util.ThreadPoolUtil;
import com.qbao.store.async.Log2DBThread;
import com.qbao.store.entity.log.UserOperationLogEntity;
import com.qbao.store.util.SpringUtil;
import com.qbao.store.util.UserCenterContext;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * **********************************************************
 *  内容摘要	：<p>
 *
 *  作者	：94841
 *  创建时间	：2016年4月26日 下午5:15:43 
 *  当前版本号：v1.0
 *  历史记录	:
 *  	日期	: 2016年4月26日 下午5:15:43 	修改人：niuzan
 *  	描述	:
 ***********************************************************
 */
public class LogFilter extends HttpServlet implements Filter
{

	private static final long serialVersionUID = 8440219491928138602L;

	private static Logger accessLogger = LoggerFactory.getLogger("access_log");
	
	private static Logger loginHadoopLogger = LoggerFactory.getLogger("login_hadoop_log");
	
	private static Logger mobilecodeHadoopLogger = LoggerFactory.getLogger("mobilecode_hadoop_log");

	private static Logger logger = LoggerFactory.getLogger(LogFilter.class);

	private static String encryptParamNamesStr = "pwd,paypwd,password,paypassword,repwd,repaypwd,repassword,repaypassword,newpaypassword,renewpaypassword,paypasswordinput";

	private static Set<String> encryptParamNames = new HashSet<String>();
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException
	{
		logger.info("系统启动开始");
		if (encryptParamNames.size() == 0)
		{
			String[] strs = encryptParamNamesStr.split(",");
			Collections.addAll(encryptParamNames, strs);
		}
	}

	@Override
	public void destroy()
	{
		super.destroy();
		logger.info("系统shutdown,destroy");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException,
			ServletException
	{
		UserOperationLogEntity entity = new UserOperationLogEntity();
		Date begin = new Date();
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		String statusCode = "0";
		HttpSession session = request.getSession();
		String id = session.getId();
		try
		{
			UserCenterContext.setUserOperationLogEntity(entity);
			HttpServletResponse response = (HttpServletResponse) servletResponse;
			WrapperedResponse wrapperedResponse = new WrapperedResponse(response);
			chain.doFilter(servletRequest, wrapperedResponse);
			byte[] bytes = wrapperedResponse.getResponseData();
			String responseString = new String(bytes, "UTF-8");
			if(StringUtils.isNotBlank(responseString) && isJosnString(responseString))
			{
				BaseResponse baseResponse = JSONObject.toJavaObject(JSONObject.parseObject(responseString), BaseResponse.class);
				statusCode = baseResponse.getCode();
			}
			ServletOutputStream output = response.getOutputStream();
	        output.write(bytes);
	        output.flush();
		} catch (Exception e)
		{
			logger.error("access log filter throw exception:", e);
		} finally
		{
			String accessLoggerStr = getAccessLog(request, statusCode, begin, id);
			accessLogger.info(accessLoggerStr);
		}

	}

	private void asyncLog2DB(UserOperationLogEntity userOperationLogEntity)
	{
		if(StringUtils.isNotBlank(userOperationLogEntity.getUserId()) && StringUtils.isNotBlank(userOperationLogEntity.getBuId()))
		{
			Log2DBThread log2dbThread = SpringUtil.getBeanById("log2DBThread");
			log2dbThread.setEntity(userOperationLogEntity);
			ThreadPoolUtil.execute(log2dbThread);
		}
	}

	@SuppressWarnings("rawtypes")
	private static String getAccessLog(HttpServletRequest request, String stateCode, Date begin, String id)
	{
		StringBuilder sb = new StringBuilder();
		try
		{
			// user ip
			sb.append(IPUtil.getIpAddr(request)).append(" - - ");

			// request time
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss:SSS");

			String time = format.format(begin);
			sb.append("[").append(time).append(" +0800] ");

			// request method
			String method = request.getMethod();
			sb.append("\"").append(method).append(" ");

			// request url
			/*
			 * sb.append("http://"); sb.append(request.getHeader("Host"));
			 */
			sb.append(request.getRequestURI());

			// request params
			if ("GET".equals(method))
			{
				sb.append(request.getQueryString() == null ? "" : "?" + request.getQueryString()).append(" ");
			} else
			{
				sb.append("?");
				Enumeration enumeration = request.getParameterNames();

				while (enumeration.hasMoreElements())
				{
					String paramName = (String) enumeration.nextElement();
					String value = request.getParameter(paramName);
					if (encryptParamNames.contains(paramName.toLowerCase()))
					{
						sb.append(paramName).append("=").append("XXX").append("&");

					} else
					{
						try
						{
							value = URLEncoder.encode(value, "UTF-8");
						} catch (UnsupportedEncodingException e)
						{
							logger.error("", e);
						}
						sb.append(paramName).append("=").append(value).append("&");
					}
				}
				sb.deleteCharAt(sb.length() - 1).append(" ");
			}

			// protocol
			sb.append(request.getProtocol()).append("\" ");

			// state code
			sb.append(stateCode).append(" - ");

			// 响应时间
			sb.append(String.valueOf(System.currentTimeMillis() - begin.getTime())).append(" ");
			
			UserOperationLogEntity entity = UserCenterContext.getUserOperationLogEntity();
				
			sb.append("\"").append(entity == null? "" : entity.getUserId()).append("\" ");
			// session id
			sb.append("\"").append(id).append("\" ");

		} catch (Exception e)
		{
			logger.error("get access log string Exception:", e);
		}
		return sb.toString();
	}
	
	@SuppressWarnings("rawtypes")
	private static String getHadoopLog(HttpServletRequest request, String stateCode, Date begin, String id, BizType bizType)
	{
		StringBuilder sb = new StringBuilder();
		try
		{
			// request time
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			String time = format.format(begin);
			if(BizType.login == bizType)
			{
				sb.append(RequestDataHelper.LOGIN_TIME + "=").append(time).append("&");
			}
			if(BizType.getCode == bizType)
			{
				sb.append("getCodeTime=").append(time).append("&");
			}
						
			UserOperationLogEntity entity = UserCenterContext.getUserOperationLogEntity();
			
			// request params
			Enumeration enumeration = request.getParameterNames();
			sb.append(RequestDataHelper.RESPONSE_CODE + "=").append(stateCode).append("&");
			while (enumeration.hasMoreElements())
			{
				String paramName = (String) enumeration.nextElement();
				String value = request.getParameter(paramName);
				if (RequestDataHelper.getNeedLogParameterNames().contains(paramName))
				{
					try
					{
						if(value.indexOf("&") != -1)
						{
							value = URLEncoder.encode(value, "UTF-8");
						}
					} catch (UnsupportedEncodingException e)
					{
						logger.error("", e);
					}
					sb.append(paramName).append("=").append(value).append("&");
				}
			}
			sb.deleteCharAt(sb.length() - 1);
			if(entity != null)
			{
				sb.append("&" + RequestDataHelper.PASSPORT + "=" + (StringUtils.isBlank(entity.getMobile()) ? entity.getEmail() : entity.getMobile()));
				sb.append("&" + RequestDataHelper.USER_ID + "=" + entity.getUserId());
				sb.append("&" + RequestDataHelper.CLIENT_ID + "=" + entity.getClientId());
				sb.append("&" + RequestDataHelper.THIRD_ID + "=" + entity.getThirdId());
				sb.append("&" + RequestDataHelper.OPEN_ID + "=" + entity.getOpenId());
			}
			
			if(sb.charAt(sb.length() - 1) == '&')
			{
				sb.deleteCharAt(sb.length() - 1);
			}

		} catch (Exception e)
		{
			logger.error("get access log string Exception:", e);
		}
		return sb.toString();
	}

	private static final boolean isJosnString(String str)
	{
		try
		{
			JSONObject.toJavaObject(JSONObject.parseObject(str), BaseResponse.class);
			return true;
		}catch(Exception e)
		{
			return false;
		}
	}

}
