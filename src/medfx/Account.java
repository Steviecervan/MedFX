/*	Class Contains:
*	Account information used for logging into system: username, password, and role.
*	This class can be thought of as the class that will be interacting with the system for each user.
*	This class will be associated to **every** user, including doctor and nurse.
*/

package medfx;

public class Account {
	private Role role;	//	Patient, Nurse, or Doctor
	private String username;
	private String password;
	
	public Account(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	//	Get functions
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public Role getRole() {
		return role;
	}
	
	//	Change functions
	public void changeUsername(String newUsername) {
		this.username = newUsername;
	}
	
	public void changePassword(String newPassword) {
		this.password = newPassword;
	}
}
