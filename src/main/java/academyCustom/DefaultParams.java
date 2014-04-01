package academyCustom;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DefaultParams {

	private String rootFolder = "";
	private String excelFile = "";
	private String ccEmail = "";
	private String bccEmail = "";
	private String email = "";
	private String signature = "";

	private int numPars = 6;

	private File f;

	public DefaultParams(String fname) {
		f = new File(fname);	
	}

	public DefaultParams() {
		this("./params.txt");
	}
	
	// methods
	public boolean readDefault() throws IOException {
		if (!f.exists()) {
			return false;
		}
		
		BufferedReader br = new BufferedReader(new FileReader(f));
		
		try {				
			String line = br.readLine();

			String[] input = new String[numPars];

			int ii = 0;

			while (line != null && ii < numPars) {
				input[ii++] = line;
				line = br.readLine();
			}

			fillParams(input);
		} catch (IOException e) {
		
		} finally {
			if (br != null) {
				br.close();
			}
		}

		return true;
	}


	public boolean saveDefault(String rootFolder, String excelFile, String ccEmail,
			String bccEmail, String email, String signature) throws IOException {

		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(f, false));
		
		try {
			bw.write(rootFolder); bw.newLine();
			bw.write(excelFile); bw.newLine();
			bw.write(ccEmail); bw.newLine();
			bw.write(bccEmail); bw.newLine();
			bw.write(email); bw.newLine();
			bw.write(signature);
		} catch (IOException e) {
			e.printStackTrace();			
		} finally {
			if (bw != null) {
				bw.close();
			}
		}

		return true;	
	}

	private void fillParams(String[] input) {
		rootFolder = input[0];
		excelFile = input[1];
		ccEmail = input[2];
		bccEmail = input[3];
		email = input[4];
		signature = input[5];
	}

	public String getRootFolder() {
		return rootFolder;
	}

	public void setRootFolder(String rootFolder) {
		this.rootFolder = rootFolder;
	}

	public String getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(String excelFile) {
		this.excelFile = excelFile;
	}

	public String getCcEmail() {
		return ccEmail;
	}

	public void setCcEmail(String ccEmail) {
		this.ccEmail = ccEmail;
	}

	public String getBccEmail() {
		return bccEmail;
	}

	public void setBccEmail(String bccEmail) {
		this.bccEmail = bccEmail;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getNumPars() {
		return numPars;
	}

	public void setNumPars(int numPars) {
		this.numPars = numPars;
	}

	public File getF() {
		return f;
	}

	public void setF(File f) {
		this.f = f;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

}
