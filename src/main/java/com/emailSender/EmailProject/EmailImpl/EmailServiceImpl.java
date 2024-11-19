package com.emailSender.EmailProject.EmailImpl;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.emailSender.EmailProject.EmailDetails.EmailDetails;
import com.emailSender.EmailProject.EmailServerce.EmailServices;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailServices{
	
	@Autowired private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}") private String sender;
    
	@Override
	public String sendSimpleMail(EmailDetails details) {
		try {
			//Extracting the username from the json data mail address to greet the receipent....
			String name = details.getReceipent();
			String username = name.substring(0, name.indexOf("@"));
			
			SimpleMailMessage mail = new SimpleMailMessage();
			mail.setFrom(sender);
			mail.setTo(details.getReceipent());
			mail.setSubject(details.getSubject() + " " + username);
			mail.setText(details.getMsgBody());
			
			javaMailSender.send(mail);
			
			return "Mail send Successfully";
			}catch(Exception e) {
			return e.getMessage();
		}
	}

	@Override
	public String sendAttachmentMail(EmailDetails details) {
		
		MimeMessage msg = javaMailSender.createMimeMessage();
		MimeMessageHelper mailHelper;
		String name = details.getReceipent();
		String username = name.substring(0, name.indexOf("@"));
		
		
		 try {
			 mailHelper = new MimeMessageHelper(msg, true);
			mailHelper.setFrom(sender);
			mailHelper.setTo(details.getReceipent());
			mailHelper.setSubject(details.getSubject() + " " + username);
			mailHelper.setText(details.getMsgBody());
			
			FileSystemResource fl = new FileSystemResource(new File(details.getAttachment()));
			mailHelper.addAttachment(fl.getFilename(), fl);
			
			javaMailSender.send(msg);
			return "Mail attached Successfull";
			
		} catch (MessagingException e) {
			return e.getMessage();
		}
		 
	}
    
	
}
