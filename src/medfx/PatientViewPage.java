//	Author: Stevie Cervantes

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
	private Patient patient;
		
	private VBox patientInfoContainer;
	private TextArea textInputArea;
	private VBox messageViewContainer;
	
	private Button addressButton;
	private Button phoneNumberButton;
	private Button emailButton;
	private Button pharmacyButton;
	
	private String data;
	private TextField newDataTextField;
	
	public PatientViewPage(Patient patient){
		//set default css pane setup
		this.getStylesheets().add(getClass().getResource("application.css").toString()); // fetches the style sheet
		this.getStyleClass().add("BasicPaneSetUp");
		
		// Patient object
		this.patient = patient;
		
		//	General View -----------------------------------------------------------------------------------------
		//	This remains the same throughout the use of this view		
		
		//	Top Container
		HBox topContainer = new HBox();
		topContainer.setAlignment(Pos.CENTER_LEFT);
		topContainer.setSpacing(593);
		topContainer.setStyle("-fx-background-color: #39C0EA; -fx-pref-height: 50; -fx-padding: 10");

		//	Text Logo
		Label medfxLabel = new Label("MedFX");
		medfxLabel.setStyle("-fx-font-size: 27; -fx-font-family: 'Roboto'; -fx-font-weight: bold; -fx-text-fill: white");
		
		//	Sign out button
		Button signOutButton = new Button("Sign Out");
		signOutButton.getStyleClass().add("WhiteButton");

		//	Add medFxLabel and sign out button to top container
		topContainer.getChildren().addAll(medfxLabel, signOutButton);
		
		//	Display Container
		HBox displayContainer = new HBox();
		displayContainer.setPadding(new Insets(0,15,15,15));
		displayContainer.setSpacing(15);
		displayContainer.setPrefHeight(445);
		
		//	Navigation Container
		//	Holds the buttons to navigate the different views
		VBox navigationContainer = new VBox();
		navigationContainer.setPrefWidth(200);
		navigationContainer.setStyle("-fx-background-color: whitesmoke; -fx-padding: 8");

		//	Welcome Label
		Label welcomeHeaderLabel = new Label("Welcome, " + patient.getPersonalInfo().getFirstName());
		welcomeHeaderLabel.getStyleClass().add("HeaderText");
		welcomeHeaderLabel.setPadding(new Insets(5, 0, 5, 15));
		welcomeHeaderLabel.setStyle("-fx-font-size: 25; -fx-font-family: 'Roboto'; -fx-font-weight: bold; -fx-padding: 15, 0, 0, 0");
		
		//	PatientInfoContainer
		patientInfoContainer = new VBox();
		patientInfoContainer.setMinWidth(600);
		patientInfoContainer.setStyle("-fx-background-color: whitesmoke; -fx-padding: 8");
		
		//	Navigation functionality for each button
		Button myInfoButton = new Button("My Info");
		myInfoButton.getStyleClass().add("NavigationButton");
		myInfoButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				patientInfoContainer.getChildren().remove(0);
				patientInfoContainer.getChildren().add(setToInfoView());
			}
		});
		
		Button myVisitsButton = new Button("My Visits");
		myVisitsButton.getStyleClass().add("NavigationButton");
		myVisitsButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				patientInfoContainer.getChildren().remove(0);
				patientInfoContainer.getChildren().add(setToVisitsView());
			}
		});
		
		Button messagesButton = new Button("Messages");
		messagesButton.getStyleClass().add("NavigationButton");
		messagesButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				patientInfoContainer.getChildren().remove(0);
				patientInfoContainer.getChildren().add(setToMessageView());
			}
		});
		
		//	Put navigation buttons into navigationContainer
		navigationContainer.getChildren().addAll(myInfoButton, myVisitsButton, messagesButton);
		
		//	PatientInfoContainer contains the changing views for the patient
		//	DisplayContainer contains the navigationContainer and patientInfoContainer
		//	Add PatientInfoContainer and DisplayContainer to be children of the class
		//	PatientInfoContainer defaults to myInfoView
		patientInfoContainer.getChildren().addAll(setToInfoView());
		displayContainer.getChildren().addAll(navigationContainer, patientInfoContainer);
		this.getChildren().addAll(topContainer,welcomeHeaderLabel, displayContainer);
	}
	
	//	Info View -----------------------------------------------------------------------------------------
	//	This view shows the patients information
	public VBox setToInfoView() {
		//	Main container that gets returned to set the info view
		VBox mainContainer = new VBox();
		
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
		pName = new Label();
		pBirthDate = new Label();
		pAddress = new Label();
		pPhoneNumber = new Label();
		pEmail = new Label();
		pInsurance = new Label();
		pPharmacy = new Label();
		
		//	Fills in above labels with patient information
		pName.setText(patient.getPersonalInfo().getFirstName() + " " + patient.getPersonalInfo().getLastName());
		pBirthDate.setText(patient.getPersonalInfo().getBirthDate());
		pAddress.setText(patient.getPersonalInfo().getAddress());
		pPhoneNumber.setText(patient.getPersonalInfo().getPhoneNumber());
		pEmail.setText(patient.getPersonalInfo().getEmail());
		pInsurance.setText(patient.getInsurance());
		pPharmacy.setText(patient.getPharmacy());
		
		//	Buttons to change data
		addressButton = new Button("Change");
		addressButton.getStyleClass().add("WhiteButton");
		addressButton.setStyle("-fx-font-weight: normal; -fx-font-size: 12; -fx-background-color: transparent");
		addressButton.setOnAction(new ButtonHandler());
		
		phoneNumberButton = new Button("Change");
		phoneNumberButton.getStyleClass().add("WhiteButton");
		phoneNumberButton.setStyle("-fx-font-weight: normal; -fx-font-size: 12; -fx-background-color: transparent");
		phoneNumberButton.setOnAction(new ButtonHandler());
		
		emailButton = new Button("Change");
		emailButton.getStyleClass().add("WhiteButton");
		emailButton.setStyle("-fx-font-weight: normal; -fx-font-size: 12; -fx-background-color: transparent");
		emailButton.setOnAction(new ButtonHandler());
		
		pharmacyButton = new Button("Change");
		pharmacyButton.getStyleClass().add("WhiteButton");
		pharmacyButton.setStyle("-fx-font-weight: normal; -fx-font-size: 12; -fx-background-color: transparent");
		pharmacyButton.setOnAction(new ButtonHandler());
		
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
		
		//	Add the info view components to the mainContainer
		mainContainer.getChildren().addAll(infoHeaderLabel, infoGridPane);
		
		//	Return the VBox w/ infoViewLabel and infoGridPane
		return mainContainer;
	}
	
	//	Visits View -----------------------------------------------------------------------------------------
	//	This view shows the patient's visits
	public VBox setToVisitsView() {
		//	Main Container that gets returned for the visit view
		VBox mainContainer = new VBox();
		
		//	Labels
		Label visitsHeaderLabel = new Label("My Visits");
		visitsHeaderLabel.getStyleClass().add("HeaderText");
		
		mainContainer.getChildren().add(visitsHeaderLabel);
		
		//	Add Each Visit the Patient Contains
		for(Visits visit : patient.getVisits()) {
			//	Holds all of the visit information together
			HBox examinationContainer = new HBox();		
			examinationContainer.setPadding(new Insets(5, 0, 0, 0));
			examinationContainer.setAlignment(Pos.CENTER_LEFT);
			
			//	Visit date Label
			Label examinationDateLabel = new Label("Examination Date:" + visit.getTimestamp());
			examinationDateLabel.setMinWidth(505);
			
			//	Visit Summary Button
			Button summaryButton = new Button("Summary");
			summaryButton.getStyleClass().add("WhiteButton");
			summaryButton.setStyle("-fx-background-color: transparent; -fx-font-weight: normal; -fx-font-size: 12");
			
			//	Adds the header label and examinationContainer to the mainContainer to be returned
			mainContainer.getChildren().add(visitsHeaderLabel);
			
			//	Action Event for showing the summary of a visit		
			summaryButton.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					Stage summaryWindow = new Stage();
					summaryWindow.setWidth(600);
					summaryWindow.setHeight(400);
					
					//	Display Information
					VBox summaryContainer = new VBox();
					summaryContainer.setPadding(new Insets(15, 15, 15, 15));
					
					Label visitDateLabel = new Label("Visit" + visit.getTimestamp());
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
					Label pWeight = new Label(String.valueOf(visit.getWeight()));
					Label pHeight= new Label(String.valueOf(visit.getHeight()));
					Label pBodyTemp= new Label(String.valueOf(visit.getBodyTemperature()));
					Label pBloodPressure = new Label(String.valueOf(visit.getBloodPressure()));
					
					TextArea healthConcernTextArea = new TextArea();				
					healthConcernTextArea.setEditable(false);
					healthConcernTextArea.setText(visit.getHealthConcerns());
					
					TextArea physicalResultsTextArea = new TextArea();
					physicalResultsTextArea.setEditable(false);
					physicalResultsTextArea.setText(visit.getPhysicalResults());
					
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
					
					//	Add everything together to create the Summary container
					Scene scene = new Scene(summaryContainer);
					summaryWindow.setScene(scene);
					
					summaryWindow.show();
				}
			});
			
			//	Adds the visits to the container. The examinationContainer will be the parent container
			//	to display all the visits.
			examinationContainer.getChildren().addAll(examinationDateLabel, summaryButton);
			
			//	Adds the examinationContainer to the mainContainer
			mainContainer.getChildren().add(examinationContainer);
		}
		
		return mainContainer;
	}
	
	//	Messages View -----------------------------------------------------------------------------------------
	public VBox setToMessageView() {
//		Main Container
			VBox mainContainer = new VBox();

			//	Container that holds the message bubbles
			messageViewContainer = new VBox();
			messageViewContainer.setPadding(new Insets(0, 0, 10, 0));
			messageViewContainer.setSpacing(5);
			messageViewContainer.setMinHeight(295);
			messageViewContainer.setAlignment(Pos.BOTTOM_RIGHT);
			
			//	-- Text Input Container --
			HBox textInputContainer = new HBox();
			textInputContainer.getStyleClass().add("TextInputContainer");
			
			//	TextField - message input
			textInputArea = new TextArea();
			textInputArea.getStyleClass().add("TextInputArea");
			textInputArea.setMaxWidth(535);
			
			//	Send Message Button
			Button sendButton = new Button("^");
			sendButton.getStyleClass().add("SendButton");
			sendButton.setMinWidth(30);
			sendButton.setMinHeight(30);
			sendButton.setOnAction(new EventHandler<ActionEvent> () {
				public void handle(ActionEvent e) {				
					//	~ Message bubble (Sender) ~
					VBox messageBubbleContainerSender = new VBox();
					messageBubbleContainerSender.getStyleClass().add("MessageBoxSend");
					messageBubbleContainerSender.setMaxWidth(300);
					
					//	Text for Message
					Label messageTextSender = new Label();
					messageTextSender.setText(textInputArea.getText());
					messageTextSender.getStyleClass().add("MessageTextSend");
					
					//	Puts the text into the message bubble
					messageBubbleContainerSender.getChildren().addAll(messageTextSender);
					
					//	Clear the text input
					textInputArea.clear();
					
					//	Adds the bubble to the messageViewContainer
					messageViewContainer.getChildren().add(messageBubbleContainerSender);
				}
			});
			
			//	Add message input & send message button to textInputContainer
			textInputContainer.getChildren().addAll(textInputArea, sendButton);
			
			//	Adds the message bubble container and text input container to the main container
			mainContainer.getChildren().addAll(messageViewContainer, textInputContainer);
			
			return mainContainer;
	}
	

	//	Shows change data window
	private class ButtonHandler implements EventHandler<ActionEvent>{
		public void handle(ActionEvent e) {			
			//	Create change pop up menu
			Stage changeWindow = new Stage();
			changeWindow.setWidth(500);
			changeWindow.setHeight(150);
			
			//	Containers
			VBox mainContainer = new VBox();
			GridPane changeGridPane = new GridPane();
			
			//	Elements
			Label changeHeader = new Label("Change Information");
			Label instructions = new Label("Enter new data below and click Save");
			Label dataLabel = new Label();
			newDataTextField = new TextField();
			Button updateButton = new Button("Save");
			
			//	Update Button Event
			updateButton.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					//	Change the data
					if(data == "address") {
						patient.getPersonalInfo().changeAddress(newDataTextField.getText());
						pAddress.setText(patient.getPersonalInfo().getAddress());
					}
					if(data == "phone") {
						patient.getPersonalInfo().changePhoneNumber(newDataTextField.getText());
						pPhoneNumber.setText(patient.getPersonalInfo().getPhoneNumber());
					}
					if(data == "email") {
						patient.getPersonalInfo().changePhoneNumber(newDataTextField.getText());
						pEmail.setText(patient.getPersonalInfo().getEmail());
					}
					if(data == "pharmacy") {
						patient.setPharmacy(newDataTextField.getText());
						pPharmacy.setText(patient.getPharmacy());
					}
					
					//	Close the change window
					changeWindow.close();
				}
			});
			
			//	Style Elements
			changeHeader.setStyle("-fx-font-family: 'Roboto'; -fx-font-size: 15; -fx-font-weight: bold");
			updateButton.setStyle("-fx-background-color: #39C0EA; -fx-text-fill: white; -fx-font-weight: bold");
			updateButton.setStyle("-fx-padding: 7, 7, 7, 7");
			dataLabel.setMinWidth(120);
			newDataTextField.setMinWidth(250);
			changeGridPane.setHgap(15);
			mainContainer.setSpacing(10);
			mainContainer.setPadding(new Insets(10, 10, 10, 10));
			
			//	Add elements to gridpane
			changeGridPane.add(dataLabel, 0, 0);
			changeGridPane.add(newDataTextField, 1, 0);
			changeGridPane.add(updateButton, 2, 0);
			
			//	Add all elements to main container
			mainContainer.getChildren().addAll(changeHeader, instructions, changeGridPane);
			
			//	Creates the scene for the window
			Scene scene = new Scene(mainContainer);
			changeWindow.setScene(scene);
			
			changeWindow.show();
			
			if(addressButton.isArmed()) {
				dataLabel.setText("New Address");
				data = "address";
			}
			if(phoneNumberButton.isArmed()) {
				dataLabel.setText("New Phone Number");
				data = "phone";
			}
			if(emailButton.isArmed()) {
				dataLabel.setText("New Email");
				data = "email";
			}
			if(pharmacyButton.isArmed()) {
				dataLabel.setText("New Pharmacy");
				data = "pharmacy";
			}
		}
	}
}
