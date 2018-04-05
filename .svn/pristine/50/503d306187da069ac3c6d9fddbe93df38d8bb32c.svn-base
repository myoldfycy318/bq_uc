package com.qbao.store.controller.healthcheck;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.qbao.store.controller.BaseController;

@Controller
@RequestMapping("/health")
public class HealthCheckController extends BaseController
{
	@RequestMapping("/check")
    @ResponseBody
    public String healthCheck()
    {
		Map<String, String> data = new HashMap<String, String>();
		data.put("result", "ok");
		return JSONObject.toJSONString(data);
    }
	
}
