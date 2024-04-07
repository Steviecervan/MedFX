package medfx;

import java.util.ArrayList;

public class Patient extends User
{
	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = -1632230017250822490L;
	private PersonalInformation personalInfo;
	private ArrayList<Object> visitInformation;// will be updated with the type in the ArrayList once the patientvisit class is created
	
	public Patient(PersonalInformation personalInfo, String role)
	{
		super(personalInfo, role); // calls the constructor for User
		
		visitInformation = new ArrayList<Object>(); // instantiate an empty arraylist for the visitinformation
	}
	
	/**
	 * Gets the corresponding visit summary using the visitID and return ths Visit object
	 * 
	 * @param visitID String ID for the visit that is being looked for
	 * @return Visit or null if visit was not found
	 */
	public Object getVisitSummary(String visitID)
	{
		/*
		 * Add code to get visit summary once Visit class is implemented 
		 */
		return null;
	}
	
	/**
	 * Adds a new visit summary to the visit summaries for this patient
	 * @param visit Visit object being added
	 */
	public void addVisitSummary(Object visit)
	{
		visitInformation.add(visit);
	}
	
	// includes whatever methods are also in User

}
