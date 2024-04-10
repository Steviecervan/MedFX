package medfx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets; 
import javafx.geometry.Pos;
import javafx.scene.text.TextAlignment;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.Pane;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileReader;
import javafx.scene.control.TextInputDialog;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class DoctorViewPage extends VBox
{
	private Scene visitScene;
	private Button signOutButton;	
	private Button backBtn;
	private Doctor doctor;
	
	private TextArea textInputArea;
	private VBox messageViewContainer;
	
	public DoctorViewPage(Doctor doctor) throws IOException {
		
		this.doctor = doctor;
	
	Label medFXLabel= new Label("MedFX");
	    medFXLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24)); 
	    medFXLabel.setTextFill(javafx.scene.paint.Color.WHITE);
	    
	   // Button patientButton = new Button("Patients");
	    Button messageButton = new Button("Messages");
	    signOutButton = new Button("Sign Out");
	    signOutButton.setOnAction(new ButtonHandler());
	    
	    Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

		 HBox titleBox= new HBox(10);
	     titleBox.setStyle("-fx-background-color: #39C0EA;");

		 titleBox.getChildren().addAll(medFXLabel, messageButton,spacer, signOutButton);
		 titleBox.setPadding(new Insets(10));
		 
		 Label patientsLabel= new Label("Patients");
		 Font largeBoldFont = Font.font("Arial", FontWeight.BOLD, 20); 
	     patientsLabel.setFont(largeBoldFont);
		 //Button newPatientButton= new Button("New Patient");
	 
		 HBox searchBarBox= new HBox(10);
		 //searchBarBox.setPadding(new Insets(10)); 
		 searchBarBox.getChildren().addAll(patientsLabel);
		 searchBarBox.setPadding(new Insets(10,10,10,10));
		
		Label patientNameLabel= new Label("Patient Name");
        patientNameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16)); 
     
        ArrayList<Patient> patientList = Doctor.getPatientList();
	    
	    
	    VBox individualPatientHolderBox= new VBox(15);
	    individualPatientHolderBox.setPadding(new Insets(10,10,10,10));

		
	    for (Patient patient : patientList) {
	    	
	    	 Region bigSpace = new Region();
	         HBox.setHgrow(bigSpace, Priority.ALWAYS);
	        Label nameLabel = new Label(patient.getUsername());

	        Button viewButton = new Button("View");
	        viewButton.setOnAction(event -> newPatientScreen(patient));

	        Button msgsButton = new Button("Message");

	        HBox individualPatientBox = new HBox(5);
	        individualPatientBox.setPadding(new Insets(10, 10, 10, 10));
	        individualPatientBox.getChildren().addAll(nameLabel, bigSpace, viewButton, msgsButton);
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
	   // viewButton.setOnAction(event -> visitScreen());	     
	   messageButton.setOnAction(event -> messageScreen());

	    
}
	private void newPatientScreen(Patient patient) {
		HBox topBox= new HBox(20);
		topBox.setSpacing(20);
    	Label medFXLabel= new Label("MedFX");
    	 medFXLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24)); 
    	 medFXLabel.setTextFill(javafx.scene.paint.Color.WHITE);
         signOutButton = new Button("Sign Out");

         backBtn= new Button("Back");
         backBtn.setOnAction(new ButtonHandler());
        
        topBox.getChildren().addAll(medFXLabel, signOutButton, backBtn);
        topBox.setStyle("-fx-background-color: #39C0EA;");
    	
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
	        if (!patient.getPersonalInfo().getEmail().equals(""))      
	        {
	        	patientEmail= new Label(patient.getPersonalInfo().getEmail());
	        }
	             
	        Label phoneLabel= new Label("Phone Number:");
	        Label patientPhone= new Label("Information not yet provided");
	        if (!patient.getPersonalInfo().getPhoneNumber().equals(""))
	        {
	         patientPhone= new Label(patient.getPersonalInfo().getPhoneNumber());
	        }
	        
	        Label addressLabel = new Label("Address:");
	        Label patientAddress= new Label("Information not yet provided");
	        if (!patient.getPersonalInfo().getAddress().equals(""))
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
	    
        Label visitLabel= new Label(); // temporarily creates empty label
        visitLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12)); 
        visitBox.getChildren().add(visitLabel); // add label to visit box

        ArrayList<Visits> patientVisits = patient.getVisits();

        if (!patientVisits.isEmpty())
        {
        	visitLabel= new Label("Visits");
        	// for loop to add visits
            for (Visits visit : patientVisits)
            {
            	int visitIndex = patientVisits.indexOf(visit);
            	
            	Button visitButton= new Button("View");
            	Region spacer = new Region();
                HBox.setHgrow(spacer, Priority.ALWAYS);
                
                HBox individualVisitBox= new HBox(10);
                individualVisitBox.setStyle("-fx-background-color: #d3d3d3;");
                Label visitDateLabel= new Label("Visit Date: " + visit.getDate());   
                individualVisitBox.getChildren().addAll(visitDateLabel, spacer, visitButton);
                visitBox.getChildren().add(individualVisitBox);
                visitButton.setOnAction(event -> visitScreen(patient, visitIndex));
            	
            }
        	
        }
        
	    visitBox.setPadding(new Insets(10,10,10,10));
        

        GridPane medHistoryGrid= new GridPane();
        Label medHistoryLabel= new Label("Medical History");
        Label patientMedHistoryLabel = new Label();
       
       medHistoryGrid.add(medHistoryLabel,0,0);
       medHistoryGrid.add(patientMedHistoryLabel,0,1);
        
       GridPane allergyGrid= new GridPane();
        Label allergyLabel= new Label("Allergies");
        Label patientAllergyLabel = new Label();
        allergyGrid.add(allergyLabel,0,0);
        allergyGrid.add(patientAllergyLabel,0,1);

        GridPane medicationGrid= new GridPane();
        Label medicationLabel= new Label("Medication History");
        
        Label patientMedicationLabel = new Label(); // temporarily creates empty label
        
        if (!patientVisits.isEmpty())
        {
        	//populate prescription
        	patientMedicationLabel = new Label (patientVisits.get(0).getPrescription().toString()); // displays most recent medication
        	patientMedicationLabel.setMinHeight(75);
        	patientMedicationLabel.setMaxWidth(200);
        	patientMedicationLabel.setWrapText(true);
        	
        	//populate allergy label
        	patientAllergyLabel.setText(patientVisits.get(0).getAllergies());
        	
        	//populate medical history label
        	patientMedHistoryLabel.setText(patientVisits.get(0).getMedicalHistory());
        	
        }
        
        medicationGrid.add(medicationLabel,0,0);
        medicationGrid.add(patientMedicationLabel,0,1);

        
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
        //visitButton.setOnAction(event -> visitScreen()); comment out for now


    }
	
	private void visitScreen(Patient patient, int index) {
		
		   BorderPane visitPane = new BorderPane();
		   //visitPane.setPadding(new Insets(0,50,50,50));
		    HBox topBox = new HBox(10);
		    topBox.setPadding(new Insets(10));
		    topBox.setStyle("-fx-background-color: #39C0EA;");
		    Label medFXLabel = new Label("MedFX");
		    medFXLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24)); 
		    medFXLabel.setTextFill(javafx.scene.paint.Color.WHITE);
		    Button patientButton = new Button("Patients");
		    Button messageButton = new Button("Messages");
		    signOutButton = new Button("Sign Out");
		    
		    backBtn= new Button("Back");
	        backBtn.setOnAction(new ButtonHandler());
	        
	        Visits visit = patient.getVisits().get(index);

		    Label visitTopLabel = new Label("Visit " + visit.getDate());
		    Font largeBoldFont = Font.font("Arial", FontWeight.BOLD, 20); 
		    visitTopLabel.setFont(largeBoldFont);

		    GridPane vitalGrid = new GridPane();
		    VBox concernBox = new VBox();
		    VBox physicalResultBox = new VBox();
		    VBox prescribeBox = new VBox();
		    prescribeBox.setPadding(new Insets(0,100,0,0));

		    Label vitalResult = new Label("Vital Results");
		    Label concernLabel = new Label("Health Concerns");
		    Label physicalResultLabel = new Label("Physical Results");
		    Label prescribeLabel = new Label("Prescribe Medication");
		    
		   GridPane prescriptionPane=new GridPane();
		  
		    Label medName=new Label("Medication Name:");
		    Label medStrength=new Label("Strength:");
		    Label medDosage=new Label("Dosage:");
		    
		    TextField medNameArea= new TextField();
		    TextField medStrengthArea= new TextField();
		    TextArea medDosageArea= new TextArea();
		    medNameArea.setMaxWidth(200);
		    medNameArea.setMaxHeight(2);
		    medStrengthArea.setMaxWidth(200);
		    medStrengthArea.setMaxHeight(2);
		    medDosageArea.setMaxWidth(200);
		    medDosageArea.setMaxHeight(2);
		    
		    
		    prescriptionPane.add(medName, 0, 0);
		    prescriptionPane.add(medNameArea, 1, 0);
		    prescriptionPane.add(medStrength, 0, 1);
		    prescriptionPane.add(medStrengthArea, 1, 1);
		    prescriptionPane.add(medDosage, 0, 2);
		    prescriptionPane.add(medDosageArea, 1, 2);

		    Button orderButton=new Button("Order");
		    
		    TextArea concernArea= new TextArea(visit.getHealthConcerns());
		    TextArea physicalResultArea= new TextArea(visit.getPhysicalResults());
		    
		    //make text area
		    concernArea.setEditable(false);
		    physicalResultArea.setEditable(false);
		    
		    concernArea.setMaxWidth(200);
		    concernArea.setMaxHeight(2);
		    physicalResultArea.setMaxWidth(200);
		    physicalResultArea.setMaxHeight(2);
		    
		    vitalResult.setStyle("-fx-underline: true;");
		    concernLabel.setStyle("-fx-underline: true;");
		    physicalResultLabel.setStyle("-fx-underline: true;");
		    prescribeLabel.setStyle("-fx-underline: true;");
		    
		    Label weightLabel= new Label("Weight: ");
		    Label heightLabel= new Label("Height: ");
		    Label tempLabel= new Label("Body Temperature: ");
		    Label BPLabel= new Label("Blood Pressure: ");
		    
		    Label XWLabel= new Label("" + visit.getWeight());
		    Label XHLabel= new Label("" + visit.getHeight());
		    Label XTLabel= new Label("" + visit.getBodyTemperature());
		    Label XBPLabel= new Label(visit.getBloodPressure());
		    
		    vitalGrid.add(vitalResult, 0,0);
		    vitalGrid.add(weightLabel, 0,1);
		    vitalGrid.add(XWLabel, 1,1);
		    vitalGrid.add(heightLabel, 2,1);
		    vitalGrid.add(XHLabel, 3,1);
		    vitalGrid.add(tempLabel, 4,1);
		    vitalGrid.add(XTLabel, 5,1);
		    vitalGrid.add(BPLabel, 0,2);
		    vitalGrid.add(XBPLabel, 1,2);
		    
		    vitalGrid.setHgap(20);
		    vitalGrid.setVgap(6);
		    
		    
		    concernBox.getChildren().addAll(concernLabel, concernArea);
		    physicalResultBox.getChildren().addAll(physicalResultLabel,physicalResultArea);
		    prescribeBox.getChildren().addAll(prescribeLabel, prescriptionPane, orderButton);
		   
		    prescriptionPane.setAlignment(Pos.CENTER);
		    prescriptionPane.setVgap(6);
    
		    VBox visitInfoBox = new VBox();
		    visitInfoBox.setAlignment(Pos.CENTER); 
		    visitInfoBox.setPadding(new Insets(20,100,100,100)); 
		    visitInfoBox.getChildren().addAll(visitTopLabel,vitalGrid, concernBox, physicalResultBox, prescribeBox);

		    vitalGrid.setAlignment(Pos.CENTER);
		    concernBox.setAlignment(Pos.CENTER);
		    physicalResultBox.setAlignment(Pos.CENTER);
		    prescribeBox.setAlignment(Pos.CENTER);

		    topBox.getChildren().addAll(medFXLabel, patientButton, messageButton, signOutButton, backBtn);
		    topBox.setPadding(new Insets(10));


		    visitPane.setTop(topBox);
		    visitPane.setCenter(visitInfoBox);
		    Scene visitScene = new Scene(visitPane, 800, 500);
		    Stage visitStage = new Stage();
		    visitStage.setScene(visitScene);
		    visitStage.show();
		    

		    
		    
		    orderButton.setOnAction(event -> {
		    	Medication prescription = new Medication(medNameArea.getText(), medDosageArea.getText(), medStrengthArea.getText());
		    	
		    	visit.setPrescription(prescription);
		    	 
		    	 try
		    	 {
		    		 Patient.writePatientToDatabase(patient); // update patient in database
		    	 }
		    	 catch (IOException ex)
		    	 {
		    		 // do nothing
		    		 Label err = new Label(ex.toString());
		    		 prescribeBox.getChildren().add(err);
	
		    	 }
		    	
		    	Label orderedPrescription = new Label("Prescription ordered!");
		    	prescribeBox.getChildren().add(orderedPrescription);
		    } );
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
			
			if (backBtn.isArmed())
			{
				try
				{
					
					SceneController.switchToDoctorView(e, doctor); 
				}
				catch (Exception ex)
				{
					
				}
				
				
			}
			
		}
	}
	
	
}