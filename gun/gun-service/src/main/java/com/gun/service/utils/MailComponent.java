package com.gun.service.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.gun.common.exception.ServiceException;
import com.gun.common.pojo.MailSender;
import com.gun.common.pojo.MailTargetSeparator;
import com.gun.common.pojo.SimpleMailMessageDTO;
import com.gun.common.pojo.TemplateMailMessageDTO;
import com.gun.common.pojo.VelocityUtil;
import com.gun.common.system.config.WfSystemConfigManager;
import com.gun.common.utils.EncodeDecodeUtils;
import com.gun.common.utils.LotteryConstants;
import com.gun.common.utils.StringUtils;

/**
 * Purpose: MailComponent
 * @author Felixli
 * @since  JDK 1.7
 * @date   2017/4/5
 * @MaintenancePersonnel Felixli
 */
@Service
public class MailComponent {

	public static final String MAIL_TEMPLATE_BASE_PATH = "/com/cyber/lottery/common/mail/template/";
	
	/** The ssl protocol: 'smtps' */
	public static final String SSL_PROTOCOL = "smtps";
	
	private static final Log log = LogFactory.getLog(MailComponent.class);
	
	@Autowired(required = true)
	private MailSender mailSender;
	
	/**
	 * Constructor:
	 */
	public MailComponent() {
		
	}

	public ApplicationContext ctx = null;  

  /**
   * Purpose:發送email
   * @param message：mail訊息DTO
   * @throws Exception：錯誤發生時拋出Exception
   */
  public void mail(final TemplateMailMessageDTO message) throws Exception {
    if (mailSender == null) {
      log.warn("mailSender is not set. Switch to mock operation only.");
      return;
    }
    
    String host = WfSystemConfigManager.getProperty(LotteryConstants.SYSTEM_CONFIG_MAIL, LotteryConstants.SYSTEM_CONFIG_MAIL_HOST);
	String user = WfSystemConfigManager.getProperty(LotteryConstants.SYSTEM_CONFIG_MAIL, LotteryConstants.SYSTEM_CONFIG_MAIL_ADMINID);
    String pwd = EncodeDecodeUtils.decryptDes(WfSystemConfigManager.getProperty(LotteryConstants.SYSTEM_CONFIG_MAIL, LotteryConstants.SYSTEM_CONFIG_MAIL_ADMINPASSWORD), LotteryConstants.LOTTERY);
    String hostIsSSL = WfSystemConfigManager.getProperty(LotteryConstants.SYSTEM_CONFIG_MAIL, LotteryConstants.SYSTEM_CONFIG_MAIL_HOST_IS_SSL);
    
    if (LotteryConstants.TRUE_VALUE.equals(hostIsSSL)) {
    	mailSender.setProtocol(SSL_PROTOCOL);
    }
    
    mailSender.setHost(host);
    log.debug(" mailSender.setHost !! " + mailSender.getHost() );
    mailSender.setUsername(user);
    log.debug(" mailSender.setUsername !! " + mailSender.getUsername() );
    mailSender.setPassword(pwd);
    log.debug(" mailSender.setPassword !! " + mailSender.getPassword() );
    //組裝mail地址
    MailTargetSeparator.split(message);
    MimeMessagePreparator preparator = new MimeMessagePreparator() {
      public void prepare(MimeMessage mimeMessage)
          throws MessagingException {
        // from
        String from = message.getFrom();
        mimeMessage.setFrom(new InternetAddress(from));
        log.debug("from = " + from);
        // to
        Address[] to = toAddresses(message.getTo());
        mimeMessage.setRecipients(Message.RecipientType.TO, to);
        log.debug("to = " + genStringText(message.getTo()));
        // CC
        Address[] cc = toAddresses(message.getCc());
        if (cc != null) {
          mimeMessage.setRecipients(Message.RecipientType.CC, cc);
          log.debug("cc = " + genStringText(message.getCc()));
        }
        // BCC
        Address[] bcc = toAddresses(message.getBcc());
        if (bcc != null) {
          mimeMessage.setRecipients(Message.RecipientType.BCC, bcc);
          log.debug("bcc = " + genStringText(message.getBcc()));
        }
        // subject
        String subject = VelocityUtil.merge(
            findTemplate(message.getSubjectTemplate(),
                message.getCharset()),
            message.getSubjectVariables(), message.getCharset());
        mimeMessage.setSubject(subject, message.getCharset());
        log.debug("subject = " + subject);
        // context (text)
        String text = VelocityUtil.merge(
            findTemplate(message.getTextTemplate(),
                message.getCharset()),
            message.getTextVariables(), message.getCharset());
        Multipart multipart = new MimeMultipart();
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(text,
            "text/html;charset=" + message.getCharset());
        multipart.addBodyPart(messageBodyPart);
        mimeMessage.setContent(multipart);
        log.debug("text = " + text);
        // attachments
        String[] attachments = message.getAttachments();
        if (attachments != null) {
          for (String attachment : attachments) {
            log.debug("attachment - " + attachment);
            File file = new File(attachment);
            messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(attachment);
            messageBodyPart.setDataHandler(new DataHandler(source));
            try {
              messageBodyPart.setFileName(MimeUtility.encodeText(
                  file.getName(), message.getCharset(), "B"));
            } catch (UnsupportedEncodingException ex) {
              log.warn(ex.getMessage());
              messageBodyPart.setFileName(file.getName());
            }
            log.debug("filename - " + file.getName());
            multipart.addBodyPart(messageBodyPart);
          }
        }
      }
    };
    mailSender.send(preparator);
  }
		
	  /**
	   * Purpose:读取mail内容
	   * @param key:模板地址
	   * @param charset:编码方式
	   * @return String
	   */
	  private String findTemplate(String key, String charset) {
	    try {
	      // look up classpath
	      InputStream inStream = this.getClass().getResourceAsStream(key);
	      if (inStream == null) {
	        throw new IllegalStateException("resouce can not be found - "
	            + key);
	      }
	      ByteArrayOutputStream outStream = new ByteArrayOutputStream();
	      byte[] buffer = new byte[1024];
	      int length = 0;
	      while ((length = inStream.read(buffer)) > 0) {
	        outStream.write(buffer, 0, length);
	      }
	      return new String(outStream.toByteArray(), charset);
	    } catch (Exception ex) {
	      log.error(ex, ex);
	      throw new IllegalStateException(ex);
	    }
	  }
	
	  /**
	   * Purpose:发送mail
	   * @author candicechen
	   * @param sender:发件人地址
	   * @param receiver:收件地址
	   * @param subjectTemplate:mial主旨
	   * @param textTemplate:mail内容
	   * @param variables:mail变量集合
	   * @throws ServiceException:出错时返回ServiceException
	   * @return void
	   */
	  public void mailTo(String sender, String receiver, String subjectTemplate, String textTemplate, Map<String, Object> variables) throws ServiceException {
	    try {
	      if(!StringUtils.hasText(sender)){
	        log.debug("MailComponent.mailTo from:Because LogonUser set FromMailAddress is Null,So,OS get System.config default fromMailAddress");
	        sender = WfSystemConfigManager.getProperty(LotteryConstants.SYSTEM_CONFIG_MAIL, LotteryConstants.SYSTEM_CONFIG_MAIL_FROMMAIL);
	        log.debug("MailComponent.mailTo OS default fromMailAddress:"+sender);
	      }
	      log.debug("MailComponent.mailTo from:"+sender);
	      log.debug("MailComponent.mailTo to:"+receiver);      
	      log.debug("MailComponent.mailTo subjectTemplate:"+subjectTemplate);
	      log.debug("MailComponent.mailTo textTemplate:"+textTemplate);
	      TemplateMailMessageDTO message = new TemplateMailMessageDTO();
	      message.setFrom(sender);
	      message.setTo(receiver);
	      message.setSubjectTemplate(subjectTemplate);
	      message.setTextTemplate(textTemplate);
	      message.setSubjectVariables(variables);
	      message.setTextVariables(variables);
	      this.mail(message);    
	    } 
	    catch(Throwable e){
	      log.debug("MailComponent prepareMailContent failed:"+e, e);
	      throw new ServiceException(e);
	    }
	  }
	
	public void mail(SimpleMailMessageDTO content) throws Exception {
		if (mailSender == null) {
			mailMock(content);
			return;
		}

		// split emails
		MailTargetSeparator.split(content);

		// Create a thread safe "sandbox" of the message
		SimpleMailMessage msg = new SimpleMailMessage(content);
		try {
			mailSender.send(msg);
			log.debug("mail sent");
			log(content);
		} catch (MailException ex) {
			log.error(ex, ex);
		}
	}
	
  private Address[] toAddresses(String[] array) throws AddressException {
		if (array == null || array.length == 0) {
			return null;
		}
		Address[] addresses = new Address[array.length];
		for (int i = 0; i < array.length; i++) {
			addresses[i] = new InternetAddress(array[i]);
		}
		return addresses;
	}
	
	private String genStringText(String[] messages) {
		StringBuffer buffer = new StringBuffer();
		if (messages == null) {
			buffer.append("(empty)");
		} else {
			for (String message : messages) {
				buffer.append(message);
				buffer.append(",");
			}
		}
		return buffer.toString();
	}
	
	/**
	 * Purpose:記錄log
	 * @param content
	 * @throws Exception
	 * @return void
	 */
	private void mailMock(SimpleMailMessageDTO content) throws Exception {
		log.warn("mailSender is not set. Switch to mock operation only.");
		log(content);
	}
	
	private void log(SimpleMailMessageDTO message) throws Exception {
		log.debug("from = " + message.getFrom());
		StringBuffer buffer = new StringBuffer();
		for (String to : message.getTo()) {
			buffer.append(to + ",");
		}
		log.debug("to[] = " + buffer.toString());
		log.debug("subject = " + message.getSubject());
		log.debug("text = " + message.getText());
	}
	
}
