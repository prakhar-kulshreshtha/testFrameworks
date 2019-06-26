package simple;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public class Runner {

	private final static String fileName = "oho.txt";
	private final static String fileDir = "/Users/innovation/Desktop";
	protected static String timeStamp() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
	public static void print(String msg) {
		System.out.println(timeStamp()+" : "+msg);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		print("App started");
		
		FileUtil fs = new FileUtil(fileDir);
//		fs.matchDate("Info.txt");
		boolean result = fs.matchDate(fileName);
		
		
	// mail send part
		String senderEmail = "autobotgamma@gmail.com";
		String recepientList = "prakhar.kulshreshtha@outlook.com,prakhar04101990@gmail.com,Sahil.2824@gmail.com";
		MailClient mail = new MailClient();
		try {
			mail.setFrom(senderEmail);
			mail.setRecipients(recepientList);
			print("File match result: "+result);
			if (result) {
				mail.setSubject("Auto generated mail | File match - Success");
				mail.setBodyContent(timeStamp()+"File Match result for file name : "+fileName+" : SUCCESS");
			}
			else {
					mail.setSubject("Auto generated mail | File match - Failure");
					mail.setBodyContent(timeStamp()+"File Match result for file name : "+fileName+" : FAILURE");
			}
			if (mail.send()) {
			print("Program terminated successfully");	
			}
			
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
