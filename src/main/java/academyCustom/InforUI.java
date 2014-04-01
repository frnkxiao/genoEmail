package academyCustom;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class InforUI extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String rootFolder = "Pick a folder ...";
	private String excelFile = "Pick an excel file ...";
	private String date;
	private String ccEmail;
	private String bccEmail;
	private String email;
	private String password;
	private String signature;
	private int smtpSetting;
			
	private JTextField txtRootFolder;
	private JTextField txtExcelFile;
	private JTextField txtDate;
	
	private JButton btnSelectFolder;
	private JButton btnSelectFile;
	private JButton btnSendEmails;
	private JTextField txtEmail;
	private JPasswordField txtPassword;
	private JTextField txtCcEmail;
	private JTextField txtBccEmail;
	private JTextField txtSignature;
	private JComboBox cmbSmtp;	
	private JFileChooser fc;
	private DefaultParams dparams;
	
	private App app;
	
	
	public InforUI() {	
		dparams = new DefaultParams();		
		fc = new JFileChooser();
		app = new App();
		
		try {
			loadDefault();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		setDate(new Date());		
		setupWindow();
		
	}
	
	private void loadDefault() throws IOException {
		if (dparams.readDefault()) {
			rootFolder = dparams.getRootFolder();
			excelFile = dparams.getExcelFile();
			ccEmail = dparams.getCcEmail();
			bccEmail = dparams.getBccEmail();
			email = dparams.getEmail();
			signature = dparams.getSignature();
		}		
	}

	private void setupWindow() {
		
		setTitle("GenoEmail");
		getContentPane().setLayout(null);
		
		setSize(450, 626);
		
		// root folder
		JLabel lblRootFolder = new JLabel("Data Root Folder: ");
		lblRootFolder.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblRootFolder.setBounds(55, 48, 133, 14);
		getContentPane().add(lblRootFolder);
		
		txtRootFolder = new JTextField();
		txtRootFolder.setHorizontalAlignment(SwingConstants.CENTER);
		txtRootFolder.setToolTipText("Type or pick a root folder.");
		txtRootFolder.setText(rootFolder);
		txtRootFolder.setBounds(55, 73, 345, 20);
		getContentPane().add(txtRootFolder);
		txtRootFolder.setColumns(10);
		
		btnSelectFolder = new JButton("Select Folder");
		btnSelectFolder.setToolTipText("Select root folder");
		btnSelectFolder.addActionListener(this);
		btnSelectFolder.setBounds(286, 44, 114, 23);
		getContentPane().add(btnSelectFolder);
		
		// excel file
		JLabel lblExcelFile = new JLabel("Geno User Info Excel File:");
		lblExcelFile.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblExcelFile.setBounds(55, 121, 194, 14);
		getContentPane().add(lblExcelFile);
		
		txtExcelFile = new JTextField();
		txtExcelFile.setHorizontalAlignment(SwingConstants.CENTER);
		txtExcelFile.setToolTipText("Type or select Excel file containing user emails");
		txtExcelFile.setText(excelFile);
		txtExcelFile.setColumns(10);
		txtExcelFile.setBounds(55, 144, 345, 20);
		getContentPane().add(txtExcelFile);
		
		btnSelectFile = new JButton("Select File");
		btnSelectFile.addActionListener(this);
		btnSelectFile.setBounds(286, 117, 114, 23);
		getContentPane().add(btnSelectFile);
		
		// date 	
		JLabel lblDate = new JLabel("Date (MM/DD/YYYY):");
		lblDate.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblDate.setBounds(55, 198, 193, 14);
		getContentPane().add(lblDate);
		
		txtDate = new JTextField();
		txtDate.setHorizontalAlignment(SwingConstants.CENTER);
		txtDate.setToolTipText("Pick a date to send data");
		txtDate.setText(date);
		txtDate.setColumns(10);
		txtDate.setBounds(230, 195, 89, 20);
		getContentPane().add(txtDate);
		
		// user login
		JLabel lblUserLogin = new JLabel("App User Login:");
		lblUserLogin.setBackground(Color.BLUE);
		lblUserLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserLogin.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblUserLogin.setBounds(38, 358, 362, 20);
		getContentPane().add(lblUserLogin);
		
		// email
		JLabel lblUsername = new JLabel("Email:");
		lblUsername.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblUsername.setBounds(75, 386, 57, 14);
		getContentPane().add(lblUsername);
		
		txtEmail = new JTextField();
		txtEmail.setHorizontalAlignment(SwingConstants.CENTER);
		txtEmail.setToolTipText("Please type in your full email address");
		txtEmail.setText(email);
		txtEmail.setColumns(10);
		txtEmail.setBounds(147, 383, 187, 20);
		getContentPane().add(txtEmail);
		
		// password
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblPassword.setBounds(75, 414, 77, 14);
		getContentPane().add(lblPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.setHorizontalAlignment(SwingConstants.CENTER);
		txtPassword.setBounds(147, 411, 187, 20);
		getContentPane().add(txtPassword);
		
		// cc Email 
		JLabel lblCcEmail = new JLabel("Email cc:");
		lblCcEmail.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblCcEmail.setBounds(55, 250, 89, 14);
		getContentPane().add(lblCcEmail);
		
		txtCcEmail = new JTextField();
		txtCcEmail.setToolTipText("Please type in your full email address");
		txtCcEmail.setText(ccEmail);
		txtCcEmail.setHorizontalAlignment(SwingConstants.CENTER);
		txtCcEmail.setColumns(10);
		txtCcEmail.setBounds(129, 247, 187, 20);
		getContentPane().add(txtCcEmail);
		
		// bcc Email
		JLabel lblEmailBcc = new JLabel("Email bcc:");
		lblEmailBcc.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblEmailBcc.setBounds(55, 278, 89, 14);
		getContentPane().add(lblEmailBcc);
		
		txtBccEmail = new JTextField();
		txtBccEmail.setToolTipText("Please type in your full email address");
		txtBccEmail.setText(bccEmail);
		txtBccEmail.setHorizontalAlignment(SwingConstants.CENTER);
		txtBccEmail.setColumns(10);
		txtBccEmail.setBounds(129, 275, 187, 20);
		getContentPane().add(txtBccEmail);
		
		btnSendEmails = new JButton("Send Emails");
		btnSendEmails.addActionListener(this);
		btnSendEmails.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSendEmails.setBounds(147, 493, 157, 62);
		getContentPane().add(btnSendEmails);
		
		JLabel lblSignature = new JLabel("Signature:");
		lblSignature.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblSignature.setBounds(55, 306, 89, 17);
		getContentPane().add(lblSignature);
		
		txtSignature = new JTextField();
		txtSignature.setToolTipText("Enter your signatrue for email");
		txtSignature.setText(signature);
		txtSignature.setHorizontalAlignment(SwingConstants.CENTER);
		txtSignature.setColumns(10);
		txtSignature.setBounds(129, 303, 187, 20);
		getContentPane().add(txtSignature);
		
		cmbSmtp = new JComboBox();
		cmbSmtp.setToolTipText("Try different SMTP setting");
		cmbSmtp.setModel(new DefaultComboBoxModel(new String[] {"SMTP-TLS-587", "SMTP-SSL-465", "SMTP-SSL-25"}));
		cmbSmtp.setSelectedIndex(0);
		cmbSmtp.setBounds(147, 457, 187, 23);
		getContentPane().add(cmbSmtp);

	}
	
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == btnSelectFolder) {
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int rval = fc.showOpenDialog(InforUI.this);
			
			if (rval == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();				
				
				txtRootFolder.setText(file.getAbsolutePath());
			}
			
		} else if (event.getSource() == btnSelectFile) {
			
			String[] ext = {"xls","xlsx"};
			fc.setFileSelectionMode(JFileChooser.FILES_ONLY);			
			fc.setFileFilter(new ExtensionFileFilter("MS Excel",ext));
			int rval = fc.showOpenDialog(InforUI.this);
			
			if (rval == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				
				txtExcelFile.setText(file.getAbsolutePath());
			}
			
		} else if (event.getSource() == btnSendEmails) {	
			
			updateParams();
			
			if (validateParams()) {
				
				try {
					dparams.saveDefault(rootFolder, excelFile, ccEmail, bccEmail, email, signature);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				app.setAllParams(rootFolder, excelFile, getDate(), ccEmail, bccEmail, email, password, signature, smtpSetting);
				
				try {
					app.sendEmails();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
					return;
				} catch (RuntimeException re) {
					JOptionPane.showMessageDialog(null, "Email or password is incorrect.", 
							"Fail to login", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				showMessages();				
				
			} else {
				JOptionPane.showMessageDialog(null, "Please fill in all neccessary the information.",  
						"Missing information!", JOptionPane.ERROR_MESSAGE);
			}
			
		}
	}
		
	private void showMessages() {
		String msg;
		String title;
		
		
		if (app.getNumEmailSent() == 0) {
			title = "No messages have been sent!";
			
			if (app.getNumFileUnsent() == 0) {
				msg = "No data was found for the given date. \n\nThank you for using GenoEmail application.";
								
			} else {
				msg = "No messages have been sent, however " + app.getNumFileUnsent() + 			
					" data files were found for the given date. \n\nPlease make sure you have all user information in " +
					"the given Excel file. \n\nThank you for using GenoEmail application.";				 
			}
		} else {
			title = "Messages successfully sent!";
			
			if (app.getNumFileUnsent() == 0) {
				msg = "You just sent " + app.getNumEmailSent() + 
						" messages to the users. \n\nThank you for using GenoEmail application.";
				 
			}else {
				msg = "You just sent " + app.getNumEmailSent() + 
						" messages, however " + app.getNumFileUnsent() + 
						" more data files were found for the given date. \n\nPlease make sure you have all user information in " +
						"the given Excel file. \n\nThank you for using GeoEmail application.";
						
			}
		}
		
		JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);		

	}

	private void updateParams() {
		rootFolder = txtRootFolder.getText();
		excelFile = txtExcelFile.getText();
		ccEmail = txtCcEmail.getText();
		bccEmail = txtBccEmail.getText();
		email = txtEmail.getText();
		password = new String(txtPassword.getPassword());
		date = txtDate.getText();
		signature = txtSignature.getText();
		smtpSetting = cmbSmtp.getSelectedIndex();
	}

	private boolean validateParams() {
		if (rootFolder == null || ! new File(rootFolder).exists())
			return false;
		
		if (excelFile == null || !new File(excelFile).exists())
			return false;
		
		MyEmailValidator ev = MyEmailValidator.getInstance();
		if (email == null || !ev.validate(email))
			return false;
		
		if (password == null)
			return false;
		
		return true;
	}

	public String getRootFolder() {
		return rootFolder;
	}

	public void Folder(String rootFolder) {
		this.rootFolder = rootFolder;
	}

	public String getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(String excelFile) {
		this.excelFile = excelFile;
	}

	@SuppressWarnings("deprecation")
	public Date getDate() {
		String[] str = date.split("/");
		int year = Integer.parseInt(str[2]) - 1900;
		int month = Integer.parseInt(str[0]) - 1;
		int day = Integer.parseInt(str[1]);
		
		return new Date(year,month,day);
	}

	public void setDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
		
		this.date = sdf.format(date);
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

	public String getPassword() {
		return password;
	}
}
