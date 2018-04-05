package com.qbao.store.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;


/**
 * http工具类
 * @author yewenhai
 *
 */
public final class HttpUtil {

    /**
     * 发送Http请求
     * @param url 请求url
     * @param method post or get
     * @param data 提交的数据
     * @return
     */
    public static String httpRequest(String requestUrl, String method, String data) {
        StringBuffer buffer = new StringBuffer();
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            conn.setRequestMethod(method);
            if (StringUtils.isNotEmpty(data)) {
                OutputStream outputStream = conn.getOutputStream();
                outputStream.write(data.getBytes("UTF-8"));
                outputStream.close();
            }
            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }

            // 释放资源
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            conn.disconnect();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return buffer.toString();
    }



    /**
     * 发送Http请求
     * @param url 请求url
     * @param method post or get
     * @param data 提交的数据
     * @return
     */
    public static String httpRequestByHeader(String requestUrl, String method, Map<String, String> headerParam) {
        StringBuffer buffer = new StringBuffer();
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(2000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            conn.setRequestMethod(method);
            if (null != headerParam && 0 != headerParam.size()) { // 设置httpheader
                Set<Map.Entry<String, String>> entries = headerParam.entrySet();
                for (Map.Entry<String, String> entry : entries) {
                    conn.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }
            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }

            // 释放资源
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            conn.disconnect();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return buffer.toString();
    }

}
