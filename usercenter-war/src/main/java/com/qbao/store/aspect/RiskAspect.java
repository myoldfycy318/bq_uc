package com.qbao.store.aspect;

import com.alibaba.fastjson.JSONObject;
import com.bqiong.usercenter.constants.ErrorCode;
import com.bqiong.usercenter.exception.BizException;
import com.bqiong.usercenter.httpresponse.BaseResponse;
import com.qbao.store.controller.BaseController;
import com.qbao.store.service.risk.RiskService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component("riskAspect")
@Order(3)//执行权重 最小最先执行
public class RiskAspect extends BaseController {

    private final static Logger log = LoggerFactory.getLogger(RiskAspect.class);

    @Autowired
    private RiskService riskService;

    @Around("@annotation(com.bqiong.usercenter.annotation.Risk)")
    public Object doAround(ProceedingJoinPoint point) {
        try {
            riskService.validateRisk();
            Object obj = point.proceed();
            log.info("begin resolve risk bussness！");
            BaseResponse baseResponse = JSONObject.toJavaObject(JSONObject.parseObject((String) obj), BaseResponse.class);
            ErrorCode errorCode = ErrorCode.getFromKey(baseResponse.getCode());
            if (errorCode == ErrorCode.SYSTEM_EXCEPTION) {
                return JSONObject.toJSONString(baseResponse);
            }
            Map<String, String> map = riskService.risk(errorCode);
            Map<String, Object> data = new HashMap<String, Object>();
            if (baseResponse.getData() != null) {
                data = (Map<String, Object>) baseResponse.getData();
                if (!CollectionUtils.isEmpty(map)) {
                    data.putAll(map);
                }
            } else {
                if (!CollectionUtils.isEmpty(map)) {
                    data.putAll(map);
                }
            }
            log.info("end of resolve risk bussness！risk result is=" + JSONObject.toJSONString(map));
            return JSONObject.toJSONString(BaseResponse.buildResponse(errorCode, data));
        } catch (Throwable e) {
            return doAfterThrowing(e);
        }
    }

    //当校验风控时就出现异常时会抛到这里来，这里做处理，其他异常都会抛到basecontroller里面处理
    private String doAfterThrowing(Throwable e) {
        log.error("风控异常", e);
        if (e instanceof BizException) {
            ErrorCode errorCode = ErrorCode.getFromKey(((BizException) e).getErrorCode());
            Map<String, String> map = riskService.risk(errorCode);
            String response = JSONObject.toJSONString(BaseResponse.buildResponse(errorCode, map));
            log.info("接口返回数据：" + response);
            return response;
        }

        return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.SYSTEM_EXCEPTION));
    }

}
