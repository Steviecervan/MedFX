//	Author: Stevie Cervantes (idk if we're doing this)

package medfx;

import java.io.FileInputStream;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class PatientViewPage extends VBox{
	private Label pName;
	private Label pBirthDate;
	private Label pAddress;
	private Label pPhoneNumber;
	private Label pEmail;
	private Label pInsurance;
	private Label pPharmacy; 
	private VBox patientInfoContainer;
	
	public PatientViewPage() throws IOException {	
		//	Set CSS style sheet
		this.getStylesheets().add(getClass().getResource("application.css").toString());
		this.getStyleClass().add("BasicPaneSetUp");
		
		// loads image from file and then creates an image view to add to scene
		FileInputStream imagePath = new FileInputStream("MedFxLogo.png"); // will read the file that contains the image, may throw FileNotFoundException
		Image medFXPng = new Image(imagePath); // creates Image object out of image path
		imagePath.close(); // close stream
		ImageView logoView = new ImageView(medFXPng); // need to create an ImageView object because you cannot add an image directly to the scene
		
		// Use the ImageView object to resize image
		logoView.setFitHeight(30); 
		logoView.setFitWidth(335);           
		
		//Setting the preserve ratio of the image view 
		logoView.setPreserveRatio(true);		
		
		//	General View -----------------------------------------------------------------------------------------
		//	This view is static
		Label welcomeHeaderLabel = new Label("Welcome, " + pName);
		//welcomeHeaderLabel.getStyleClass().add("HeaderText");
		welcomeHeaderLabel.setPadding(new Insets(5, 0, 5, 15));
		welcomeHeaderLabel.setStyle("-fx-font-size: 25; -fx-font-family: 'Roboto'; -fx-font-weight: bold");
		
		Button signOutButton = new Button("Sign Out");
		signOutButton.getStyleClass().add("WhiteButton");
		
		HBox topContainer = new HBox();
		topContainer.setAlignment(Pos.CENTER_LEFT);
		topContainer.setSpacing(593);
		topContainer.setStyle("-fx-background-color: #39C0EA; -fx-pref-height: 50; -fx-padding: 15");
		topContainer.getChildren().addAll(logoView, signOutButton);
		
		HBox displayContainer = new HBox();
		displayContainer.setPadding(new Insets(0,15,15,15));
		displayContainer.setSpacing(15);
		displayContainer.setPrefHeight(445);
		
		VBox navigationContainer = new VBox();
		navigationContainer.setPrefWidth(200);
		//navigationContainer.setSpacing();
		navigationContainer.setStyle("-fx-background-color: whitesmoke; -fx-padding: 8");
		
		patientInfoContainer = new VBox();
		patientInfoContainer.setMinWidth(600);
		patientInfoContainer.setStyle("-fx-background-color: whitesmoke; -fx-padding: 8");
		
		Button myInfoButton = new Button("My Info");
		myInfoButton.getStyleClass().add("NavigationButton");
		
		Button myVisitsButton = new Button("My Visits");
		myVisitsButton.getStyleClass().add("NavigationButton");
		
		Button messagesButton = new Button("Messages");
		messagesButton.getStyleClass().add("NavigationButton");
		
		navigationContainer.getChildren().addAll(myInfoButton, myVisitsButton, messagesButton);
		
		//	Info View -----------------------------------------------------------------------------------------
		//	This view shows the patients information
		
		//	Labels (does not have user information)
		Label infoHeaderLabel = new Label("My Info");
		infoHeaderLabel.getStyleClass().add("HeaderText");
		
		Label nameLabel = new Label("Name");
		Label birthDateLabel = new Label("Birth date");
		Label addressLabel = new Label("Address");
		Label phoneNumberLabel = new Label("Phone Number");
		Label emailLabel = new Label("Email Address");
		Label insuranceLabel = new Label("Insurance");
		Label pharmacyLabel = new Label("Preferred Pharmacy");
		
		//	Labels (where user information goes)
		pName = new Label("Stevie Marie Cervantes");
		pBirthDate = new Label("06/03/2004");
		pAddress = new Label("1151 S Forest Ave, Tempe, AZ");
		pPhoneNumber = new Label("602-303-2033");
		pEmail = new Label("smcerva8@asu.edu");
		pInsurance = new Label("United Healthcare");
		pPharmacy = new Label("Walgreens");
		
		//	Buttons to change data
		Button addressButton = new Button("Change");
		addressButton.getStyleClass().add("WhiteButton");
		addressButton.setStyle("-fx-font-weight: normal; -fx-font-size: 12; -fx-background-color: transparent");
		
		Button phoneNumberButton = new Button("Change");
		phoneNumberButton.getStyleClass().add("WhiteButton");
		phoneNumberButton.setStyle("-fx-font-weight: normal; -fx-font-size: 12; -fx-background-color: transparent");
		
		Button emailButton = new Button("Change");
		emailButton.getStyleClass().add("WhiteButton");
		emailButton.setStyle("-fx-font-weight: normal; -fx-font-size: 12; -fx-background-color: transparent");
		
		Button pharmacyButton = new Button("Change");
		pharmacyButton.getStyleClass().add("WhiteButton");
		pharmacyButton.setStyle("-fx-font-weight: normal; -fx-font-size: 12; -fx-background-color: transparent");
		
		//	Information grid pane
		GridPane infoGridPane = new GridPane();
		infoGridPane.setAlignment(Pos.CENTER_LEFT);
		infoGridPane.setVgap(5);
		infoGridPane.setHgap(25);
		
		//	Add labels to grid pane
		infoGridPane.add(nameLabel, 0, 0);
		infoGridPane.add(pName, 1, 0);
		infoGridPane.add(birthDateLabel, 0, 1);
		infoGridPane.add(pBirthDate, 1, 1);
		infoGridPane.add(addressLabel, 0, 2);
		infoGridPane.add(pAddress, 1, 2);
		infoGridPane.add(addressButton, 2, 2);
		infoGridPane.add(phoneNumberLabel, 0, 3);
		infoGridPane.add(pPhoneNumber, 1, 3);
		infoGridPane.add(phoneNumberButton, 2, 3);
		infoGridPane.add(emailLabel, 0, 4);
		infoGridPane.add(pEmail, 1, 4);
		infoGridPane.add(emailButton, 2, 4);
		infoGridPane.add(insuranceLabel, 0, 5);
		infoGridPane.add(pInsurance, 1, 5);
		infoGridPane.add(pharmacyLabel, 0, 6);
		infoGridPane.add(pPharmacy, 1, 6);
		infoGridPane.add(pharmacyButton, 2, 6);
		
		//	Visits View -----------------------------------------------------------------------------------------
		//	This view shows the patient's visits
		
		//	Labels
		Label visitsHeaderLabel = new Label("My Visits");
		visitsHeaderLabel.getStyleClass().add("HeaderText");
		
		//	Examination Container (template)
		//	When implementing the database, we can use this template for each visit object
		HBox examinationContainer = new HBox();		
		examinationContainer.setPadding(new Insets(5, 0, 0, 0));
		examinationContainer.setAlignment(Pos.CENTER_LEFT);
		
		Label examinationDateLabel = new Label("Examination Date: MM/DD/YYYY");
		examinationDateLabel.setMinWidth(505);
		
		Button summaryButton = new Button("Summary");
		summaryButton.getStyleClass().add("WhiteButton");
		summaryButton.setStyle("-fx-background-color: transparent; -fx-font-weight: normal; -fx-font-size: 12");
				
		//	Action Event for showing the summary of a visit
		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				Stage summaryWindow = new Stage();
				summaryWindow.setWidth(600);
				summaryWindow.setHeight(400);
				
				//	Display Information
				VBox summaryContainer = new VBox();
				summaryContainer.setPadding(new Insets(15, 15, 15, 15));
				
				Label visitDateLabel = new Label("Visit MM-DD-YYYY");
				visitDateLabel.setStyle("-fx-font-family: roboto; -fx-font-weight: bold; -fx-font-size: 20; -fx-padding: 0, 0, 0, 20");
				
				Label vitalResultsLabel = new Label("Vital Results");
				vitalResultsLabel.setStyle("-fx-font-family: roboto; -fx-font-weight: bold; -fx-font-size: 15; -fx-padding: 0, 5, 0, 5");
								
				Label healthConcernsLabel = new Label("Health Concerns");
				healthConcernsLabel.setStyle("-fx-font-family: roboto; -fx-font-weight: bold; -fx-font-size: 15; -fx-padding: 0, 5, 0, 5");
				
				Label physicalResultsLabel = new Label("Physical Results");
				physicalResultsLabel.setStyle("-fx-font-family: roboto; -fx-font-weight: bold; -fx-font-size: 15; -fx-padding: 0, 5, 0, 5");
				
				//	Vital Results
				Label weightLabel = new Label("Weight");
				Label heightLabel = new Label("Height");
				Label bodyTemperatureLabel = new Label("Body Temperature");
				Label bloodPressureLabel = new Label("Blood Pressure");
				Label pWeight = new Label("");
				Label pHeight= new Label("");
				Label pBodyTemp= new Label("");
				Label pBloodPressure = new Label("");
				
				TextArea healthConcernTextArea = new TextArea();				
				healthConcernTextArea.setEditable(false);
				
				TextArea physicalResultsTextArea = new TextArea();
				physicalResultsTextArea.setEditable(false);
				
				GridPane vitalsGridPane = new GridPane();
				vitalsGridPane.setAlignment(Pos.CENTER_LEFT);
				vitalsGridPane.setVgap(5);
				vitalsGridPane.setHgap(25);
				
				vitalsGridPane.add(weightLabel, 0, 0);
				vitalsGridPane.add(pWeight, 1, 0);
				vitalsGridPane.add(heightLabel, 0, 1);
				vitalsGridPane.add(pHeight, 1, 1);
				vitalsGridPane.add(bodyTemperatureLabel, 0, 2);
				vitalsGridPane.add(pBodyTemp, 1, 2);
				vitalsGridPane.add(bloodPressureLabel, 0, 3);
				vitalsGridPane.add(pBloodPressure, 1, 3);

				summaryContainer.getChildren().addAll(visitDateLabel, vitalResultsLabel, vitalsGridPane, healthConcernsLabel, healthConcernTextArea, physicalResultsLabel, physicalResultsTextArea);
				
				//	Add everything together
				Scene scene = new Scene(summaryContainer);
				summaryWindow.setScene(scene);
				
				summaryWindow.show();
			}
		};
		
		//	Sets summary buttons click event
		summaryButton.setOnAction(event);
		
		//	Adds the visits to the container. The examinationContainer will be the parent container
		//	to display all the visits.
		examinationContainer.getChildren().addAll(examinationDateLabel, summaryButton);

		//	Messages View -----------------------------------------------------------------------------------------
		VBox messageViewContainer = new VBox();
		messageViewContainer.setSpacing(5);
		
		VBox messageBox = new VBox();
		messageBox.setPrefHeight(400);
		
		HBox inputBox = new HBox();
		inputBox.setSpacing(5);
		
		TextField pTextInput = new TextField();
		pTextInput.setPrefWidth(550);
		
		Button sendButton = new Button("^");
		sendButton.getStyleClass().add("BlueButton");
		
		inputBox.getChildren().addAll(pTextInput, sendButton);
		messageViewContainer.getChildren().addAll(messageBox, inputBox);
		
		//	Info view: infoHeaderLabel, infoGridPane
		//	Visits view: visitsHeaderLabel, examinationContainer
		//	Message View: messageViewContainer
		
		//	Add everything to parents
		patientInfoContainer.getChildren().addAll(infoHeaderLabel, infoGridPane);
		displayContainer.getChildren().addAll(navigationContainer, patientInfoContainer);
		this.getChildren().addAll(topContainer,welcomeHeaderLabel, displayContainer);
	}
}
