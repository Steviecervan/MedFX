package medfx;

import java.io.FileInputStream;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CreateAccountPage extends VBox
{
	private VBox userInputPane;
	private HBox birthDatePane;
	private TextField firstName;
	private TextField lastName;
	private TextField month;
	private TextField day;
	private TextField year;
	private Button createAcntBtn;
	
	public CreateAccountPage() throws IOException
	{
		//set default css pane setup
		this.getStylesheets().add(getClass().getResource("application.css").toString()); // fetches the style sheet
		this.getStyleClass().add("BasicPaneSetUp");
		
		// add image
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
	    
	    // first and last name text fields
	    firstName = new TextField("First Name");
	    firstName.setMaxWidth(480);
	    lastName = new TextField("Last Name");
	    lastName.setMaxWidth(480);
	    
	    // birthDate pane
	    birthDatePane = new HBox();
	    birthDatePane.setSpacing(15);
	    birthDatePane.setAlignment(Pos.CENTER);
	    month = new TextField("MM");
	    day = new TextField("DD");
	    year = new TextField("YYYY");
	    //add to birthdate pane
	    birthDatePane.getChildren().addAll(month, day, year);
	    
	    //setting css classes for each text field
	    firstName.getStyleClass().add("GrayTextField");
	    lastName.getStyleClass().add("GrayTextField");
	    month.getStyleClass().add("GrayTextField");
	    day.getStyleClass().add("GrayTextField");
	    year.getStyleClass().add("GrayTextField");
	    
	    // specific resizing for the birthdate textfields
	    month.setStyle("-fx-pref-column-count: 2");
	    day.setStyle("-fx-pref-column-count: 2");
	    year.setStyle("-fx-pref-column-count: 4");
	    
	    //create account button
	    createAcntBtn = new Button("Create Account");
	    createAcntBtn.getStyleClass().add("BlueButton");
	    createAcntBtn.setOnAction(new ButtonHandler());
	    
	    // user input pane
	    userInputPane = new VBox();
	    userInputPane.setAlignment(Pos.CENTER);
	    userInputPane.setSpacing(10);
	    userInputPane.getChildren().addAll(firstName, lastName, birthDatePane);
	    
	    
	    
	    this.setAlignment(Pos.CENTER);
	    this.setSpacing(30);
	    this.getChildren().addAll(logoView, userInputPane, createAcntBtn);
	}
	
	private class ButtonHandler implements EventHandler<ActionEvent>
	{
		public void handle(ActionEvent e)
		{
			if (createAcntBtn.isArmed())
			{
				// TODO: implement create account button functionality
			}
		}
	}
}
