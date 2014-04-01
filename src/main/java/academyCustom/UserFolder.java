package academyCustom;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;

public class UserFolder {	
	private String root;
	
	private Date date;
	private String fYear;
	private String fMonth;
	private String fDate;
	
	public UserFolder(String root, Date date) {
		this.root = root;
		this.date = date;
		
		setupFolderName();
	}
	
	public UserFolder(String root) {
		this(root, new Date());
	}
	
	public void setRoot(String root) {
		this.root = root;
		setupFolderName();
	}
	

	//Expectin folder format: root\2014\Feb2014\Fishell\022014
	
	
	public String[] getUserFolders(String[] lasts) {
		Vector<String> folders = new Vector<String>();
		
		String folder;
		for (String last : lasts) {
			folder = root + File.separator + fYear + File.separator + fMonth +
				File.separator + last + File.separator + fDate;
			
			File file = new File(folder);
			
			if (file.exists() && file.isDirectory())
				folders.add(folder);
		}
			
		if (folders.size() < 1)
			return new String[0];
		
		return (String[]) folders.toArray(new String[0]);
	}
	
	public String getLast(String path) {
		
		String [] parts = path.split( "\\" + File.separator);
		
		if (parts.length < 5)
			return new String();
		
		return parts[parts.length - 2];
	}
	
	public String [] getAttachmentForUser(String folder, String initial) {
		
		File file = new File(folder);
		
		Vector<String> attachments = new Vector<String>();
		
		if (file.exists() && file.isDirectory()) {
			String[] filesInFolder = file.list();
			
			for (String dataFile : filesInFolder) {
				if (!dataFile.trim().subSequence(0, 1).equals(".") && dataFile.contains("_" + initial + "_ed")) {
					attachments.add(folder + File.separator + dataFile);
				}
			}
		}

		return (String[]) attachments.toArray(new String[0]);
	}

	public boolean move(String folder, String initial) {
		File file = new File(folder);
		
		if (file.exists() && file.isDirectory()) {
			String[] filesInFolder = file.list();
			
			String sentFolder = folder + File.separator + "sent";
			
			if (! new File(sentFolder).exists()) {
				File fs = new File(sentFolder);
				fs.mkdirs();
			}
			
			for (String dataFile : filesInFolder) {
				if (dataFile.contains("_" + initial + "_")) {
					
					File oldFile = new File(folder + File.separator + dataFile);
					
					oldFile.renameTo(new File(sentFolder + File.separator + dataFile));
				}
			}
		}
		
		return true;
	}
	
	public int getNumofFiles(String folder) {
		File file = new File(folder);
		
		if (file.exists() && file.isDirectory()) {
			File[] files = file.listFiles();
			
			if (files == null) {
				return 0;
			}
			
			int num = 0;
			
			for (File tfl : files) {
				// make sure file is not a directory and is not hidden
				if (tfl.isFile() && !tfl.isHidden()) {
					num++;
				}
			}
			
			return num;		
		}
		
		return 0;
	}
	
	public String[] getInitialsInFolder(String foldername){
		
		Vector<String> initials = new Vector<String>();
		
		File folder = new File(foldername);
		
		if (folder.exists() && folder.isDirectory()) {
			File[] files = folder.listFiles();
			
			if (files != null) {
				String fname;
				String[] namestrs;
				String initial;
				
				for (File file : files) {
					fname = file.getName();
					namestrs = fname.split("_");
					
					if (namestrs.length > 2) {
						initial = namestrs[namestrs.length - 2];
						
						if (! initials.contains(initial)) {
							initials.add(initial);
						}
					}			
					
				}
			}
			
		}
		
		
		return (String[]) initials.toArray(new String[0]);
		
	}
	
	
	private void setupFolderName() {
		SimpleDateFormat sdf = new SimpleDateFormat("MMddyy", Locale.US);		
		fDate = sdf.format(date);
		
		sdf = new SimpleDateFormat("MMMyyyy", Locale.US);		
		fMonth = sdf.format(date);
		
		sdf = new SimpleDateFormat("yyyy",Locale.US);		
		fYear = sdf.format(date);
	}
	
}
