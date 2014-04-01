package academyCustom;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Frank
 *
 */
public class MyEmailValidator {
	
	private Pattern pattern;
	private Matcher matcher;
	private static final MyEmailValidator INSTANCE = new MyEmailValidator();
	
	private static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
					+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	// private constructor to enforce singleton
	private MyEmailValidator() {
		pattern = Pattern.compile(EMAIL_PATTERN);
	}
	
	public static MyEmailValidator getInstance() {
		return INSTANCE;
	}
	
	public boolean validate(final String email) {
		matcher = pattern.matcher(email);
		
		return matcher.matches();
	}
	

}
