package com.qbao.store.controller.web;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.qbao.store.base.BaseTest;
import com.qbao.store.base.TestUtil;

public class TestAppExtController extends BaseTest {

    /**
     * 分页列表查询
     * @throws Exception
     */

    public void queryList() throws Exception {
        MockHttpServletRequestBuilder request = get(this.urlPrefix() + "queryList");
        request.param("type", "1"); // 0 置顶、1 精品
        request.param("pageno", "1"); // 页码
        this.mockMvc.perform(request).andExpect(status().isOk()).andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.responseCode", is(1000))).andExpect(jsonPath("$.data.pageNo", is(1)))
                .andExpect(jsonPath("$.data.pageSize", is(10)));
    }

    /**
     * 保存appExt：有效的数据
     * @throws Exception
     */
    @Test
    public void saveAppExt_success() throws Exception {
        MockHttpServletRequestBuilder request = get(this.urlPrefix() + "saveAppExt");
        request.param("appid", "147"); // 游戏ID
        request.param("type", "1");// 0 置顶、1 精品
        request.param("rank", "0"); // 排序
        this.mockMvc.perform(request).andExpect(status().isOk()).andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.responseCode", is(1000)));
    }

    /**
     * 保存appExt：无效的appId
     * @throws Exception
     */
    public void saveAppExt_invalidAppId() throws Exception {
        MockHttpServletRequestBuilder request = get(this.urlPrefix() + "saveAppExt");
        request.param("appid", "99999"); // 游戏ID
        request.param("type", "1");// 0 置顶、1 精品
        request.param("rank", "0"); // 排序
        this.mockMvc.perform(request).andExpect(status().isOk()).andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.responseCode", is(1005)));
    }

    /**
     * 保存appExt：无效的类型
     * @throws Exception
     */
    public void saveAppExt_invalidType() throws Exception {
        MockHttpServletRequestBuilder request = get(this.urlPrefix() + "saveAppExt");
        request.param("appid", "147"); // 游戏ID
        request.param("type", "5");// 0 置顶、1 精品
        request.param("rank", "0"); // 排序
        this.mockMvc.perform(request).andExpect(status().isOk()).andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.responseCode", is(1005)));
    }

    @Override
    protected String urlPrefix() {
        return "/web/appext/";
    }
}
