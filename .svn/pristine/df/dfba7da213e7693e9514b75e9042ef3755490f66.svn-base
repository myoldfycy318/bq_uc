package com.qbao.store.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * AbstractRoutingDataSource整合多个数据源,每个{@link #getConnection()}方法
 * 从数据源集合中通过{@link #determineCurrentLookupKey()}方法获取获取一个数据源
 * 来获取数据库连接对象.
 *
 * @See org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource
 */
public class DynamicDataSourceSelector extends AbstractRoutingDataSource {

    private static final Logger log = LoggerFactory.getLogger(DynamicDataSourceSelector.class);

    private static final ThreadLocal<String> HOLDER = new ThreadLocal<>();


    /**
     * 主库数据源
     */
    private Map<String, DataSource> dataSources;

    @Override
    public void afterPropertiesSet() {
        Map<Object, Object> allDataSources = new HashMap<>();
        allDataSources.putAll(dataSources);
        log.info("开始向数据源路由器加载数据源......,数据源信息:{}");
        super.setTargetDataSources(allDataSources);
        super.afterPropertiesSet();
    }

    /**
     * 数据源切换
     */
    public void markDataSource(String dataSourceKey) throws Exception {
        String _dataSourceKey = getDataSource(dataSourceKey);
        if (_dataSourceKey == null)
            throw new Exception("数据源切换异常");
        HOLDER.set(_dataSourceKey);
    }


    /**
     * 移除当前线程数据源
     */
    public void remove() {
        HOLDER.remove();
    }

    /**
     * 当前线程是否已经绑定指定数据源
     *
     * @return true = 已经绑定, false = 未绑定
     */
    public boolean isBinded() {

        return HOLDER.get() != null;
    }

    /**
     * 获取当前线程数据源
     *
     * @return 返回当前线程数据源
     * @See org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource#determineCurrentLookupKey
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return HOLDER.get();
    }


    /**
     * 随机获取库数据源,当数据源增加时,可以修改该方法采用相应的策略来获取数据源
     *
     * @return 主库数据源
     */
    private String getDataSource(String dataSourceKey) {
        for (Map.Entry<String, DataSource> entry : dataSources.entrySet()) {
            if (dataSourceKey.equals(entry.getKey())) {
                return dataSourceKey;
            }
        }
        return null;
    }


    public Map<String, DataSource> getDataSources() {
        return dataSources;
    }

    public void setDataSources(Map<String, DataSource> dataSources) {
        this.dataSources = dataSources;
    }
}
