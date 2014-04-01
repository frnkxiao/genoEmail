package academyCustom;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class GenoEmail {
	
	private int smtpSetting = 0;
	
	private String ccEmail;
	private String bcEmail;
	
	private final String username;
	private final String password;
	
	private Properties props;
	private Session session;
	
	
	private String user = "Geno User";
	private String subject = "Genotyping result";
	private String sender = "Geno core";
	
	
	public GenoEmail(String username, String password) {
		
		this.username = username;
		this.password = password;
		
		setAppUser();	
	}
	
	
	private void setAppUser() {
		
		// for gmail
		props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.connectiontimeout", "10000");
		props.put("mail.smtp.timeout", "10000");
		props.put("mail.smtp.writetimeout", "20000");
		
		smtpSetup();
				
		props.put("mail.debug", "true"); // for debug 
		
		
		session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});	
		
		session.setDebug(true);
		
		try  {			
			session.setDebugOut(new PrintStream(new File("./email-debug-info.txt")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	private void smtpSetup() {
		if (smtpSetting == 0) {
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.port", "587");
			
		} else if(smtpSetting == 1) {
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");			
			props.put("mail.smtp.port", "465");
			
		} else if (smtpSetting == 2) {
			props.put("mail.smtp.socketFactory.port", "25");
			props.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");			
			props.put("mail.smtp.port", "25");
			
		}
		

	}


	public void setCcEmail(String ccEmail) {
		this.ccEmail = ccEmail;
	}
	
	public void setBcEmail(String bcEmail) {
		this.bcEmail = bcEmail;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public void setSender(String sender) {
		this.sender =sender;
	}
	
	public boolean send(String email, String[] attachments) {
		
		try {
			Message message = new MimeMessage(session);
			
			//Send to
			message.setFrom(new InternetAddress(username));
			
			//Send from
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
			
			if (ccEmail != null)
				message.addRecipient(Message.RecipientType.CC, new InternetAddress(ccEmail));
			
			if (bcEmail != null)			
				message.addRecipient(Message.RecipientType.BCC, new InternetAddress(bcEmail));
			
			//Subject
			message.setSubject(subject);
			
			//create message body part and fill with message
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText("Dear " + user + ":\n\n" +
									"Your genotyping results are ready.  Please find them in attachment. \n" + 
									"Thanks for using our service! \n\n" +
									"Best regards,\n" + 
									sender);
			
			//create a multipart message
			Multipart multipart = new MimeMultipart();
			
			//add text message part
			multipart.addBodyPart(messageBodyPart);
			
			//add attachments
			for (String attachment : attachments) {
				String [] fnames = attachment.split("\\" + File.separator);
				
				messageBodyPart = new MimeBodyPart();
				DataSource datasource = new FileDataSource(attachment);
				messageBodyPart.setDataHandler(new DataHandler(datasource));				
				messageBodyPart.setFileName(fnames[fnames.length - 1]);
				
				multipart.addBodyPart(messageBodyPart);
			}
			
			//The complete message
			message.setContent(multipart);
			
			//send message
			Transport.send(message);
						
			
		} catch(MessagingException e) {
			throw new RuntimeException(e);
		}
		
		return true;
		
		
	}


	public int getSmtpSetting() {
		return smtpSetting;
	}


	public void setSmtpSetting(int smtpSetting) {
		this.smtpSetting = smtpSetting;
	}
	

}
