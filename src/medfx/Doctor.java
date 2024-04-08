package medfx;

public class Doctor extends User{
	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = -140347943225749714L;
	private String docId;	//	D.....
	
	public Doctor(PersonalInformation personalInfo) {
		//	Calls constructor for User
		super(personalInfo, "Doctor");
	}
	
	public String getDoctorId() {
		return this.docId;
	}
}

// WE GOT THIS! 🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓