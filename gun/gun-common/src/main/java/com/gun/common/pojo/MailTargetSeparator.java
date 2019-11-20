package com.gun.common.pojo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.util.StringUtils;

/**
 * Purpose: MailTargetSeparator
 * @author MooreChen
 * @since  JDK 1.5
 * @date   2014/9/24
 * @MaintenancePersonnel MooreChen
 */
public class MailTargetSeparator {

	private static final String Delimiter = ";";
	private static Log log = LogFactory.getLog(MailTargetSeparator.class);
	
	public static void split(SimpleMailMessage message) {
		String[] result = null;
		// To
		if(message == null){
			log.error("message is null!! ");
		}else{
			result = split(message.getTo());
			message.setTo(result);
			log.debug("Splitted 'To' length = " + result.length);
			
			// Cc
			result = split(message.getCc());
			message.setCc(result);
			log.debug("Splitted 'Cc' length = " + result.length);
			
			// Bcc
			result = split(message.getBcc());
			message.setBcc(result);
			log.debug("Splitted 'Bcc' length = " + result.length);
		}
	}
	
	public static String[] split(String[] emailArray) {
		if (emailArray == null) {
			return new String[0];
		}
		List<String> result = new ArrayList<String>();
		for (String element : emailArray) {
			String[] emails = element.split(Delimiter);
			for (String email : emails) {
				email = email.trim();
				if (StringUtils.hasText(email)) {
					result.add(email);
				}
			}
		}
		return result.toArray(new String[0]);
	}

}
