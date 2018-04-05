package com.qbao.store.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration({"classpath:config/applicationContext.xml"})
public class TestSuits 
{
	@Test
	public void testRedis()
	{
		Map<String, Map<String, String>> result = new HashMap<String, Map<String, String>>();
		Map<String, String> map = new HashMap<String, String>();
		result.put("data", map);
		String mapString = JSONObject.toJSONString(result);
		JSONObject json = JSONObject.parseObject(mapString);
		if(json.getJSONObject("data") != null)
		{
			if(json.getJSONObject("data").getBoolean("1"))
			{
				System.out.println(1);
			}
		}
		System.out.println(2);
	}
	
}
