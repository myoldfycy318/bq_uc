package com.bqiong.usercenter.util;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * 配置文件加载
 */
public class PropertiesUtil {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    private static Properties properties = new Properties();

    private List<String> filePathList;

    public void setFilePathList(List<String> filePathList) {
        this.filePathList = filePathList;
    }

    /**
     * 初始化方法,如果初始化不成功，环境无法启动
     *
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void init() {
        if (CollectionUtils.isEmpty(filePathList)) {
            log.info("filePathList not found");
            return;
        }
        InputStream in = null;
        for (String filePath : filePathList) {
            in = PropertiesUtil.class.getResourceAsStream(filePath);
            if (in == null) {
                log.info("配置文件:{}加载失败", filePath);
                continue;
            }
            try {
                if (!(in instanceof BufferedInputStream)) {
                    in = new BufferedInputStream(in);
                }
                properties.load(in);
            } catch (Exception e) {
                log.info("Error while processing properties file", e);
            } finally {
                IOUtils.closeQuietly(in);
            }
        }

    }


    /**
     * get a properties value
     *
     * @param key
     * @return
     */
    public String getString(String key) {
        return properties.getProperty(key);
    }

    /**
     * @param key
     * @param defaultValue
     * @return
     */
    public String getString(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    /**
     * get a properties int value
     *
     * @param key
     * @return
     */
    public int getInt(String key) {
        return Integer.parseInt(properties.getProperty(key));
    }

    public int getInt(String key, String defaultValue) {
        return Integer.parseInt(properties.getProperty(key, defaultValue));
    }

}
