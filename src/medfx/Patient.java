package medfx;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Patient extends User {
	private static final long serialVersionUID = -1632230017250822490L;
	private PersonalInformation personalInfo;
	private ArrayList<Visits> visitInformation; // Updated to store Visit objects
	private ArrayList<Message> messages;
	private String insurance;
	private String pharmacy;

	// Constructor
	public Patient(PersonalInformation personalInfo, String role) {
		super(personalInfo, role); // Calls the constructor for User
		this.visitInformation = new ArrayList<Visits>(); // Instantiate an empty ArrayList for visit information
		this.messages = new ArrayList<Message>();
	}

	// Adds a new visit to the visit summaries for this patient
	public void addVisit(Visits visit) {
		if (visit != null) {
			visitInformation.add(visit);
		}
	}

	/*
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
	}*/


	// Retrieves all visits for this patient
	public ArrayList<Visits> getVisits() {
		return this.visitInformation;
	}

	// writing and reading patient objects
	public static void writePatientToDatabase(Patient patient) throws IOException
	{
		File patientDatabase = new File("PatientDatabase");

		FileOutputStream bytesToDisk = new FileOutputStream(new File(patientDatabase, patient.getUsername() + ".object"));	
		ObjectOutputStream objectToBytes = new ObjectOutputStream(bytesToDisk);
		
		objectToBytes.writeObject(patient);
		
		// close streams
		bytesToDisk.close();
		objectToBytes.close();
	}
	
	public static Patient readPatientFromDatabase(String patientId) throws Exception // used generic exception account for both ClassNotFoundException and FileNotFoundException
	{
		File patientFile = new File(new File("PatientDatabase"), patientId + ".object");
		
		FileInputStream fileIn = new FileInputStream(patientFile);
		ObjectInputStream in = new ObjectInputStream(fileIn);
		
		//Patient object
		Patient patient = (Patient) in.readObject();
		
		//close stream
		fileIn.close();
		in.close();
		
		return patient;
	}
	
	//	Message Functionality
	public ArrayList<Message> getMessages(){
		return this.messages;
	}
	
	//	Getters
	public String getInsurance() {
		if(insurance == null) {
			return "Information not provided";
		}
		return this.insurance;
	}
	
	public String getPharmacy() {
		if(pharmacy == null) {
			return "Information not provided";
		}
		return this.pharmacy;
	}
	
	//	Setters
	public void setInsurance(String newInsurance) {
		this.insurance = newInsurance;
	}
	
	public void setPharmacy(String newPharmacy) {
		this.pharmacy = newPharmacy;
	}
}
