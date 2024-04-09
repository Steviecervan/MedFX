package medfx;

import java.io.Serializable;

public class User implements Serializable
{
	/**
	 * Generated serialVersionUID in order to write the object as a file
	 */
	private static final long serialVersionUID = 5053859598126977567L;
	private PersonalInformation personalInfo;	//	Class holds the first name, last name, birthday, address, phone, email
	private String role; // Three different roles: Patient, Doctor, Nurse
	private String username;

	public User(PersonalInformation personalInfo, String role) {
		this.personalInfo = personalInfo;
		this.role = role;
		
		// generates username from personal info
		this.username = personalInfo.getLastName() + "-" + personalInfo.getFirstName() + "-" + personalInfo.getBirthDate();
		
	}
	
	public PersonalInformation getPersonalInfo() {
		return this.personalInfo;
	}

	
	public String getUsername() {
		return username;
	}
	
	
	public String getRole() {
		return role;
	}
}
