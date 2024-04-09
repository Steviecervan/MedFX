package medfx;

import java.io.File;
import java.io.FilenameFilter;
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
	
	
	/**
	 * Reads all the patients that are currently in the database and adds them to patientList
	 * This method will only be run at the beginning of the program to ensure that any patient made 
	 * in a different run of the application persists in this run
	 * @throws Exception
	 */
	public static void readAllPatientsFromDatabase() throws Exception
	{
		File patientDirectory = new File("PatientDatabase");
		// the Filenamefilter is an interface so you have to basically create a mini class to use it
		// this filter allows us to read each file from a directory if it is not the .gitignore file
		FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File f, String name)
			{
				return !name.equals(".gitkeep"); 
			}
		};
		
		File[] fileList = patientDirectory.listFiles(filter); // returns file list as an array so need to use and array
		
		// now read all patients from database if not empty (if size is 0 then loop doesn't run)
		for (int i = 0; i < fileList.length; i++)
		{
			int dotIndex = fileList[i].getName().indexOf('.'); // patient should not have '.' in name until extension
			String patientUsername = fileList[i].getName().substring(0, dotIndex); // gets the username from file name
			
			Patient currentPatient = Patient.readPatientFromDatabase(patientUsername); // reads the current file
			
			Doctor.addPatient(currentPatient); // adds this patient to the arraylist
		}
		
	}
}

// WE GOT THIS! 🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓