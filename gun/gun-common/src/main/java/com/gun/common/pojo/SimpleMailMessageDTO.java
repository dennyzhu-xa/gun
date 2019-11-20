package com.gun.common.pojo;

import org.springframework.mail.SimpleMailMessage;

public class SimpleMailMessageDTO extends SimpleMailMessage {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8094350706351939815L;

	public SimpleMailMessageDTO() {
		super();
	}

	public SimpleMailMessageDTO(SimpleMailMessage original) {
		super(original);
	}

}
