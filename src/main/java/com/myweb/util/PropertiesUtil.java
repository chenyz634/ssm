package com.myweb.util;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
	
	public static String url;
	public static String version;
	public static String forceUpdateVersion;
	public static String androidVersion;
	public static String iosVersion;
	public static String redisHost;
	public static String redisPort;
	public static String redisPwd;
	public static String androidDownUrl;
	public static String iosDownUrl;
	static {
		Properties pros = new Properties();
		InputStream in = PropertiesUtil.class.getResourceAsStream("/log4j.properties");
		InputStream in2 = PropertiesUtil.class.getResourceAsStream("/config.properties");
		try {
			pros.load(in);
			pros.load(in2);
			url = pros.getProperty("log4j.appender.File.File");
			version = pros.getProperty("redis.version");
			androidVersion = pros.getProperty("redis.version.android");
			forceUpdateVersion = pros.getProperty("redis.version.forceupdate");
			iosVersion = pros.getProperty("redis.version.ios");
			redisHost = pros.getProperty("redis.HOST");
			redisPort = pros.getProperty("redis.PORT");
			redisPwd = pros.getProperty("redis.PWD");
			androidDownUrl = pros.getProperty("downloadAn.url");
			iosDownUrl = pros.getProperty("downloadIOS.url");
		} catch (Exception e) {
			System.out.println("配置文件加载失败");
			e.printStackTrace();
		}
	}
	
	
}

