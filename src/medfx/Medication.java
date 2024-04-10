package medfx;

import java.io.Serializable;
import java.util.Objects;

public class Medication implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;
	private String strength;
	private String dosage;

	// Constructor
	public Medication(String name, String dosage, String strength) {
		this.name = name;
		this.dosage = dosage;
		this.strength = strength;
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
	
	public String getStrength()
	{
		return this.strength;
	}
	
	public void setStrength(String strength)
	{
		this.strength = strength;
	}


	// String representation for easy reading and debugging
	@Override
	public String toString() {
		String result = this.name + " " + this.strength + "\n" + this.dosage;
		return result;
	}

	// Additional methods as needed for medication management...
}
