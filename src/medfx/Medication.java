package medfx;

import java.io.Serializable;
import java.util.Objects;

public class Medication implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;
	private String dosage;
	private String frequency; // e.g., "once a day", "every 6 hours"
	private int duration; // Duration in days
	private String sideEffects;
	private String purpose; // Reason for the medication

	// Constructor
	public Medication(String name, String dosage, String frequency, int duration, String sideEffects, String purpose) {
		this.name = name;
		this.dosage = dosage;
		this.frequency = frequency;
		this.duration = duration;
		this.sideEffects = sideEffects;
		this.purpose = purpose;
	}

	// Getters and Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDosage() {
		return dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getSideEffects() {
		return sideEffects;
	}

	public void setSideEffects(String sideEffects) {
		this.sideEffects = sideEffects;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	// Method to update medication details
	public void updateMedication(String dosage, String frequency, int duration, String sideEffects, String purpose) {
		this.dosage = dosage;
		this.frequency = frequency;
		this.duration = duration;
		this.sideEffects = sideEffects;
		this.purpose = purpose;
	}

	// Equals and hashCode for comparing medication objects
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Medication that = (Medication) o;
		return duration == that.duration &&
				Objects.equals(name, that.name) &&
				Objects.equals(dosage, that.dosage) &&
				Objects.equals(frequency, that.frequency) &&
				Objects.equals(sideEffects, that.sideEffects) &&
				Objects.equals(purpose, that.purpose);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, dosage, frequency, duration, sideEffects, purpose);
	}

	// String representation for easy reading and debugging
	@Override
	public String toString() {
		return "Medication{" +
				"name='" + name + '\'' +
				", dosage='" + dosage + '\'' +
				", frequency='" + frequency + '\'' +
				'}';
	}

	// Additional methods as needed for medication management...
}
