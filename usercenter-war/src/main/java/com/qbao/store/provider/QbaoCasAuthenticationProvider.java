package com.qbao.store.provider;

import com.alibaba.fastjson.JSONObject;
import com.qbao.store.controller.thirdpart.ThirdLoginByPasswordController;
import com.qbao.store.entity.request.RequestData;
import com.qbao.store.util.ApiConnector;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.jasig.cas.client.validation.Assertion;
import org.jasig.cas.client.validation.TicketValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.cas.authentication.CasAuthenticationToken;
import org.springframework.security.cas.authentication.NullStatelessTicketCache;
import org.springframework.security.cas.authentication.StatelessTicketCache;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * QbaoCasAuthenticationProvider
 *
 * @author Zhang ShanMin
 * @date 2017/6/21
 * @time 15:05
 */
public class QbaoCasAuthenticationProvider implements AuthenticationProvider, InitializingBean, MessageSourceAware {

    Logger logger = LoggerFactory.getLogger(QbaoCasAuthenticationProvider.class);
    protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
    private StatelessTicketCache statelessTicketCache = new NullStatelessTicketCache();
    private String key;
    private TicketValidator ticketValidator;
    private String qBaoUserDetailUrl;
    private String qBaoUserIdUrl;
    private String qBaoAppId;
    JSONObject jsonObject = null;
    JSONObject userInfo = null;
    String qbUserId = "";
    String response = null;

    @Autowired
    private ThirdLoginByPasswordController thirdLoginByPasswordController;

    //~ Methods ========================================================================================================

    public void afterPropertiesSet() throws Exception {
        Assert.notNull(this.ticketValidator, "A ticketValidator must be set");
        Assert.notNull(this.statelessTicketCache, "A statelessTicketCache must be set");
        Assert.hasText(this.key, "A Key is required so CasAuthenticationProvider can identify tokens it previously authenticated");
        Assert.notNull(this.messages, "A message source must be set");
        Assert.notNull(this.qBaoUserDetailUrl, "qBaoUserUrl is null");
    }


    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            if (!supports(authentication.getClass())) {
                return null;
            }
            if (authentication instanceof UsernamePasswordAuthenticationToken
                    && (!CasAuthenticationFilter.CAS_STATEFUL_IDENTIFIER.equals(authentication.getPrincipal().toString())
                    && !CasAuthenticationFilter.CAS_STATELESS_IDENTIFIER.equals(authentication.getPrincipal().toString()))) {
                return null;
            }
            Map<String, String> map = (Map<String, String>) authentication.getDetails();
            Assertion assertion = this.ticketValidator.validate(authentication.getCredentials().toString(), map.get("appClientService"));

            List<NameValuePair> paramsList = new ArrayList<NameValuePair>();
            paramsList.add(new BasicNameValuePair("userName", assertion.getPrincipal().getName()));
            paramsList.add(new BasicNameValuePair("appId", qBaoAppId));
            response = ApiConnector.post(qBaoUserIdUrl, paramsList);
            logger.info("根据userName获取钱宝用户userid,请求url:{},请求参数：{},响应报文：{}", qBaoUserIdUrl, com.alibaba.fastjson.JSONObject.toJSONString(paramsList), response);
            if (StringUtils.isBlank(response) || (jsonObject = com.alibaba.fastjson.JSONObject.parseObject(response)) == null
                    || 1 != jsonObject.getInteger("code") || StringUtils.isBlank(jsonObject.getString("data"))) {
                throw new UsernameNotFoundException("用户信息查询失败");
            }
            qbUserId = jsonObject.getString("data");
            paramsList.clear();
            paramsList.add(new BasicNameValuePair("userId", qbUserId));
            paramsList.add(new BasicNameValuePair("appId", qBaoAppId));
            response = ApiConnector.post(qBaoUserDetailUrl, paramsList);
            logger.info("根据userId获取钱宝用户信息,请求url:{},请求参数：{},响应报文：{}", qBaoUserDetailUrl, com.alibaba.fastjson.JSONObject.toJSONString(paramsList), response);
            if (StringUtils.isBlank(response) || (jsonObject = com.alibaba.fastjson.JSONObject.parseObject(response)) == null
                    || 1 != jsonObject.getInteger("code") || (userInfo = jsonObject.getJSONObject("data")) == null) {
                throw new UsernameNotFoundException("用户信息查询失败");
            }
            if (0 == userInfo.getInteger("enabled")) {
                throw new UsernameNotFoundException("您的账户已被冻结");
            }
            RequestData requestData = new RequestData();
            requestData.setQbaoUid(qbUserId);
            requestData.setUserName(userInfo.getString("username"));
            requestData.setBuId(map.get("buId"));
            requestData.setChannelId(map.get("channelCode"));
            requestData.setPlatformType(map.get("platformType"));
            String result = thirdLoginByPasswordController.login(requestData);
            return new UsernamePasswordAuthenticationToken(result, null);
        } catch (Exception e) {
            logger.error("QbaoCasAuthenticationProvider 获取钱宝用户信息异常", e);
        }
        return null;
    }


    protected String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public StatelessTicketCache getStatelessTicketCache() {
        return statelessTicketCache;
    }

    protected TicketValidator getTicketValidator() {
        return ticketValidator;
    }

    public void setMessageSource(final MessageSource messageSource) {
        this.messages = new MessageSourceAccessor(messageSource);
    }

    public void setStatelessTicketCache(final StatelessTicketCache statelessTicketCache) {
        this.statelessTicketCache = statelessTicketCache;
    }

    public void setqBaoUserIdUrl(String qBaoUserIdUrl) {
        this.qBaoUserIdUrl = qBaoUserIdUrl;
    }

    public void setqBaoAppId(String qBaoAppId) {
        this.qBaoAppId = qBaoAppId;
    }

    public void setqBaoUserDetailUrl(String qBaoUserDetailUrl) {
        this.qBaoUserDetailUrl = qBaoUserDetailUrl;
    }

    public void setTicketValidator(final TicketValidator ticketValidator) {
        this.ticketValidator = ticketValidator;
    }

    public boolean supports(final Class<? extends Object> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication)) ||
                (CasAuthenticationToken.class.isAssignableFrom(authentication)) ||
                (CasAssertionAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
