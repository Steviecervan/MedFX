package medfx;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;

public class Doctor extends User{
	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = -140347943225749714L;
	private String docId;	//	D.....
	private static ArrayList<Patient> patientList = new ArrayList<Patient>();
	
	public Doctor(PersonalInformation personalInfo) {
		//	Calls constructor for User
		super(personalInfo, "Doctor");
		// generate doctor id
		docId = "D." + this.getUsername();
	}
	
	public String getDoctorId() {
		return this.docId;
	}
	
	public static void addPatient(Patient p)
	{
		patientList.add(p);
	}
	
	public static ArrayList<Patient> getPatientList()
	{
		return patientList;
	}
	
	public static void writeDoctorToDatabase(Doctor doctor) throws IOException
	{
		File doctorDatabase = new File("DoctorDatabase");

		FileOutputStream bytesToDisk = new FileOutputStream(new File(doctorDatabase, doctor.getDoctorId() + ".object"));	
		ObjectOutputStream objectToBytes = new ObjectOutputStream(bytesToDisk);
		
		objectToBytes.writeObject(doctor);
		
		// close streams
		bytesToDisk.close();
		objectToBytes.close();
	
	}
	
	public static Doctor readDoctorFromDatabase(String doctorId) throws Exception
	{
		File doctorFile = new File(new File("DoctorDatabase"), doctorId + ".object");
		
		FileInputStream fileIn = new FileInputStream(doctorFile);
		ObjectInputStream in = new ObjectInputStream(fileIn);
		
		//Patient object
		Doctor doctor = (Doctor) in.readObject();
		
		//close stream
		fileIn.close();
		in.close();
		
		return doctor;
	}
}

// WE GOT THIS! 🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓