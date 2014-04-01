package academyCustom;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.JOptionPane;
/**
 * @author Frank
 *
 *
 *
 * 
 */
public class App 
{
	private String root;
	private String excelFile;
	private Date date;
	private String ccEmail;
	private String bccEmail;
	private String email;
	private String password;
	private String signature;
	private int smtpSetting;
	
	private int numEmailSent;	
	private int numFileUnsent; 
	
	
	public App() {
		numEmailSent = 0;
		numFileUnsent = 0;
	}
	
    public void sendEmails() throws IOException, RuntimeException {
    	
    	if (root == null || excelFile == null || date == null ||
    			email == null || password == null) {
    		
    		JOptionPane.showMessageDialog(null, "Please fill in all neccessary the information.", 
    				"Missing information!", JOptionPane.ERROR_MESSAGE);
    		return;
    	}
    	       
    	numEmailSent = 0;
    	numFileUnsent = 0;
    	
        //Setup folder structure        
        UserFolder userFolder = new UserFolder(root, date);
        
        //Setup user information from excel file 
        UserEmail userEmail = new UserEmail();
        
        //Setup App user email info
        GenoEmail genoEmail = new GenoEmail(email, password);
        
        MyEmailValidator ev = MyEmailValidator.getInstance();
        
        
        if (! ccEmail.isEmpty() && ev.validate(ccEmail)) 
        	genoEmail.setCcEmail(ccEmail);
        
        if (! bccEmail.isEmpty() && ev.validate(bccEmail))
        	genoEmail.setBcEmail(bccEmail);
        
        genoEmail.setSubject("Genotyping result " + getDateStr());
        
        if (! signature.isEmpty()) {
        	genoEmail.setSender(signature);
        } else {
        	genoEmail.setSender("Geno Core");
        }
        
        if (smtpSetting > -1)
        	genoEmail.setSmtpSetting(smtpSetting);
        
        
        
        if (userEmail.importExcel(excelFile)) {
        	//get list of PI's lastname
        	String[] lasts = userEmail.getPIsLast();
        	
        	String[] folders = userFolder.getUserFolders(lasts);
        	
        	if (folders.length > 0) {
	        	for (String folder : folders) {
	        		
	        		String piLast = userFolder.getLast(folder);
	        		String[] initialInFolder = userFolder.getInitialsInFolder(folder);
	        		
	        		for (String initial : initialInFolder) {
	        			User user = userEmail.getUserByPiLastAndUserInital(piLast, initial);
	        			
	        			// user in database
	        			if (user != null) {
	        			
		        			String[] attachments = userFolder.getAttachmentForUser(folder, initial);
		        			
		        			// Data available for user
		        			if (attachments.length > 0) {
		        				genoEmail.setUser("Dr. " + user.getUserLast());
		        				
		        				if (genoEmail.send(user.getEmail(), attachments)) {
		        					numEmailSent++;		        				
			        				userFolder.move(folder, user.getUsernameInitial());	        					
		        				}	        			
		        			}
	        			}
	        		}
	        		
	        		numFileUnsent += userFolder.getNumofFiles(folder);
	        	}
        	}
        	
        }
    }

	public void setRoot(String root) {
		this.root = root;
	}

	public void setExcelFile(String excelFile) {
		this.excelFile = excelFile;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setCcEmail(String ccEmail) {
		this.ccEmail = ccEmail;
	}

	public void setBccEmail(String bccEmail) {
		this.bccEmail = bccEmail;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setAllParams(String root, String excelFile, Date date, 
			String ccEmail, String bccEmail, String email, String password, String signature, int smtpSetting) {
		
		this.root = root;
		this.excelFile = excelFile;
		this.date = date;
		this.ccEmail = ccEmail;
		this.bccEmail = bccEmail;
		this.email = email;
		this.password = password;
		this.signature = signature;
		this.smtpSetting = smtpSetting;
	}

	public int getNumEmailSent() {
		return numEmailSent;
	}
	
	public int getNumFileUnsent() {
		return numFileUnsent;
	}
	
	
	private String getDateStr() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yy", Locale.US);
		
		return sdf.format(date);
	}

	
}
