package medfx;

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
        
        HBox individualPatientBox= new HBox(5);
	    Label nameLabel= new Label("last-first-DOB");
	    Button checkInButton= new Button("Check In");
	    Button msgsButton= new Button("Message");
	    
	    individualPatientBox.setPadding(new Insets(10,10,10,10));

	    individualPatientBox.getChildren().addAll(nameLabel,Bigspacer, checkInButton, msgsButton);
	    
	    VBox individualPatientHolderBox= new VBox();
	    individualPatientHolderBox.getChildren().addAll(individualPatientBox);
	    individualPatientHolderBox.setPadding(new Insets(10,10,10,10));

		
		VBox patientBox= new VBox();
		patientBox.getChildren().addAll(patientNameLabel, individualPatientHolderBox);
		patientBox.setPadding(new Insets(10,10,250,10));
		patientNameLabel.setAlignment(Pos.TOP_LEFT);
		//patientBox.setAlignment(Pos.CENTER);
		patientBox.setStyle("-fx-background-color: #d3d3d3;");
		individualPatientBox.setStyle("-fx-background-color: #F4F4F4;");

		
		VBox holderBox= new VBox();
		holderBox.setPadding(new Insets(20,50,50,50));
		holderBox.getChildren().addAll(searchBarBox, patientBox);
	
		VBox wholeSearchBox= new VBox();
		wholeSearchBox.getChildren().addAll(holderBox);
     
	     BorderPane mainPane= new BorderPane();
	     mainPane.setCenter(wholeSearchBox);
	     mainPane.setTop(titleBox);
	     this.getChildren().add(mainPane); 
	    newPatientButton.setOnAction(event -> newPatientScreen());	     
	    checkInButton.setOnAction(event -> checkInScreen());	
	    messageButton.setOnAction(event -> messageScreen());

}
	private void newPatientScreen() {
		HBox topBox= new HBox(500);
    	Label medFXLabel= new Label("MedFX");
    	 medFXLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24)); 
    	 medFXLabel.setTextFill(javafx.scene.paint.Color.WHITE);
        Button signOutButton = new Button("Sign Out");
        topBox.getChildren().addAll(medFXLabel, signOutButton);
        topBox.setStyle("-fx-background-color: #39C0EA;");
        topBox.setPadding(new Insets(10));
    	
        BorderPane intakePane = new BorderPane();
        GridPane intakeGridPane= new GridPane();
       
	        Label firstNameLabel= new Label("First Name:");
	        TextArea firstNameArea = new TextArea();
	        firstNameArea.setPrefRowCount(1); 
	        firstNameArea.setPrefColumnCount(10);
	       
	        Label middleInitial= new Label("Middle Initial");
	        TextArea middleInitialArea= new TextArea();
	        middleInitialArea.setPrefRowCount(1); 
	        middleInitialArea.setPrefColumnCount(1);
	               
	        Label lastNameLabel= new Label("Last Name:");
	        TextArea lastNameArea = new TextArea();
	        lastNameArea.setPrefRowCount(1); 
	        lastNameArea.setPrefColumnCount(10);
	             
	        Label emailLabel= new Label("Email Address:");
	        TextArea emailArea = new TextArea();
	        emailArea.setPrefRowCount(1); 
	        emailArea.setPrefColumnCount(5);
	             
	        Label phoneLabel= new Label("Phone Number:");
	        TextArea phoneArea = new TextArea();
	        phoneArea.setPrefRowCount(1); 
	        phoneArea.setPrefColumnCount(5);
	            
	        Label DOBLabel= new Label("DOB:");
	        TextArea DOBArea = new TextArea();     
	        DOBArea.setPrefRowCount(1); 
	        DOBArea.setPrefColumnCount(1);
	              
	        Label addressLabel = new Label("Address:");
	        TextArea addressArea = new TextArea();
	        addressArea.setPrefRowCount(1); 
	        addressArea.setPrefColumnCount(10);
              
        intakeGridPane.setHgap(5);
        intakeGridPane.setVgap(5);
        
	        intakeGridPane.add(firstNameLabel, 0, 0);
	        intakeGridPane.add(firstNameArea, 1, 0);
	        intakeGridPane.add(middleInitial, 2, 0);
	        intakeGridPane.add(middleInitialArea, 3, 0);
	        intakeGridPane.add(lastNameLabel, 4, 0);
	        intakeGridPane.add(lastNameArea, 5, 0);
	
	        intakeGridPane.add(DOBLabel, 0, 1);
	        intakeGridPane.add(DOBArea, 1, 1);
	        intakeGridPane.add(phoneLabel, 2, 1);
	        intakeGridPane.add(phoneArea, 3, 1);
	        
	        intakeGridPane.add(addressLabel, 0, 2);
	        intakeGridPane.add(addressArea, 1, 2);
	        intakeGridPane.add(emailLabel, 2, 2);
	        intakeGridPane.add(emailArea, 3, 2);
	        
        VBox visitBox= new VBox();
        Label visitLabel= new Label("Visits");
        Button visitButton= new Button("Go 2 Visit");
        visitBox.getChildren().addAll(visitLabel, visitButton);
        
        GridPane medHistoryGrid= new GridPane();
        Label medHistoryLabel= new Label("Medical History");
        medHistoryGrid.getChildren().add(medHistoryLabel);

        GridPane allergyGrid= new GridPane();
        Label allergyLabel= new Label("Allergies");
        allergyGrid.getChildren().add(allergyLabel);

        GridPane immunizationGrid= new GridPane();
        Label immunizationLabel= new Label("Immunization History");
        immunizationGrid.getChildren().add(immunizationLabel);

        GridPane medicationGrid= new GridPane();
        Label medicationLabel= new Label("Medication History");
        medicationGrid.getChildren().add(medicationLabel);
        
        
        GridPane patientHistoryGrid= new GridPane();
        patientHistoryGrid.add(medHistoryGrid, 0, 0);
        patientHistoryGrid.add(allergyGrid, 0, 1);
        patientHistoryGrid.add(immunizationGrid, 1, 0);
        patientHistoryGrid.add(medicationGrid, 1, 1);
        patientHistoryGrid.setHgap(200); 
        patientHistoryGrid.setVgap(50);        
        
        VBox allPatientInfoBox= new VBox();
        allPatientInfoBox.getChildren().addAll(intakeGridPane, visitBox, patientHistoryGrid);
        allPatientInfoBox.setSpacing(20);
        allPatientInfoBox.setPadding(new Insets(20));
        allPatientInfoBox.setAlignment(Pos.CENTER);
                   
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
		visitSummaryBox.getChildren().addAll(infoBox, vitalResultsLabel, vitalsGridPane, substancesBox, concernBox);
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
