package com.traydakalo.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtils {
    public static Properties getPropertiesByName(String propertiesFileName){
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String propertiesPath = rootPath + propertiesFileName;

        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(propertiesPath)){
            properties.load(fis);
        } catch (IOException e) {
            new FileNotFoundException().printStackTrace();
        }
        return properties;
    }
}
