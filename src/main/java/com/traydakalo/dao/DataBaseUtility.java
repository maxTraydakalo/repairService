package com.traydakalo.dao;

import com.traydakalo.utils.PropertiesUtils;
import org.apache.commons.dbcp2.BasicDataSource;

import java.util.Properties;

public class DataBaseUtility {
    private static final String PROPERTIES_FILE = "dao.properties";
    private static final String PROPERTY_URL = "url";
    private static final String PROPERTY_DRIVER = "driver";
    private static final String PROPERTY_USERNAME = "username";
    private static final String PROPERTY_PASSWORD = "password";


    private static BasicDataSource dataSource;

    public static BasicDataSource getDataSource() {
        if (dataSource == null)
            synchronized (DataBaseUtility.class) {
                if (dataSource == null) {
                    {
                        Properties properties = PropertiesUtils.getPropertiesByName(PROPERTIES_FILE);

                        BasicDataSource ds = new BasicDataSource();
                        ds.setDriverClassName(properties.getProperty(PROPERTY_DRIVER));
                        ds.setUrl(properties.getProperty(PROPERTY_URL));
                        ds.setUsername(properties.getProperty(PROPERTY_USERNAME));
                        ds.setPassword(properties.getProperty(PROPERTY_PASSWORD));


                        ds.setMinIdle(5);
                        ds.setMaxIdle(10);
                        ds.setMaxOpenPreparedStatements(100);

                        dataSource = ds;
                    }
                }
            }
        return dataSource;
    }

}


