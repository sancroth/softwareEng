package hrm.model;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class MailGenerator extends Object
{
	String pass;
	String user;
	Manager manager;
	public MailGenerator(Manager newManager,String user,String pass){
		this.manager = newManager;
		this.pass=pass;
		this.user=user;
	}
	
	public Manager getManager(){
		return this.manager;
	}


public void autoMail()
   {    


	    try{

	        Properties props = new Properties();
	        props.put("mail.smtp.host", "smtp.gmail.com"); // for gmail use smtp.gmail.com
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.debug", "true"); 
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.port", "465");
	        props.put("mail.smtp.socketFactory.port", "465");
	        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	        props.put("mail.smtp.socketFactory.fallback", "false");

	        Session mailSession = Session.getInstance(props, new javax.mail.Authenticator() {

	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication("sancroth@gmail.com", "...");
	            }
	        });

	        mailSession.setDebug(true); // Enable the debug mode

	        Message msg = new MimeMessage( mailSession );

	        //--[ Set the FROM, TO, DATE and SUBJECT fields
	        msg.setFrom( new InternetAddress( "sancroth@gmail.com" ) );
	        msg.setRecipients( Message.RecipientType.TO,InternetAddress.parse("senountanikos@gmail.com") );
	        msg.setSentDate( new Date());
	        msg.setSubject( "USER INFO");

	        //--[ Create the body of the mail
	        msg.setText( "User:"+this.user+"Pass"+this.pass );

	        //--[ Ask the Transport class to send our mail message
	        Transport.send( msg );

	    }catch(Exception E){
	        System.out.println( "Oops something has gone pearshaped!");
	        System.out.println( E );
	    }
	}

   }
