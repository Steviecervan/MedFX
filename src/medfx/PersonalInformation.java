/*	Class Contains:
*	Basic user information. First, last, address, email, birthday, email, and phone number.
*	This class will be associated to **every** user, including doctor and nurse.
*/

package medfx;

public class PersonalInformation {
	private String firstName;
	private String lastName;
	private String address;
	private String email;
	private String birthDate;	//	MM/DD/YYYY
	private int phoneNumber;
	
	public PersonalInformation(String firstName, String lastName, String address, String email, String birthDate, int phoneNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.email = email;
		this.birthDate = birthDate;
		this.phoneNumber = phoneNumber;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getDate() {
		return birthDate;
	}
	
	public int getPhoneNumber() {
		return phoneNumber;
	}
	
	//	Editable attributes
	public void changeAddress(String newAddress) {
		this.address = newAddress;
	}
	
	public void changeEmail(String newEmail) {
		this.email = newEmail;
	}
	
	public void changePhoneNumber(int newPhoneNumber) {
		this.phoneNumber = newPhoneNumber;
	}
}
