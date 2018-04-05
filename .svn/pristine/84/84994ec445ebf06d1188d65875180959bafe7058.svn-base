package com.qbao.store.security;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;

import com.qbao.store.core.web.converter.Result;

/**
 * app后台自定义跳转模式
 * @author yewenhai
 *
 */
public class AppBaAuthenticationEntryPoint extends CasAuthenticationEntryPoint {

    private static final Logger LOG = LoggerFactory.getLogger(AppBaAuthenticationEntryPoint.class);

    @Override
    protected void preCommence(HttpServletRequest request, HttpServletResponse response) {
        if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) { // ajax方法返回错误码，页面做自动跳转
            Result result = new Result();
            result.setResponseCode(Result.RESPONSE_CODE_SESSION_TIMOUT);
            result.setData(this.createRedirectUrl(this.createServiceUrl(request, response)));
            JSONObject obj = new JSONObject(result);
            LOG.info("result: " + obj.toString());
            Writer writer = null;
            try {
                writer = response.getWriter();
                writer.write(obj.toString());
            } catch (IOException e) {
                LOG.error(ExceptionUtils.getFullStackTrace(e));
            } finally {
                if (null != writer) {
                    try {
                        writer.close();
                    } catch (IOException e) {
                        LOG.error(ExceptionUtils.getFullStackTrace(e));
                    }
                }
            }
        }

    }

}
