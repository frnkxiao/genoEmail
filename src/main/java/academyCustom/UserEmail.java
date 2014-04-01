package academyCustom;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author Frank
 * 
 *
 */
public class UserEmail {

	private Vector<User> users = new Vector<User>();
	
	
	
	public UserEmail() {
		
	}
	
	
	public boolean importExcel(String fname) throws IOException {
		
		String ext = getExtension(fname); 
		if (ext.equals("xlsx")) {
			return importExcelXlsx(fname);
		} else if (ext.equals("xls")) {
			return importExcelXls(fname);
		}
		
		return false;
	}
	
	
	private String getExtension(String fname) {
		String[] str = fname.split("\\.");
		
		return str[str.length - 1];
	}


	private boolean importExcelXlsx(String fname) throws IOException {
		
		FileInputStream inWorkbook = new FileInputStream(fname);
		
		XSSFWorkbook wb;
		
		try {
			wb = new XSSFWorkbook(inWorkbook);
			
			// Get the first sheet from workbook
			XSSFSheet sheet = wb.getSheetAt(0);
			
			String[] uinfo = new String[3];
			boolean valid;
			
			Iterator<Row> rowIterator = sheet.iterator();
						
			while (rowIterator.hasNext()) {
				
				valid = true;
				
				Row row = rowIterator.next();				
				Iterator<Cell> cellIterator = row.cellIterator();
				
				int i = 0;
				
				while(cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					
					// Assumed PI/user/email in each cols respectively
					if (i < 3 && cell.getCellType() == Cell.CELL_TYPE_STRING) {
						uinfo[i++] = cell.getStringCellValue();
					} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
						valid = false;
						break;
					} else {
						System.err.println("Error in UserEmail.java: unexpect cell format from excel file.");
						
						valid = false;
						
						break;
					}
					
				}
				
				if (valid)
					addUser(uinfo);				
			}
			
				
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (users.size() > 0) 
			return true;
		
		return false;
	}
	
private boolean importExcelXls(String fname) throws IOException {
		
		FileInputStream inWorkbook = new FileInputStream(fname);
		
		HSSFWorkbook wb;
		
		try {
			wb = new HSSFWorkbook(inWorkbook);
			
			// Get the first sheet from workbook
			HSSFSheet sheet = wb.getSheetAt(0);
			
			String[] uinfo = new String[3];
			boolean valid;
			
			Iterator<Row> rowIterator = sheet.iterator();
						
			while (rowIterator.hasNext()) {
				
				valid = true;
				
				Row row = rowIterator.next();				
				Iterator<Cell> cellIterator = row.cellIterator();
				
				int i = 0;
				
				while(cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					
					// Assumed PI/user/email in each cols respectively
					if (i < 3 && cell.getCellType() == Cell.CELL_TYPE_STRING) {
						uinfo[i++] = cell.getStringCellValue();
					} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
						valid = false;
						break;
					} else {
						System.err.println("Error in UserEmail.java: unexpect cell format from excel file.");
						
						valid = false;
						
						break;
					}
					
				}
				
				if (valid)
					addUser(uinfo);				
			}
			
				
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (users.size() > 0) 
			return true;
		
		return false;
	}

	public User[] getUsersForPI(String pIName) {
		Vector<User> pIUsers = new Vector<User>();
		
		for (User user : users) {
			if (user.getpI().equalsIgnoreCase(pIName)) {
				pIUsers.add(user);
			}
		}
		
		return (User[]) pIUsers.toArray(new User[0]);
	}
	
	public User[] getUsersForPILast(String pIName) {
		Vector<User> pIUsers = new Vector<User>();
		
		for (User user : users) {
			if (user.getPILast().equalsIgnoreCase(pIName)) {
				pIUsers.add(user);
			}
		}
		
		return (User[]) pIUsers.toArray(new User[0]);
	}
	
	public String [] getPIsLast() {
		Vector<String> lasts = new Vector<String>();
		
		for (User user : users) {
			lasts.add(user.getPILast());
		}
		
		int ii = 0;
		while (ii < lasts.size()) {
			for (int jj = ii + 1; jj < lasts.size(); jj++) {
				if (lasts.get(ii).equals(lasts.get(jj))) {
					lasts.remove(jj);
					jj--;
				}
			}
			
			ii++;
		}
		
		return (String[]) lasts.toArray(new String[0]);
	}
	
	public User[] getAllUsers() {
		return (User[]) users.toArray(new User[0]);
	}
	
	public void addUser(String pI, String username, String email) {
		User user = new User(pI, username, email);
		
		if (user.isValidEmail()) {
			users.add(user);
		}
	}
	
	public void addUser(String[] uinfo) {
		if (uinfo.length < 3)
			return;
		
		addUser(uinfo[0], uinfo[1], uinfo[2]);
	}


	public User getUserByPiLastAndUserInital(String last, String initial) {

		for (User user : users) {
			if (user.getPILast().equalsIgnoreCase(last) && user.getUsernameInitial().equalsIgnoreCase(initial)) {
				return user;
			}
		}

		return null;
	}
	
}
