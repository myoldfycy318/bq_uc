package com.qbao.store.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.bqiong.usercenter.constants.BUEnum;
import com.bqiong.usercenter.constants.ErrorCode;
import com.bqiong.usercenter.httpresponse.BaseResponse;
import com.bqiong.usercenter.util.aes.AESUtils;
import com.qbao.store.huanxin.model.TalkGroup;
import com.qbao.store.huanxin.model.TalkGroupFriend;
import com.qbao.store.huanxin.model.TalkGroupMember;
import com.qbao.store.huanxin.model.TalkNode;
import com.qbao.store.huanxin.service.impl.TalkDataServiceImpl;
import com.qbao.store.huanxin.tool.Global;
import com.qbao.store.huanxin.tool.JsonTool;
import com.qbao.store.util.DESUtil;

@Controller
@RequestMapping("/huanxin")
public class HuanXinController extends BaseController {

	TalkDataServiceImpl service = TalkDataServiceImpl.getInstance();
	
	public static final String HX_PASSWORD="dome666@1qaz@WSX";

	@RequestMapping("/login")
	@ResponseBody
	public String login(String username, String password, HttpServletRequest request) {
		try {
			TalkNode tn = service.login(username, password);
			return JsonTool.write(tn);
		} catch (Exception e) {
			log.info("登录环信异常,用户环信帐号:"+username, e);
		}
		return null;
	}
	
	
	@RequestMapping("/logout")
	@ResponseBody
	public String logout(String username, HttpServletRequest request) {
		try {
			TalkNode tn = service.logout(username);
			return JsonTool.write(tn);
		} catch (Exception e) {
			log.info("登出环信异常,用户环信帐号:"+username, e);
		}
		return null;
	}
	
	
	@RequestMapping("/register")
	@ResponseBody
	public String register(String bqUserId,String nickname, HttpServletRequest request) {
		try {
			String password=DESUtil.enCrypto(bqUserId, HX_PASSWORD);
			TalkNode tn = service.userSave(bqUserId, password, nickname);
			log.info("-----------------------/huanxin/register调试信息nickname:{},tn:{}",nickname,JSONObject.toJSONString(tn));
			if(tn.getEntities()!=null){
				return JSONObject.toJSONString(BaseResponse.success());
			}
//			return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.SYSTEM_EXCEPTION));
		} catch (Exception e) {
			log.info("注册环信异常,注册的用户环信帐号:"+bqUserId, e);
		}
		return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.SYSTEM_EXCEPTION));
	}
	
	@RequestMapping("/groupSave")
	@ResponseBody
	public String groupSave(String owner,String groupName,String desc, HttpServletRequest request) {
		try {
			TalkNode tn = service.groupSave(owner, groupName, desc, 500, null);
			TalkGroup group=tn.getData_group();
			if(group!=null){
				return JSONObject.toJSONString(BaseResponse.success(group.getGroupid()));
			}
			return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.SYSTEM_EXCEPTION));
		} catch (Exception e) {
			log.info("创建环信群组异常,创建用户环信帐号:"+owner, e);
		}
		return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.SYSTEM_EXCEPTION));
	}
	

	@RequestMapping("/groupFriendSave")
	@ResponseBody
	public String groupFriendSave(String groupId,String bqUserId, HttpServletRequest request) {
		try {
			TalkNode tn = service.groupFriendSave(groupId, bqUserId);
			TalkGroupFriend tgf=tn.getData_group_friend();
			if(tgf!=null){
				if(tgf.isResult()){
					return	JSONObject.toJSONString(BaseResponse.success());
				}
			}
			return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.SYSTEM_EXCEPTION));
		} catch (Exception e) {
			log.info("加入环信群组异常,加入的群组ID为"+groupId+",请求加入的用户环信帐号:"+bqUserId, e);
		}
		return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.SYSTEM_EXCEPTION));
	}
	
	
	@RequestMapping("/getHxPassword")
	@ResponseBody
	public String getHxPassword(String bqUserId, HttpServletRequest request) {
		String password="";
		try {
			password = DESUtil.enCrypto(bqUserId, HX_PASSWORD);
		} catch (Exception e) {
			log.info("获取密码发生异常,用户ID为:"+bqUserId, e);
		}
		return JSONObject.toJSONString(BaseResponse.success(password));
	}
	
	
	@RequestMapping("/groupModify")
	@ResponseBody
	public String groupModify(String groupId,String groupName,String desc, HttpServletRequest request) {
		try {
			log.info("------------- groupName:"+groupName+",desc:"+desc);
			TalkNode tn = service.groupModify(groupId, groupName, desc, 500);
			TalkGroupMember  tgm=tn.getData_group_update();
			if(tgm!=null){
				if(tgm.getGroupname()&tgm.getDescription()){
					return	JSONObject.toJSONString(BaseResponse.success());
				}
			}
			return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.SYSTEM_EXCEPTION));
		} catch (Exception e) {
			log.info("修改环信群组信息异常,修改的群组ID为"+groupId, e);
		}
		return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.SYSTEM_EXCEPTION));
	}
	
	
	@RequestMapping("/groupFriendDrop")
	@ResponseBody
	public String groupFriendDrop(String groupId,String bqUserId, HttpServletRequest request) {
		try {
			TalkNode tn = service.groupFriendDrop(groupId, bqUserId);
			TalkGroupFriend tgf=tn.getData_group_friend();
			if(tgf!=null){
				if(tgf.isResult()){
					return	JSONObject.toJSONString(BaseResponse.success());
				}
			}
			return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.SYSTEM_EXCEPTION));
		} catch (Exception e) {
			log.info("退出环信群组异常,退出的群组ID为"+groupId+",请求退出的用户环信帐号:"+bqUserId, e);
		}
		return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.SYSTEM_EXCEPTION));
	}

	
	@RequestMapping("/groupDrop")
	@ResponseBody
	public String groupDrop(String groupId, HttpServletRequest request) {
		try {
			TalkNode tn = service.groupDrop(groupId);
			TalkGroup group=tn.getData_group();
			if(group!=null&&group.getSuccess()){
				return JSONObject.toJSONString(BaseResponse.success());
			}
			return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.SYSTEM_EXCEPTION));
		} catch (Exception e) {
			log.info("解散环信群组异常,群组ID:"+groupId, e);
		}
		return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.SYSTEM_EXCEPTION));
	}
	
	@RequestMapping("/getKey")
	@ResponseBody
	public String getKey(String buId, HttpServletRequest request) {
		try 
		{
			BUEnum buEnum = BUEnum.getFromKey(buId);
			if(BUEnum.DOME_APP_STORE != buEnum)
			{
				return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.SYSTEM_EXCEPTION));
			}
			Map<String, String> map = new HashMap<String, String>();
			String encAppKey = AESUtils.encrypt(Global.getAppKey());
			String encAppClientId = AESUtils.encrypt(Global.getAppClientId());
			String encAppClientSecret = AESUtils.encrypt(Global.getAppClientSecret());
			map.put(Global.APP_KEY, encAppKey);
			map.put(Global.APP_CLIENT_ID, encAppClientId);
			map.put(Global.APP_CLIENT_SECRET, encAppClientSecret);
			return JSONObject.toJSONString(BaseResponse.success(map));
		} catch (Exception e) {
			log.info("get huanxin secretkey failed!" +  "buId =" + buId, e);
			return handleException(e.getMessage(), e);
		}
	}
	
}
