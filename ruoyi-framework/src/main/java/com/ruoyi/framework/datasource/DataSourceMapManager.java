package com.ruoyi.framework.datasource;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 */
public class DataSourceMapManager {
    // 使用线程安全的 ConcurrentHashMap 管理数据源
    private static final Map<Object, Object> dataSourceMap = new ConcurrentHashMap<>();

    // 私有构造函数，防止外部实例化
    private DataSourceMapManager() {}

    // 获取单例实例
    public static Map<Object, Object> getInstance() {
        return dataSourceMap;
    }

    // 添加数据源
    public static void addDataSource(String key, DataSource dataSource) {
        dataSourceMap.put(key, dataSource);
    }

    // 获取数据源
    public static DataSource getDataSource(String key) {
        return (DataSource) dataSourceMap.get(key);
    }
}
