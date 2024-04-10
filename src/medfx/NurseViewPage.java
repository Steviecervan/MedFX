package medfx;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class NurseViewPage extends VBox{
private Scene visitScene;

private Nurse nurse;
private Button signOutButton;

private TextArea textInputArea;
private VBox messageViewContainer;
	
	public NurseViewPage(Nurse nurse) throws IOException {	
		
		this.nurse = nurse;
	
		Label medFXLabel= new Label("MedFX");
	    medFXLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24)); 
	    medFXLabel.setTextFill(javafx.scene.paint.Color.WHITE);
	    
	    Button patientButton = new Button("Patients");
	    Button messageButton = new Button("Messages");
	    signOutButton = new Button("Sign Out");
	    signOutButton.setOnAction(new ButtonHandler());
	    
	    Region Bigspacer = new Region();
        HBox.setHgrow(Bigspacer, Priority.ALWAYS);

		 HBox titleBox= new HBox(10);
	     titleBox.setStyle("-fx-background-color: #39C0EA;");

		 titleBox.getChildren().addAll(medFXLabel, patientButton,messageButton, Bigspacer, signOutButton);
		 titleBox.setPadding(new Insets(10));
		 
		 Label patientsLabel= new Label("Patients");
		 Font largeBoldFont = Font.font("Arial", FontWeight.BOLD, 20); 
	     patientsLabel.setFont(largeBoldFont);
		 TextField patientSearch= new TextField();
		 patientSearch.setPromptText("Search Patient");
		 Button searchButton= new Button("Search");
		 Button newPatientButton= new Button("New Patient");
		 
		 HBox searchBarBox= new HBox(10);
		 //searchBarBox.setPadding(new Insets(10)); 
		 searchBarBox.getChildren().addAll(patientsLabel, patientSearch, searchButton, newPatientButton);
		 searchBarBox.setPadding(new Insets(10,10,10,10));
		
		Label patientNameLabel= new Label("Patient Name");
        patientNameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16)); 
 
	    ArrayList<Patient> patientList = Nurse.getPatientList();
	    
	    VBox individualPatientHolderBox= new VBox(15);
	    individualPatientHolderBox.setPadding(new Insets(10,10,10,10));
	
	    for (Patient patient : patientList) {
	    	
	    	 Region bigSpace = new Region();
	         HBox.setHgrow(bigSpace, Priority.ALWAYS);
	        Label nameLabel = new Label(patient.getUsername());

		    Button checkInButton= new Button("Check In");
		    checkInButton.setOnAction(event -> checkInScreen());
		    
		    Button viewButton = new Button("View");
	        viewButton.setOnAction(event -> newPatientScreen(patient));

	        Button msgsButton = new Button("Message");

	        HBox individualPatientBox = new HBox(5);
	        individualPatientBox.setPadding(new Insets(10, 10, 10, 10));
	        individualPatientBox.getChildren().addAll(nameLabel, bigSpace, checkInButton, msgsButton, viewButton);
			individualPatientBox.setStyle("-fx-background-color: #F4F4F4;");
	        individualPatientHolderBox.getChildren().add(individualPatientBox);
	    }
	  
		VBox patientBox= new VBox();
		patientBox.getChildren().addAll(patientNameLabel, individualPatientHolderBox);
		patientBox.setPadding(new Insets(10,10,250,10));
		patientNameLabel.setAlignment(Pos.TOP_LEFT);
		//patientBox.setAlignment(Pos.CENTER);
		patientBox.setStyle("-fx-background-color: #d3d3d3;");

		
		VBox holderBox= new VBox();
		holderBox.setPadding(new Insets(20,50,50,50));
		holderBox.getChildren().addAll(searchBarBox, patientBox);
	
		VBox wholeSearchBox= new VBox();
		wholeSearchBox.getChildren().addAll(holderBox);
     
	     BorderPane mainPane= new BorderPane();
	     mainPane.setCenter(wholeSearchBox);
	     mainPane.setTop(titleBox);
	     this.getChildren().add(mainPane); 
	    //newPatientButton.setOnAction(event -> newPatientScreen());	     
	    messageButton.setOnAction(event -> messageScreen());

}
	private void newPatientScreen(Patient patient) {
		HBox topBox= new HBox(500);
    	Label medFXLabel= new Label("MedFX");
    	 medFXLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24)); 
    	 medFXLabel.setTextFill(javafx.scene.paint.Color.WHITE);
         signOutButton = new Button("Sign Out");
        
        topBox.getChildren().addAll(medFXLabel, signOutButton);
        topBox.setStyle("-fx-background-color: #39C0EA;");
        topBox.setPadding(new Insets(10));
    	
        BorderPane intakePane = new BorderPane();
        GridPane intakeGridPane= new GridPane();
        
        String[] usernameParts = patient.getUsername().split("-");
        String firstName = usernameParts[1];
        String lastName = usernameParts[0];
        String DOB = usernameParts[2] + "-" + usernameParts[3] + "-" + usernameParts[4];

       
	        Label firstNameLabel= new Label("First Name:");
	        Label patientFirstName= new Label(firstName);
	        	               
	        Label lastNameLabel= new Label("Last Name:");
		    Label patientLastName= new Label(lastName);
    
		    Label DOBLabel= new Label("DOB:");
			Label patientDOB= new Label(DOB);
			  
			
	        Label emailLabel= new Label("Email Address:");
	        Label patientEmail= new Label("Information not yet provided");
	        if (!patient.getPersonalInfo().getEmail().isEmpty())
	        {
	        	patientEmail= new Label(patient.getPersonalInfo().getEmail());
	        }
	             
	        Label phoneLabel= new Label("Phone Number:");
	        Label patientPhone= new Label("Information not yet provided");
	        if (!patient.getPersonalInfo().getPhoneNumber().isEmpty())
	        {
	         patientPhone= new Label(patient.getPersonalInfo().getPhoneNumber());
	        }
	        
	        Label addressLabel = new Label("Address:");
	        Label patientAddress= new Label("Information not yet provided");
	        if (!patient.getPersonalInfo().getAddress().isEmpty())
	        {
	         patientAddress= new Label(patient.getPersonalInfo().getAddress());
	        }
	        
	        firstNameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12)); 
	        lastNameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12)); 
	        emailLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12)); 
	        phoneLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12)); 
	        DOBLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12)); 
	        addressLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12)); 

              
        intakeGridPane.setHgap(25);
        intakeGridPane.setVgap(5);
        
	        intakeGridPane.add(firstNameLabel, 0, 0);
	        intakeGridPane.add(patientFirstName, 1, 0);
	        intakeGridPane.add(lastNameLabel, 2, 0);
	        intakeGridPane.add(patientLastName, 3, 0);
	
	       intakeGridPane.add(DOBLabel, 0, 1);
	       intakeGridPane.add(patientDOB, 1, 1);
	        intakeGridPane.add(phoneLabel, 2, 1);
	        intakeGridPane.add(patientPhone, 3, 1);
	        
	        intakeGridPane.add(addressLabel, 0, 2);
	        intakeGridPane.add(patientAddress, 1, 2);
	        intakeGridPane.add(emailLabel, 2, 2);
	        intakeGridPane.add(patientEmail, 3, 2);
	        
       
	    VBox visitBox= new VBox(10);
	    visitBox.setPadding(new Insets(10,10,10,10));
	    
        Label visitLabel= new Label("Visits");
        visitLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12)); 

	    Button visitButton= new Button("View");
	    
	    Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        
        HBox individualVisitBox= new HBox(10);
        individualVisitBox.setStyle("-fx-background-color: #d3d3d3;");
        Label visitDateLabel= new Label("Visit Date MM-DD-YYYY");   
        individualVisitBox.getChildren().addAll(visitDateLabel, spacer, visitButton);
	    visitBox.setPadding(new Insets(10,10,10,10));

        
        visitBox.getChildren().addAll(visitLabel, individualVisitBox);

        GridPane medHistoryGrid= new GridPane();
        Label medHistoryLabel= new Label("Medical History");
        medHistoryGrid.getChildren().addAll(medHistoryLabel);

        GridPane allergyGrid= new GridPane();
        Label allergyLabel= new Label("Allergies");
        allergyGrid.getChildren().addAll(allergyLabel);

        GridPane medicationGrid= new GridPane();
        Label medicationLabel= new Label("Medication History");
        medicationGrid.getChildren().addAll(medicationLabel);
        
        medHistoryLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12)); 
        allergyLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12)); 
        medicationLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12)); 

        
        GridPane patientHistoryGrid= new GridPane();
        patientHistoryGrid.add(medHistoryGrid, 0, 0);
        patientHistoryGrid.add(allergyGrid, 1, 0);
        patientHistoryGrid.add(medicationGrid, 0, 1);
        patientHistoryGrid.setHgap(200); 
        patientHistoryGrid.setVgap(50);        
        
        VBox allPatientInfoBox= new VBox();
        allPatientInfoBox.getChildren().addAll(intakeGridPane, visitBox, patientHistoryGrid);
        allPatientInfoBox.setSpacing(20);
        allPatientInfoBox.setPadding(new Insets(20));
        allPatientInfoBox.setAlignment(Pos.CENTER); 
        allPatientInfoBox.setMaxWidth(600);
        allPatientInfoBox.setMaxHeight(300);
        allPatientInfoBox.setStyle("-fx-background-color: #FFFFFF;");
             
        intakePane.setCenter(allPatientInfoBox);
        intakePane.setTop(topBox);

        Scene newPatientScene = new Scene(intakePane, 800, 500);
        Stage stage = (Stage) getScene().getWindow();
        stage.setScene(newPatientScene);       	 
        visitButton.setOnAction(event -> visitScreen());


    }
	
	private void checkInScreen()
	{
		Stage summaryWindow = new Stage();
		summaryWindow.setWidth(800);
		summaryWindow.setHeight(500);
		
		//	Display Information
		BorderPane summaryContainer = new BorderPane();
		summaryContainer.setPrefSize(100, 100);
		
		Label medFXLabel= new Label("MedFX");
	    medFXLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24)); 
	    medFXLabel.setTextFill(javafx.scene.paint.Color.WHITE);
	    
	    Button patientButton = new Button("Patients");
	    Button messageButton = new Button("Messages");
	    Button signOutButton = new Button("Sign Out");

		 HBox titleBox= new HBox(10);
	     titleBox.setStyle("-fx-background-color: #39C0EA;");
		 
		 titleBox.getChildren().addAll(medFXLabel, patientButton,messageButton,signOutButton);
		 titleBox.setPadding(new Insets(10));
		
		Label visitNameLabel = new Label("Last-First-DOB ");
		visitNameLabel.setStyle("-fx-font-family: roboto; -fx-font-weight: bold; -fx-font-size: 20; -fx-padding: 0, 0, 0, 20");
		
		Label dateLabel = new Label(" (MM-DD-YYYY) ");
		dateLabel.setStyle("-fx-font-family: roboto; -fx-font-weight: bold; -fx-font-size: 20; -fx-padding: 0, 0, 0, 20");
		
	
		
		Label vitalResultsLabel = new Label("Vitals");
		vitalResultsLabel.setStyle("-fx-font-family: roboto; -fx-font-weight: bold; -fx-font-size: 15; -fx-padding: 0, 5, 0, 5");
						
		Label over12Label = new Label("Is Patient Over 12?");
		TextArea over12Area= new TextArea();
		
		
		Label allergyLabel = new Label("Known Allergies: ");
		TextArea allergyArea= new TextArea();

		Label substancesLabel = new Label("History of Alcohol/Drug use: ");
		TextArea substancesArea= new TextArea();
		
		Label concernLabel = new Label("Concerns/Reasons for Visit: ");
		TextArea concernArea= new TextArea();
		
		Button saveButton= new Button("Save");
		
		over12Area.setPrefSize(10, 10); 
		allergyArea.setPrefSize(1, 1); 
		substancesArea.setPrefSize(300, 20); 
		concernArea.setPrefSize(300, 20); 

		
		HBox substancesBox= new HBox();
		substancesBox.setPadding(new Insets(10));
		HBox concernBox= new HBox();
		concernBox.setPadding(new Insets(10));

		substancesBox.getChildren().addAll(substancesLabel,substancesArea);
		concernBox.getChildren().addAll(concernLabel,concernArea);
		

		
		//	Vital Results
		Label weightLabel = new Label("Weight");
		Label heightLabel = new Label("Height");
		Label bodyTemperatureLabel = new Label("Temperature");
		Label bloodPressureLabel = new Label("Blood Pressure");
		//Label pWeight = new Label("");
		//Label pHeight= new Label("");
		//Label pBodyTemp= new Label("");
		//Label pBloodPressure = new Label("");
		
		TextArea patientWeightArea= new TextArea();
		TextArea patientBPArea= new TextArea();
		TextArea patientHeightArea= new TextArea();
		TextArea patientTempArea= new TextArea();

		 patientWeightArea.setPrefSize(100, 5); 
	     patientBPArea.setPrefSize(100, 5);
	     patientHeightArea.setPrefSize(100, 5);
	     patientTempArea.setPrefSize(100, 5);

		
		
		TextArea healthConcernTextArea = new TextArea();				
		healthConcernTextArea.setEditable(false);
		
		TextArea physicalResultsTextArea = new TextArea();
		physicalResultsTextArea.setEditable(false);
		
		GridPane vitalsGridPane = new GridPane();
		//vitalsGridPane.setAlignment(Pos.CENTER_LEFT);
		
		vitalsGridPane.setVgap(5);
		vitalsGridPane.setHgap(20);
		
		
		vitalsGridPane.add(heightLabel, 0, 0);
		vitalsGridPane.add(patientHeightArea, 1, 0);
		vitalsGridPane.add(weightLabel, 0, 1);
		vitalsGridPane.add(patientWeightArea, 1, 1);
		vitalsGridPane.add(over12Label, 2, 1);
		vitalsGridPane.add(over12Area, 3, 1);

		
		vitalsGridPane.add(bodyTemperatureLabel, 0, 2);
		vitalsGridPane.add(patientTempArea, 1, 2);
		vitalsGridPane.add(allergyLabel, 2, 2);
		vitalsGridPane.add(allergyArea, 3, 2);

		
		vitalsGridPane.add(bloodPressureLabel, 0, 3);
		vitalsGridPane.add(patientBPArea, 1, 3);
		
		
		HBox infoBox= new HBox();
		infoBox.getChildren().addAll(visitNameLabel,dateLabel);
		
		VBox visitSummaryBox= new VBox();
		visitSummaryBox.getChildren().addAll(infoBox, vitalResultsLabel, vitalsGridPane, substancesBox, concernBox,saveButton);
		visitSummaryBox.setPadding(new Insets(5, 50, 55, 55));

	
		summaryContainer.setTop(titleBox);
		summaryContainer.setCenter(visitSummaryBox);
		
		
		//	Add everything together
		Scene scene = new Scene(summaryContainer);
		summaryWindow.setScene(scene);
		
		summaryWindow.show();
		
	}
	
	private void visitScreen() {
		
		Stage summaryWindow = new Stage();
		summaryWindow.setWidth(800);
		summaryWindow.setHeight(500);
		
		//	Display Information
		VBox summaryContainer = new VBox();
		//summaryContainer.setPadding(new Insets(0, 0, 15, 15));
		
		HBox topBox= new HBox(500);
    	Label medFXLabel= new Label("MedFX");
    	 medFXLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24)); 
    	 medFXLabel.setTextFill(javafx.scene.paint.Color.WHITE);
        Button signOutButton = new Button("Sign Out");
        topBox.getChildren().addAll(medFXLabel, signOutButton);
        topBox.setStyle("-fx-background-color: #39C0EA;");
        topBox.setPadding(new Insets(10)); 
		
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
		//Label pWeight = new Label("");
		//Label pHeight= new Label("");
		//Label pBodyTemp= new Label("");
		//Label pBloodPressure = new Label("");
		
		TextArea patientWeightArea= new TextArea();
		TextArea patientBPArea= new TextArea();
		TextArea patientHeightArea= new TextArea();
		TextArea patientTempArea= new TextArea();

		 patientWeightArea.setPrefSize(20, 20); 
	        patientBPArea.setPrefSize(20, 20);
	        patientHeightArea.setPrefSize(20, 20);
	        patientTempArea.setPrefSize(20, 20);

		
		
		TextArea healthConcernTextArea = new TextArea();				
		healthConcernTextArea.setEditable(false);
		
		TextArea physicalResultsTextArea = new TextArea();
		physicalResultsTextArea.setEditable(false);
		
		GridPane vitalsGridPane = new GridPane();
		vitalsGridPane.setAlignment(Pos.CENTER_LEFT);
		vitalsGridPane.setVgap(5);
		vitalsGridPane.setHgap(25);
		
		vitalsGridPane.add(weightLabel, 0, 0);
		vitalsGridPane.add(patientWeightArea, 1, 0);
		vitalsGridPane.add(heightLabel, 2, 0);
		vitalsGridPane.add(patientHeightArea, 3, 0);
		vitalsGridPane.add(bodyTemperatureLabel, 4, 0);
		vitalsGridPane.add(patientTempArea, 5, 0);
		vitalsGridPane.add(bloodPressureLabel, 0, 1);
		vitalsGridPane.add(patientBPArea, 1, 1);
		
		VBox visitSummaryBox= new VBox();
		visitSummaryBox.getChildren().addAll(visitDateLabel, vitalResultsLabel, vitalsGridPane, healthConcernsLabel, healthConcernTextArea, physicalResultsLabel, physicalResultsTextArea);
		visitSummaryBox.setPadding(new Insets(30));
		
		summaryContainer.getChildren().addAll(topBox, visitSummaryBox);
		
		//	Add everything together
		Scene scene = new Scene(summaryContainer);
		summaryWindow.setScene(scene);
		
		summaryWindow.show();
	}

	private void messageScreen() {
		Label medFXLabel= new Label("MedFX");
	    medFXLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24)); 
	    medFXLabel.setTextFill(javafx.scene.paint.Color.WHITE);
	    
	    Button patientButton = new Button("Patients");
	    Button messageButton = new Button("Messages");
	    signOutButton = new Button("Sign Out");
	    signOutButton.setOnAction(new ButtonHandler());
	    
	    Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

		HBox titleBox= new HBox(10);
	    titleBox.setStyle("-fx-background-color: #39C0EA;");

		titleBox.getChildren().addAll(medFXLabel, patientButton,messageButton,spacer, signOutButton);
		titleBox.setPadding(new Insets(10));
	
		// Main Container holds everything
				VBox mainContainer = new VBox();
				mainContainer.setStyle("-fx-background-color: white");

				// Display Container - holds contacts container and message container
				HBox displayContainer = new HBox();
				displayContainer.setStyle("-fx-padding: 15, 15, 15, 15; -fx-spacing: 15; -fx-alignment: center");

				// Side container - displays the patients being messaged
				VBox contactsContainer = new VBox();
				contactsContainer.setMinWidth(235);
				contactsContainer.setStyle("-fx-background-color: whitesmoke; -fx-padding: 8, 8, 8, 8; -fx-spacing: 7");

				// Message container - shows the current conversation being displayed
				// with input bar and send button
				VBox messageContainer = new VBox();
				messageContainer.setStyle("-fx-background-color: whitesmoke; -fx-padding: 10, 10, 10, 10");

				//	MessageViewContainer - holds the message bubbles
				messageViewContainer = new VBox();
				messageViewContainer.setPadding(new Insets(0, 0, 10, 0));
				messageViewContainer.setSpacing(5);
				messageViewContainer.setMinHeight(365);
				messageViewContainer.setAlignment(Pos.BOTTOM_RIGHT);

				//	-- Text Input Container --
				HBox textInputContainer = new HBox();
				// textInputContainer.getStyleClass().add("TextInputContainer");
				textInputContainer.setStyle("-fx-alignment: center; -fx-spacing: 7;");

				// TextField - message input
				textInputArea = new TextArea();
				textInputArea.getStyleClass().add("TextInputArea");
				textInputArea.setMaxWidth(535);
				textInputArea.setStyle("-fx-wrap-text: true; -fx-max-height: 10;");

				// Send Message Button
				Button sendButton = new Button("^");
				sendButton.getStyleClass().add("SendButton");
				sendButton.setMinWidth(30);
				sendButton.setMinHeight(30);
				sendButton.setStyle("-fx-border-radius: 100; -fx-text-fill: white; -fx-text-weight: bold; -fx-background-color: #39C0EA; -fx-text-alignment: center;");
				sendButton.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent e) {
						// ~ Message bubble (Sender) ~
						VBox messageBubbleContainerSender = new VBox();
						//messageBubbleContainerSender.getStyleClass().add("MessageBoxSend");
						messageBubbleContainerSender.setStyle("-fx-background-color: #39C0EA; -fx-background-radius: 15; -fx-font-family: 'Roboto'; -fx-font-size: 15; -fx-alignment: top-left;");
						messageBubbleContainerSender.setMaxWidth(300);

						// Text for Message
						Label messageTextSender = new Label();
						messageTextSender.setText(textInputArea.getText());
						//messageTextSender.getStyleClass().add("MessageTextSend");
						messageTextSender.setStyle("-fx-text-fill: white;-fx-padding: 8, 8, 8, 8; -fx-border-radius: 20; -fx-wrap-text: true;");

						// Puts the text into the message bubble
						messageBubbleContainerSender.getChildren().addAll(messageTextSender);

						// Clear the text input
						textInputArea.clear();

						// Adds the bubble to the messageViewContainer
						messageViewContainer.getChildren().add(messageBubbleContainerSender);
					}
				});

				// Add message input & send message button to textInputContainer
				textInputContainer.getChildren().addAll(textInputArea, sendButton);

				//	Add the messageViewContainer and textInputContainer to the messageContainer
				messageContainer.getChildren().addAll(messageViewContainer, textInputContainer);

				// Adds side container and message container to display container
				displayContainer.getChildren().addAll(contactsContainer, messageContainer);

				// Adds all components together
				mainContainer.getChildren().addAll(titleBox, displayContainer);

				//	PATIENT CONTACT CONTAINER ----------------
				//	This will be used to show patient contacts in the contacts container
				//	We could pass in a patient object to get all of this information
				VBox patientContact = new VBox();
				patientContact.setMaxWidth(220);
				patientContact.setMaxHeight(50);
				patientContact.setStyle("-fx-background-color: white; -fx-spacing: 5; -fx-padding: 8, 8, 8, 8; -fx-background-radius: 10");

				//	Contact Labels 
				Label pName = new Label();
				Label messagePreview = new Label(); //	Will be the last send message (either from patient or doctor)

				pName.setText("Patient Name");	//	Placeholder
				messagePreview.setText("Here is a small message preview"); //	Placeholder

				//	Set styling
				pName.setStyle("-fx-text-fill: black;\r\n"
						+ "	-fx-font-size: 15;");
				messagePreview.setStyle("-fx-text-fill: grey;\r\n"
						+ "	-fx-font-size: 13;");
				messagePreview.setMaxWidth(220);

				//	Put the name and preview into the patientContact container
				patientContact.getChildren().addAll(pName, messagePreview);

				//	Add the patientContact to the contacts container
				contactsContainer.getChildren().add(patientContact);

				Scene messageScene = new Scene(mainContainer, 800, 500);
				Stage stage = (Stage) getScene().getWindow();
				stage.setScene(messageScene);
	}
	
	private class ButtonHandler implements EventHandler<ActionEvent>
	{
		public void handle(ActionEvent e)
		{
			if (signOutButton.isArmed())
			{
				try
				{
					
					SceneController.switchToUserMainPage(e); 
				}
				catch (Exception ex)
				{
					
				}
				
				
			}
		}
	}
	
}
