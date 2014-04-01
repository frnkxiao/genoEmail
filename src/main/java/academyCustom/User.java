package academyCustom;

/**
 * @author Frank
 *
 */
public class User {
	
	private String pI;			// expected format: Last,F
	private String username;	// expected format: Last,F
	private String email;
	
	
	// constructor
	public User(String pI, String username, String email) {
		this.pI = pI.trim();
		this.username = username.trim();
		this.email = email.trim();
	}


	public String getpI() {
		return pI;
	}


	public String getUsername() {
		return username;
	}


	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public boolean isValidEmail() {
		MyEmailValidator ev = MyEmailValidator.getInstance();
		
		return ev.validate(email);
	}
	
	public String getpIInitial() {
		return getInitial(pI);
	}

	public String getPILast() {
		if (getpIInitial().isEmpty())
			return null;
		
		return pI.split(",")[0].trim();
	}
	
	public String getUserLast() {
		if (getUsernameInitial().isEmpty())
			return null;
		
		return username.split(",")[0].trim();
	}

	public String getUsernameInitial() {
		return getInitial(username);
	}

	private String getInitial(String name) {
		// expect last, F format, otherwise return null
		
		String[] lf = name.split(",");
		
		if (lf.length != 2)
			return null;
		
		return (lf[1].trim().substring(0, 1) + lf[0].trim().substring(0, 1)).toUpperCase();		
	}
	
	public String toString() {
		return "PI: " + pI + "; User: " + username + "; Email: " + email;
	}

}
