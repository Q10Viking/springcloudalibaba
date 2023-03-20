package org.hzz.utils;

public class ConfigConstants {
    public static final String JDBCURL = "jdbc:mysql://192.168.135.130:3306/tl_mall_user?characterEncoding=utf-8&autoReconnect=true&serverTimezone=GMT%2B8";
    public static final String SCHEMA = "tl_mall_user";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "Root.123456";
    public static final String TABLENAME = "ums_member";

    public static final String ftlLocation;
    public static final String outputLocation;

    static{
        ftlLocation = Utils.getFtlFilePath();
        outputLocation = Utils.getOutputPath();
    }
}
