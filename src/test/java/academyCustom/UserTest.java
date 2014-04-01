/**
 * 
 */
package academyCustom;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Frank
 *
 */
public class UserTest {

	/**
	 * @throws java.lang.Exception
	 */
	
	private User user;
	private String goodEmail;
	private String badEmailNoAt;
	private String badEmail;
	
	private String piInitial;
	private String piLast;
	private String piName;
	
	private String userInitial;
	private String userLast;
	private String username;	
	private String userEmail;
	
	private String tstr;
	
	@Before
	public void setUp(){
		
		user = new User("Fishell, G.", "Frank, X.", "someEmail@gmail.com" );
		
		goodEmail = "some@gmail.com";
		badEmailNoAt = "someemail";
		badEmail = "some@gtad.com1";
		
		piInitial = "GF";
		piLast = "Fishell";
		piName = "Fishell, G.";
		
		userInitial = "XF";
		userLast = "Frank";
		username = "Frank, X.";
		userEmail = "someEmail@gmail.com";
		
		tstr = "PI: " + piName + "; User: " + username + "; Email: " + userEmail;
	}

	/**
	 * Test method for {@link academyCustom.User#getpI()}.
	 */
	@Test
	public void testGetpI() {
		assertTrue(user.getpI().equals(piName));		
	}

	/**
	 * Test method for {@link academyCustom.User#getUsername()}.
	 */
	@Test
	public void testGetUsername() {
		assertTrue(user.getUsername().equals(username));
	}

	/**
	 * Test method for {@link academyCustom.User#getEmail()}.
	 */
	@Test
	public void testGetEmail() {
		assertTrue(user.getEmail().equals(userEmail));
	}

	/**
	 * Test method for {@link academyCustom.User#isValidEmail()}.
	 */
	@Test
	public void testIsValidEmail() {
		assertTrue(user.isValidEmail());
		
		user.setEmail(goodEmail);
		assertTrue(user.isValidEmail());
		
		user.setEmail(badEmailNoAt);
		assertFalse(user.isValidEmail());
		
		user.setEmail(badEmail);
		assertFalse(user.isValidEmail());
		
	}

	/**
	 * Test method for {@link academyCustom.User#getpIInitial()}.
	 */
	@Test
	public void testGetpIInitial() {
		
		assertTrue(user.getpIInitial().equals(piInitial));
		
	}

	/**
	 * Test method for {@link academyCustom.User#getPILast()}.
	 */
	@Test
	public void testGetPILast() {
		assertTrue(user.getPILast().equals(piLast));
	}

	/**
	 * Test method for {@link academyCustom.User#getUserLast()}.
	 */
	@Test
	public void testGetUserLast() {
		assertTrue(user.getUserLast().equals(userLast));
	}

	/**
	 * Test method for {@link academyCustom.User#getUsernameInitial()}.
	 */
	@Test
	public void testGetUsernameInitial() {
		assertTrue(user.getUsernameInitial().equals(userInitial));
	}

	/**
	 * Test method for {@link academyCustom.User#toString()}.
	 */
	@Test
	public void testToString() {
		assertTrue(user.toString().equals(tstr));
	}

}
