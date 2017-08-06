package com.system.cfg;

import java.io.IOException;
import java.util.Properties;


public class SystemConfig {
	private static Properties config=new Properties();
	
	static{
		try {
			config.load(SystemConfig.class.getClassLoader().getResourceAsStream("com/system/cfg/config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Object getConfig(String key){
		return config.get(key);
	}
	
}
