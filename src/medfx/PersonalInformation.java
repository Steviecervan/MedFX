/*	Class Contains:
*	Basic user information. First, last, address, email, birthday, email, and phone number.
*	This class will be associated to **every** user, including doctor and nurse.
*/

package medfx;

import java.io.Serializable;

public class PersonalInformation implements Serializable {
	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = -8294629946474104641L;
	private String firstName;
	private String lastName;
	private String address;
	private String email;
	private String birthDate;	//	MM-DD-YYYY
	private String phoneNumber;
	
	public PersonalInformation(String firstName, String lastName, String address, String email, String birthDate, String phoneNumber) {
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
		if(address.equals("")){
			return "Information not provided";
		}
		return address;
	}
	
	public String getEmail() {
		if(email.equals("")) {
			return "Information not provided";
		}
		return email;
	}
	
	public String getBirthDate() {
		return birthDate;
	}
	
	public String getPhoneNumber() {
		if(phoneNumber.equals("")) {
			return "Information not provided";
		}
		return phoneNumber;
	}
	
	//	Editable attributes
	public void changeAddress(String newAddress) {
		this.address = newAddress;
	}
	
	public void changeEmail(String newEmail) {
		this.email = newEmail;
	}
	
	public void changePhoneNumber(String newPhoneNumber) {
		this.phoneNumber = newPhoneNumber;
	}
}
