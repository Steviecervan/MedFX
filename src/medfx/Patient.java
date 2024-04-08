package medfx;

import java.util.ArrayList;
import java.util.List;

public class Patient extends User {
	private static final long serialVersionUID = -1632230017250822490L;
	private PersonalInformation personalInfo;
	private ArrayList<Visits> visitInformation; // Updated to store Visit objects

	// Constructor
	public Patient(PersonalInformation personalInfo, String role) {
		super(personalInfo, role); // Calls the constructor for User
		this.visitInformation = new ArrayList<>(); // Instantiate an empty ArrayList for visit information
	}

	// Adds a new visit to the visit summaries for this patient
	public void addVisit(Visits visit) {
		if (visit != null) {
			visitInformation.add(visit);
		}
	}

	// Removes a visit from the patient's record based on visit ID
	public void removeVisit(String visitID) {
		visitInformation.removeIf(visit -> visit.getVisitID().equals(visitID));
	}


	// Finds a specific visit by its ID
	public Visits findVisit(String visitID) {
		for (Visits visit : visitInformation) {
			if (visit.getVisitID().equals(visitID)) {
				return visit;
			}
		}
		return null;
	}

	// Update a visit in the patient's record
	public void updateVisit(Visits visit) {
		for (int i = 0; i < this.visitInformation.size(); i++) {
			if (this.visitInformation.get(i).getVisitID().equals(visit.getVisitID())) {
				this.visitInformation.set(i, visit);
				break;
			}
		}
	}


	// Retrieves all visits for this patient
	public List<Visits> getVisits() {
		return this.visitInformation;
	}

	// Getters and setters for personalInfo if needed...
}
