package com.qbao.store.controller.thirdpart.thirdOauth;

import com.alibaba.fastjson.JSONObject;
import com.bqiong.usercenter.util.PropertiesUtil;
import com.qbao.store.controller.BaseController;
import com.qbao.store.util.ApiConnector;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 乐视游戏h5登录
 * Created by ym on 2017/11/20.
 */
@Controller
@RequestMapping("/thirdOauthLogin/letv")
public class LetvController {
    protected final static Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    private PropertiesUtil propertiesUtil;

    @RequestMapping("/getLetvInfoByToken")
    public void getLetvInfoByToken(HttpServletRequest request, HttpServletResponse response) {
        String[] strArr = request.getParameter("buId").split("-");
        String url = propertiesUtil.getString("letv.letvInfoUrl").trim() + "?access=" + strArr[3];
        String userStr = ApiConnector.get(url, null);

        logger.info("获取乐视游戏的URL:{},用户信息为:{}", url, userStr);
        StringBuilder sb = new StringBuilder();
        try {
            if (StringUtils.isNotBlank(JSONObject.parseObject(userStr).toString())
                    && StringUtils.isNotBlank(JSONObject.parseObject(userStr).getString("status"))
                    && JSONObject.parseObject(userStr).getString("status").equals("0")) {
                String data = JSONObject.parseObject(userStr).getString("data");
                sb.append("/thirdOauthLogin/thirdLoginLetv?");
                sb.append("openId").append("=").append(JSONObject.parseObject(data).getString("uid")).append("&");
                sb.append("userName").append("=").append(JSONObject.parseObject(data).getString("uname")).append("&");
                sb.append("avatarUrl").append("=").append(JSONObject.parseObject(data).getString("picture")).append("&");
                sb.append("gender").append("=").append("N").append("&");
//                sb.append("thirdId").append("=").append("8").append("&");
//                sb.append("buId").append("=").append(strArr[0]).append("&");
                sb.append("channelId").append("=").append(strArr[1]).append("&");
                sb.append("state").append("=").append(request.getParameter("buId"));
                logger.info("url:{}", sb.toString());
                request.getParameterMap().put("buId", strArr[0]);
                request.getRequestDispatcher(sb.toString()).forward(request, response);
            } else {
                logger.error("获取乐视游戏用户信息失败,code:{},message:{}", JSONObject.parseObject(userStr).getString("status"), JSONObject.parseObject(userStr).getString("message"));
            }
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
