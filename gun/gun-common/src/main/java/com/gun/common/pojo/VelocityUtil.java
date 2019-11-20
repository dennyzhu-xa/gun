package com.gun.common.pojo;

import java.io.StringWriter;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.util.CollectionUtils;

public class VelocityUtil {

	private static Log log = LogFactory.getLog(VelocityUtil.class);
	
	public static String merge(String templateStr, Map<String, Object> map,
			String charset) {
		try {
			VelocityEngine ve = new VelocityEngine();
			Properties p = new Properties();
			p.put("input.encoding", charset);
			p.put("output.encoding", charset);
			p.put("resource.loader", "srl");
			p.put("srl.resource.loader.class",
					"com.cyber.lottery.common.pojo.StringResourceLoader");
			ve.init(p);

			VelocityContext context = new VelocityContext();
			if(!CollectionUtils.isEmpty(map)){
				for (String key : map.keySet()) {
					context.put(key, map.get(key));
				}
			}
			Template template = ve.getTemplate(templateStr, charset);
			StringWriter sw = new StringWriter();
			template.merge(context, sw);
			return sw.toString();
		} catch (Exception ex) {
			log.error(ex, ex);
			throw new IllegalStateException(ex);
		}

	}

}
