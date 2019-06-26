package simple;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class FileUtil {
	String directoryPath;

	public FileUtil(String targetDir) {
		this.directoryPath = targetDir;
	}

	boolean matchDate(String fileName) {
		try {
			boolean result;
			File file = new File(this.directoryPath + File.separator + fileName);
			Runner.print(file.getAbsolutePath());
			Runner.print("" + file.exists());
			if (file.exists()) {
				Runner.print("Filename :"+fileName+" Found");
				Runner.print("File creation date : "+getCreationDate(file));
				result = getLastModifiedDate(file).equals(getCurrentDate());
				Runner.print("Matches with current date : "+result);
				getCurrentDate();
				return result;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return false;
	}

	
	String getCreationDate(File file) throws IOException {

		BasicFileAttributes attr = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
		String cr_dateTime = attr.creationTime().toString();
		String cr_date = cr_dateTime.split("T")[0];
		return cr_date;

	}
	
	String getLastModifiedDate(File file) throws IOException {

		BasicFileAttributes attr = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
		String cr_dateTime = attr.lastModifiedTime().toString();
		String cr_date = cr_dateTime.split("T")[0];
		return cr_date;

	}

	private String getCurrentDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(new Date());
		return date;
	}

}
