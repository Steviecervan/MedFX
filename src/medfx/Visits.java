package medfx;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Visits implements Serializable {
	private static final long serialVersionUID = 1L;

	private String visitID;
	private Date timestamp;
	private String vitals;
	private String healthConcerns;
	private List<Medication> prescriptions;
	
	private double weight;
	private double height;
	private String bloodPressure;
	private double bodyTemp;
	private String physicalResults;

	// Constructor
	public Visits(Date timestamp, String vitals, String healthConcerns, List<Medication> prescriptions) {
		this.visitID = UUID.randomUUID().toString(); // Generate a unique ID for each visit.
		this.timestamp = timestamp;
		this.vitals = vitals;
		this.healthConcerns = healthConcerns;
		this.prescriptions = prescriptions;
	}

	// Getters and setters
	public String getVisitID() {
		return visitID;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getVitals() {
		return vitals;
	}

	public String getHealthConcerns() {
		return healthConcerns;
	}
	
	public double getWeight() {
		return this.weight;
	}
	
	public double getHeight() {
		return this.height;
	}
	
	public String getBloodPressure() {
		return this.bloodPressure;
	}
	
	public double getBodyTemperature() {
		return this.bodyTemp;
	}
	
	public String getPhysicalResults() {
		return this.physicalResults;
	}

	public List<Medication> getPrescriptions() {
		return prescriptions;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public void setHeight(double height) {
		this.height = height;
	}
	
	public void setBloodPressure(String bloodPressure) {
		this.bloodPressure = bloodPressure;
	}
	
	public void setBodyTemperature(double bodyTemperature) {
		this.bodyTemp = bodyTemperature;
	}
	
	public void setPhysicalResults(String physicalResults) {
		this.physicalResults = physicalResults;
	}
	
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public void setVitals(String vitals) {
		this.vitals = vitals;
	}

	public void setHealthConcerns(String healthConcerns) {
		this.healthConcerns = healthConcerns;
	}

	public void setPrescriptions(List<Medication> prescriptions) {
		this.prescriptions = prescriptions;
	}

	// Adds this visit to a specific patient's record.
	public void addVisitToPatient(Patient patient) {
		patient.addVisit(this);
	}

	// Removes this visit from a patient's record.
	public void removeVisitFromPatient(Patient patient) {
		patient.removeVisit(this.visitID);
	}

	// Updates this visit's information in the patient's record.
	public void updateVisitInPatientRecord(Patient patient) {
		patient.updateVisit(this);
	}

	// Searches for a specific visit within a patient's record.
	public static Visits findVisitInPatientRecord(String visitID, Patient patient) {
		return patient.findVisit(visitID);
	}

	// This method would require a global context or an additional service to find a patient by visitID across all patients.
	public static Patient findPatientByVisitID(String visitID, List<Patient> patients) {
		for (Patient patient : patients) {
			if (patient.getVisits().stream().anyMatch(visit -> visit.getVisitID().equals(visitID))) {
				return patient;
			}
		}
		return null;
	}

	// Retrieves a summary of visits for a specific patient. This implementation will depend on how VisitSummary is defined.
	public static VisitSummary getVisitSummary(Patient patient) {
		return new VisitSummary(patient.getVisits());
	}

	// Updates this visit's information in the patient's record.
	public void updateVisit(Patient patient) {
		for (int i = 0; i < patient.getVisits().size(); i++) {
			if (patient.getVisits().get(i).getVisitID().equals(this.getVisitID())) {
				patient.getVisits().set(i, this);
				break;
			}
		}
	}

	// Updates the medical history in the patient's record.
	// This might involve adding new information to the most recent visit or creating a new visit entry.
	public static void updateMedicalHistory(Patient patient, String newInfo, Date timestamp, String vitals, List<Medication> prescriptions) {
		// Check if the patient has any visits
		if (!patient.getVisits().isEmpty()) {
			// Get the most recent visit
			Visits lastVisit = patient.getVisits().get(patient.getVisits().size() - 1);

			// For simplicity, let's assume we want to update the healthConcerns field of the most recent visit
			// This logic can be adjusted based on how you want to interpret updating medical history
			lastVisit.setHealthConcerns(lastVisit.getHealthConcerns() + " " + newInfo);

			// Update the visit in the patient's record
			patient.updateVisit(lastVisit);
		} else {
			// If there are no visits, create a new visit entry with the provided information
			Visits newVisit = new Visits(timestamp, vitals, newInfo, prescriptions);
			patient.addVisit(newVisit);
		}
	}

}