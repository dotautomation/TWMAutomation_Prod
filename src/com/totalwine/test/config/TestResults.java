package com.totalwine.test.config;

import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import org.testng.annotations.Test;

public class TestResults
{
	@Test
	public static void EmailTestResults() throws InterruptedException {
	   final String username = "jira@totalwine.com";
	   final String password = "Gr@pes1";
	   
	   Properties props = new Properties();
	    props.put("mail.smtp.host", "smtp.totalwine.com");
	    props.put("mail.smtp.EnableSSL.enable","true");
	   
	    Session session = Session.getInstance(props,new javax.mail.Authenticator() {
	    	protected PasswordAuthentication getPasswordAuthentication() {
	    		return new PasswordAuthentication(username, password);
	    		}
            });
	    
      String recipient = "rsud@totalwine.com";
      String sender = "jira@totalwine.com";

      try{
         MimeMessage message = new MimeMessage(session);
         message.setFrom(new InternetAddress(sender));
         message.addRecipient(Message.RecipientType.TO,new InternetAddress(recipient));
         message.setSubject("Test Results from "+ConfigurationFunctions.now());
         BodyPart messageBodyPart = new MimeBodyPart();
         messageBodyPart.setText("Test Results are attached");
         Multipart multipart = new MimeMultipart();
         multipart.addBodyPart(messageBodyPart);
         
         Thread.sleep(10000); //Get the latest report
         
         messageBodyPart = new MimeBodyPart();
         String filename = "C:/totalwine/TWMAutomation/test-output/emailable-report.html";
         DataSource source = new FileDataSource(filename);
         messageBodyPart.setDataHandler(new DataHandler(source));
         messageBodyPart.setFileName(filename);
         multipart.addBodyPart(messageBodyPart);

         message.setContent(multipart );

         Transport.send(message);
         System.out.println("Sent message successfully....");
      } catch (MessagingException mex) {
         mex.printStackTrace();
      }
   }
}
