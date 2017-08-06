package com.system.quartz;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.system.cfg.SystemConfig;

public class Job {
	private static final String basePath=(String)SystemConfig.getConfig("basePath");
	
	public void doJob(){
		//basePath="http://localhost:8888/xfun";
		
		try {
			URL url=new URL(basePath+"/autoRemind.jsp");
			URLConnection urlConnection=url.openConnection();
			InputStream is=urlConnection.getInputStream();
			if(is!=null){
				is.close();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
