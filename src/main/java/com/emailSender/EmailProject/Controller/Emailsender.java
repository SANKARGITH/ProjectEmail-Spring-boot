package com.emailSender.EmailProject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emailSender.EmailProject.EmailDetails.EmailDetails;
import com.emailSender.EmailProject.EmailServerce.EmailServices;

@RestController
@RequestMapping("/api/email")
public class Emailsender {
	
	@Autowired EmailServices emailService;
	
	@PostMapping("/sendMail")
	public ResponseEntity<String> sendMail(@RequestBody EmailDetails details){
	  String status = emailService.sendSimpleMail(details);
	   if(status.startsWith("Mail")) {
	    return ResponseEntity.ok(status);
	   }else {
		   return ResponseEntity.status(404).body("Mail not send");
	   }
	}
	   
	   @PostMapping("/sendAttachment")
	   public ResponseEntity<String>  sendAttachment(@RequestBody EmailDetails details){
		   String status = emailService.sendAttachmentMail(details);
		   if(status.startsWith("Mail")) {
			   return ResponseEntity.ok(status);
		   }else {
			   return ResponseEntity.status(404).body("Mail not send");
		   }
	   
	}
}
