package com.qbao.store.response;

import com.alibaba.fastjson.JSONObject;
import com.bqiong.usercenter.constants.ErrorCode;
import com.bqiong.usercenter.exception.BizException;
import com.bqiong.usercenter.httpresponse.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

public class CustomSimpleMappingExceptionResolver extends SimpleMappingExceptionResolver {
    private static final Logger log = LoggerFactory.getLogger(CustomSimpleMappingExceptionResolver.class);

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        log.error("CustomSimpleMappingExceptionResolver 非预期异常",ex);
        String exceptionMsg = JSONObject.toJSONString(BaseResponse.fail(ErrorCode.SYSTEM_EXCEPTION));
        if (ex instanceof BizException) {
            ErrorCode errorCode = ErrorCode.getFromKey(((BizException) ex).getErrorCode());
            if (errorCode != null) {
                exceptionMsg = JSONObject.toJSONString(BaseResponse.buildResponse(errorCode, null));
            }
        }
        try {
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(exceptionMsg.getBytes("utf-8"));
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            log.error("处理异常出错", e);
        }
        return new ModelAndView();
    }

    //把自定义的错误处理器的执行顺序配置为最高
    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }
}