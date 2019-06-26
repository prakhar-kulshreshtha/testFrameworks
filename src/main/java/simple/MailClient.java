package simple;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailClient {
	private Properties props;
	private Session session;
	MimeMessage message;
	Multipart multipart;

	public MailClient() {
		props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		session = Session.getDefaultInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("autobotgamma@gmail.com", "Autob0ts");// change
																						// accordingly
			}
		});
		message = new MimeMessage(session);
		multipart = new MimeMultipart();
	}

	void setFrom(String sender) throws AddressException, MessagingException {
		message.setFrom(new InternetAddress(sender));
	}

	void setRecipients(String to) throws MessagingException {
		message.addRecipients(Message.RecipientType.TO, to);

	}

	void setSubject(String sub) throws MessagingException {
		message.setSubject(sub);
	}

	void setBodyContent(String body) throws MessagingException {
		/*
		 * //Set Content to Body FileDataSource fdsbody = new FileDataSource(body);
		 * BodyPart messageBodyPart = new MimeBodyPart();
		 * messageBodyPart.setDataHandler(new DataHandler(fdsbody));
		 * multipart.addBodyPart(messageBodyPart);
		 */

		message.setText(body);

		// Set the multiplart object to the message object
		// message.setContent(multipart);

	}

	/*
	 * void setAttachmentContent(String attachment) throws MessagingException {
	 * 
	 * //Set Content to attachment FileDataSource fds = new
	 * FileDataSource(attachment); MimeBodyPart attachFilePart = new MimeBodyPart();
	 * attachFilePart.setDataHandler(new DataHandler(fds));
	 * attachFilePart.setFileName(fds.getName());
	 * multipart.addBodyPart(attachFilePart);
	 * 
	 * }
	 * 
	 */
	boolean send() {
		try {
			// Set the multiplart object to the message object
			// message.setContent(multipart);
			Transport.send(message);
			Runner.print("Mail sent successfully");
			return true;
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			Runner.print("Couldn't send mail as :" + e.getMessage());
			e.printStackTrace();
			return false;

		}
	}

}
