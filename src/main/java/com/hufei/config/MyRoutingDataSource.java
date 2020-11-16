package com.hufei.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.lang.Nullable;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @Author:hufei
 * @CreateTime:2020-11-16
 * @Description:获取数据源路由key
 */
public class MyRoutingDataSource extends AbstractRoutingDataSource {
    @Nullable
    @Override
    protected Object determineCurrentLookupKey() {
        return DBContextHolder.get();
    }
}
