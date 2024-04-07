package medfx;

public class User {
	private PersonalInformation personalInfo;	//	Class holds the first name, last name, birthday, address, phone, email
	private String role;
	private String username;

	public User(PersonalInformation personalInfo, String role) {
		this.personalInfo = personalInfo;
		this.role = role;
		
		// generates username from personal info
		this.username = personalInfo.getLastName() + "-" + personalInfo.getFirstName() + "-" + personalInfo.getDate();
		
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
