package com.emailSender.EmailProject.EmailServerce;


import com.emailSender.EmailProject.EmailDetails.EmailDetails;

public interface EmailServices {

	String sendSimpleMail(EmailDetails details);
	
	String sendAttachmentMail(EmailDetails details);
}
