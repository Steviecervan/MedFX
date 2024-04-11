package medfx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class System extends Application{
	
	public void start(Stage primaryStage) throws Exception // Exception because of the fileinputstream in user main page and writeobject methods
	{
		/**
		 * Create Doctor and Nurse object
		 */
		PersonalInformation docInfo = new PersonalInformation("John", "Doe", "123 N. Apache Dr., Tempe, AZ 85281", "johndoe@gmail.com", "01-01-1972", "123-456-7890");
		Doctor doctor = new Doctor(docInfo);
		
		PersonalInformation nurseInfo = new PersonalInformation("Jane", "Doe", "123 N. University Dr., Tempe, AZ 85281", "janedoe@gmail.com", "02-01-1972", "987-654-3210");
		Nurse nurse = new Nurse(nurseInfo);
		
		//write to database
		Doctor.writeDoctorToDatabase(doctor);
		Nurse.writeNurseToDatabase(nurse);
		
		//read current patients from database
		Patient.readAllPatientsFromDatabase();
		
		StackPane root = new StackPane();
        VBox mainMenu = new UserMainPage(); // this is what the main screen will be called, you can change it to fit your testing purposes
        root.getChildren().add(mainMenu);

        Scene scene = new Scene(root, 800, 500);
        primaryStage.setTitle("MedFX");
        primaryStage.setScene(scene);
        primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
