package medfx;

public class Nurse extends User{
	
	
	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = -120582796748093181L;

	public Nurse(PersonalInformation personalInfo) {
		//	Calls constructor for user
		super(personalInfo, "Nurse");
	}

}
