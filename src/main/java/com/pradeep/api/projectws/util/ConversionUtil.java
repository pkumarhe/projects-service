package com.pradeep.api.projectws.util;

import org.springframework.stereotype.Component;

import java.util.Random;


@Component
public class ConversionUtil {

	public static String flattenUserName(String primaryEmail) {
		return primaryEmail.replace(".", "").replace("@", "");
	}
	
	public static String getEmailDomain(String email) {
		return email.substring(email.indexOf('@')+1,email.length()).replace(".", "");
	}
	
	public static String encodeUrl() {        
       Random random=new Random();
       int randomNumber=random.nextInt(1000000);
       return String.valueOf(randomNumber);
    }
}
