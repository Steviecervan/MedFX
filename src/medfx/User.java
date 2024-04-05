package medfx;

public class User {
	private PersonalInformation personalInfo;	//	Class holds the first name, last name, birthday, address, phone, email
	private Account account;	//	username, password, role, changeUsername, changePassword

	public User(PersonalInformation personalInfo, Account accout) {
		this.personalInfo = personalInfo;
		this.account = account;
	}
	
	public PersonalInformation getPersonalInfo() {
		return this.personalInfo;
	}
	
	public Account getAccount() {
		return this.account;
	}
}
