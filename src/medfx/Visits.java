package medfx;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Visits implements Serializable {
	private static final long serialVersionUID = 1L;

	private String date;
	private double weight;
	private double height;
	private String bloodPressure;
	private double bodyTemp;
	private String physicalResults;
	private String healthConcerns;
	private Medication prescription;
	private String allergies;
	private String medHistory;

	// Constructor
	public Visits(String visitDate, double weight, double height, String bloodPressure, double bodyTemp, String physicalResults, String healthConcerns, Medication prescription,
			String allergies, String medHistory) {
		this.date = visitDate;
		this.weight = weight;
		this.height = height;
		this.bloodPressure = bloodPressure;
		this.bodyTemp = bodyTemp;
		this.physicalResults = physicalResults;
		this.healthConcerns = healthConcerns;
		this.prescription = prescription;
		this.allergies = allergies;
		this.medHistory = medHistory;
	}

	// Getters
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
	
	public String getDate() {
		return this.date;
	}
	
	public Medication getPrescription() {
		return this.prescription;
	}

	//	Setters
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
	
	public void setHealthConcerns(String healthConcerns) {
		this.healthConcerns = healthConcerns;
	}
	
	public void setPrescription(Medication prescription) {
		this.prescription = prescription;
	}
	
	public String getAllergies()
	{
		return this.allergies;
	}
	
	public String getMedicalHistory()
	{
		return this.medHistory;
	}
	
	public void setAllergies(String newAllergy)
	{
		this.allergies = newAllergy;
	}
	
	public void setMedicalHistory(String newHistory)
	{
		this.medHistory = newHistory;
	}
}