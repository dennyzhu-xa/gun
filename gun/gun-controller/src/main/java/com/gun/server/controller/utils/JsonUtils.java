package com.gun.server.controller.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * 
 * Purpose:回傳JSON格式數據工具類
 * @since  JDK 1.7
 * @date   2017年2月4日
 */
public class JsonUtils {
	
	private static ObjectMapper mapper = new ObjectMapper();

	private static JsonFactory factory = mapper.getJsonFactory();
	
	public static void writeJSON(HttpServletResponse response, String json) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(json);
	}
	
	public static void writeJSON(HttpServletResponse response, Object obj) throws IOException {
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		factory = mapper.getJsonFactory();
		response.setContentType("text/html;charset=utf-8");
		JsonGenerator responseJsonGenerator = factory.createJsonGenerator(response.getOutputStream(), JsonEncoding.UTF8);
		responseJsonGenerator.writeObject(obj);
	}
	
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}
}
