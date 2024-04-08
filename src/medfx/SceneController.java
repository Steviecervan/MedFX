package medfx;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

public class SceneController 
{	
	private static final int LENGTH = 800;
	private static final int WIDTH = 500;
	
	/**
	 * Switches the current page to the CreateAccountPage
	 * This is a STATIC method, all the methods in this class will be static
	 * This means the way you would use this method in your code would be SceneController.switchToCreateAccountPage(e)
	 * We do not need to make a SceneController object.
	 * 
	 * @param e ActionEvent which triggers screen switch
	 * @throws IOException
	 */
	public static void switchToCreateAccountPage(ActionEvent e) throws IOException // needs to throw IOException because of the image in create account page, if you don't use the image might not be needed
	{
		Parent root = new CreateAccountPage(); // Parent is the parent class for any screen in JavaFX
		Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow(); //gets the window where the button is being pressed using the ActionEvent (like a button press or something)
		Scene scene = new Scene(root, LENGTH, WIDTH); //creates a new scene
		stage.setScene(scene); //changes the stage 
		stage.show(); //shows new stage
	}
	
	/**
	 * Switches the current page to the UserMainPage
	 * @param e ActionEvent which triggers screen switch
	 * @throws IOException
	 */
	public static void switchToUserMainPage(ActionEvent e) throws IOException // needs to throw IOException because of the image in create account page, if you don't use the image might not be needed
	{
		Parent root = new UserMainPage(); // Parent is the parent class for any screen in JavaFX
		Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow(); //gets the window where the button is being pressed using the ActionEvent (like a button press or something)
		Scene scene = new Scene(root, LENGTH, WIDTH); //creates a new scene
		stage.setScene(scene); //changes the stage 
		stage.show(); //shows new stage
	}
	
	/**
	 * Switches the current page to the PatientViewPage
	 * NOTE: MAY NEED TO REMOVE THROWS IOEXCEPTION IF IMAGE IS REMOVED
	 * @param e ActionEvent which triggers screen switch
	 * @throws IOException
	 */
	public static void switchToPatientView(ActionEvent e, Patient p) 
	{
		Parent root = new PatientViewPage(p); // Parent is the parent class for any screen in JavaFX
		Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow(); //gets the window where the button is being pressed using the ActionEvent (like a button press or something)
		Scene scene = new Scene(root, LENGTH, WIDTH); //creates a new scene
		stage.setScene(scene); //changes the stage 
		stage.show(); //shows new stage
	}
	
	public static void switchToDoctorView(ActionEvent e, Doctor doctor) throws IOException // needs to throw IOException because of the image in create account page, if you don't use the image might not be needed
	{
		Parent root = new DoctorViewPage(doctor); // Parent is the parent class for any screen in JavaFX
		Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow(); //gets the window where the button is being pressed using the ActionEvent (like a button press or something)
		Scene scene = new Scene(root, LENGTH, WIDTH); //creates a new scene
		stage.setScene(scene); //changes the stage 
		stage.show(); //shows new stage
	}
	
	public static void switchToNurseView(ActionEvent e, Nurse nurse) throws IOException // needs to throw IOException because of the image in create account page, if you don't use the image might not be needed
	{
		Parent root = new NurseViewPage(nurse); // Parent is the parent class for any screen in JavaFX
		Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow(); //gets the window where the button is being pressed using the ActionEvent (like a button press or something)
		Scene scene = new Scene(root, LENGTH, WIDTH); //creates a new scene
		stage.setScene(scene); //changes the stage 
		stage.show(); //shows new stage
	}
	
	/**
	 * To create your own method to switch screens just copy and past the method above, change the name,
	 * and then change the Parent root = new CreateAccountPage(); line to instantiate whatever page you want to switch to
	 * Make sure to remove the throws clause if your constructor for your page doesn't have to handle exceptions like mine
	 */
	
	
}

