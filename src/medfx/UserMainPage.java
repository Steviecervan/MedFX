package medfx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream; 

public class UserMainPage extends VBox
{	
	private TextField username;
	private Button submitBtn;
	private Button createAcntBtn;
	
	// pane used for username input
	private VBox inputUsernamePane;
	
	private VBox errorPane;
	
	public UserMainPage() throws IOException
	{
		// set css style sheet
		this.getStylesheets().add(getClass().getResource("application.css").toString());
		this.getStyleClass().add("BasicPaneSetUp");
		// loads image from file and then creates an image view to add to scene
		FileInputStream imagePath = new FileInputStream("MedFxLogo.png"); // will read the file that contains the image, may throw FileNotFoundException
		Image medFXPng = new Image(imagePath); // creates Image object out of image path
		imagePath.close(); // close stream
		ImageView logoView = new ImageView(medFXPng); // need to create an ImageView object because you cannot add an image directly to the scene
		// Use the ImageView object to resize image
		logoView.setFitHeight(150); 
	    logoView.setFitWidth(455);           
	    //Setting the preserve ratio of the image view 
	    logoView.setPreserveRatio(true); 
	    
		
		Label instructions = new Label("Enter your username as: LastName-FirstName-MM-DD-YYYY");
		username = new TextField("Username");
		username.setMaxWidth(300); // prevents textfield from spanning whole window
		username.getStyleClass().add("GrayTextField");
		submitBtn = new Button("Submit");
		submitBtn.getStyleClass().add("BlueButton");
		
		// create username input pane and add corresponding nodes
		inputUsernamePane = new VBox();
		inputUsernamePane.setAlignment(Pos.CENTER);
		inputUsernamePane.setSpacing(25);
		inputUsernamePane.getChildren().addAll(instructions, username, submitBtn);
		
		createAcntBtn = new Button("Create Account");
		createAcntBtn.getStyleClass().add("WhiteButton");
		
		// set buttonhandlers
		submitBtn.setOnAction(new ButtonHandler());
		createAcntBtn.setOnAction(new ButtonHandler());
		
		// set errorPane
		errorPane = new VBox();
		errorPane.setAlignment(Pos.CENTER);
		
		this.setAlignment(Pos.CENTER); // this will also center the image
		this.setSpacing(50);
		this.getChildren().addAll(logoView, inputUsernamePane, createAcntBtn, errorPane);
	}
	
	private class ButtonHandler implements EventHandler<ActionEvent>
	{
		public void handle(ActionEvent e)
		{
			// here is where the scene controller will change screens depending on which button is pressed
			if (submitBtn.isArmed())
			{
				errorPane.getChildren().clear(); // clears errorPane
				String inputUsername = username.getText();
				// makes sure a user name was input
				if (inputUsername.equals("Username"))
				{
					Label error = new Label("Make sure you input your username! If you don't have one press the Create Account button.");
					error.setStyle("-fx-text-fill: red");
					errorPane.getChildren().add(error); // displays error to user
				}
				else if (inputUsername.substring(0, 2).equals("D.")) // the user is a doctor
				{
					// try to read the doctor file
					try
					{
						Doctor doctor = Doctor.readDoctorFromDatabase(inputUsername); // reads the doctor file object
						
						SceneController.switchToDoctorView(e, doctor); // doesn't throw an error so do not need try and catch
					}
					catch (Exception ex)
					{
						Label error = new Label("No doctor with that username exists, make sure you input it correctly.");
						error.setStyle("-fx-text-fill: red");
						errorPane.getChildren().add(error); // displays error to user
					}

				}
				else if (inputUsername.substring(0, 2).equals("N.")) // user is a nurse
				{
					// try to read the doctor file
					try
					{
						Nurse nurse = Nurse.readNurseFromDatabase(inputUsername);// reads the doctor file object
						
						SceneController.switchToNurseView(e, nurse);
					}
					catch (Exception ex)
					{
						Label error = new Label("No nurse with that username exists, make sure you input it correctly.");
						error.setStyle("-fx-text-fill: red");
						errorPane.getChildren().add(error); // displays error to user
					}
				}
				else // user is a patient
				{

					// try to read the patient file
					try
					{
						Patient currentPatient = Patient.readPatientFromDatabase(inputUsername); // reads the patient file object
						
						SceneController.switchToPatientView(e, currentPatient); // doesn't throw an error so do not need try and catch
					}
					catch (Exception ex)
					{
						Label error = new Label("No patient with that username exists, make sure you input it correctly.");
						error.setStyle("-fx-text-fill: red");
						errorPane.getChildren().add(error); // displays error to user
					}

						
					
				}
				
			}
			else if (createAcntBtn.isArmed())
			{
				//Changes the screen to the CreateAccountPage
				try
				{
					SceneController.switchToCreateAccountPage(e); // This method can possibly throw an IOException because of the Image
				}
				catch (IOException ex)
				{
					// do nothing if there is an exception, as long as the image file does not move, there should be no error
				}
			}
		}
	}
}
