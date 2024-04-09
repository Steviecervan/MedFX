package medfx;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Nurse extends User{
	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = -120582796748093181L;
	private String nurseId;		//	N.....
	private static ArrayList<Patient> patientList = new ArrayList<Patient>();

	public Nurse(PersonalInformation personalInfo) {
		//	Calls constructor for user
		super(personalInfo, "Nurse");
		this.nurseId = "N." + this.getUsername();
	}

	public String getNurseId() {
		return this.nurseId;
	}
	
	public static void addPatient(Patient p)
	{
		patientList.add(p);
	}
	
	public static ArrayList<Patient> getPatientList()
	{
		return patientList;
	}
	
	// writing and reading to/from database
	
	public static void writeNurseToDatabase(Nurse nurse) throws IOException
	{
		File nurseDatabase = new File("NurseDatabase");

		FileOutputStream bytesToDisk = new FileOutputStream(new File(nurseDatabase, nurse.getNurseId() + ".object"));	
		ObjectOutputStream objectToBytes = new ObjectOutputStream(bytesToDisk);
		
		objectToBytes.writeObject(nurse);
		
		// close streams
		bytesToDisk.close();
		objectToBytes.close();
	}
	
	public static Nurse readNurseFromDatabase(String nurseId) throws Exception
	{
		File nurseFile = new File(new File("NurseDatabase"), nurseId + ".object");
		
		FileInputStream fileIn = new FileInputStream(nurseFile);
		ObjectInputStream in = new ObjectInputStream(fileIn);
		
		//Patient object
		Nurse nurse = (Nurse) in.readObject();
		
		//close stream
		fileIn.close();
		in.close();
		
		return nurse;
	}
}
