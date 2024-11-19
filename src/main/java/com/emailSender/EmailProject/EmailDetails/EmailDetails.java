package com.emailSender.EmailProject.EmailDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailDetails {
	
	private String receipent;
	private String subject;
	private String msgBody;
	private String attachment;

}
