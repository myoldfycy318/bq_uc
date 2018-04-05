package com.qbao.store.controller.refresh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.bqiong.usercenter.httpresponse.BaseResponse;
import com.qbao.store.controller.BaseController;
import com.qbao.store.util.InitConstants;

@Controller
public class RefreshController extends BaseController
{
	@Autowired
	private InitConstants initConstants;
	
	@ResponseBody
	@RequestMapping("/refreshAll")
	public String refresh()
	{
		initConstants.load();
		return JSONObject.toJSONString(BaseResponse.success());
	}

}
