<%@page import="com.system.cfg.SystemConfig"%>
<%@page import="java.io.IOException"%>
<%@page import="java.net.MalformedURLException"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.net.URLConnection"%>
<%@page import="java.net.URL"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String mapUrl=(String)SystemConfig.getConfig("mapUrl");
	InputStream is=null;
	try {
		URL url=new URL(mapUrl);
		URLConnection urlConnection=url.openConnection();
		is=urlConnection.getInputStream();
		byte[] buf=new byte[1024];
		int count=-1;
		while((count=is.read(buf))!=-1){
			out.print(new String(buf,"UTF-8"));
		}
	} catch (MalformedURLException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		if(is!=null){
			is.close();
		}
	}
%>