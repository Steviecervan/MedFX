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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.lang.Double;
import java.lang.NumberFormatException;
import javafx.scene.paint.Color;




public class NurseViewPage extends VBox{
private Scene visitScene;
private Nurse nurse;
private Button signOutButton;
private Button patientButton;
private Button saveButton;
private TextArea textInputArea;
private VBox messageViewContainer;
	
	public NurseViewPage(Nurse nurse) throws IOException {	
		
		this.nurse = nurse;
		this.getStylesheets().add(getClass().getResource("application.css").toString());

	
		Label medFXLabel= new Label("MedFX");
	    medFXLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24)); 
	    medFXLabel.setTextFill(javafx.scene.paint.Color.WHITE);
	    
	    patientButton = new Button("Patients");
	    patientButton.getStyleClass().add("BlueButton");
	    patientButton.getStyleClass().add("buttonSize");

	    Button messageButton = new Button("Messages");
	    messageButton.getStyleClass().add("BlueButton");
	    messageButton.getStyleClass().add("buttonSize");

	    signOutButton = new Button("Sign Out");
	    signOutButton.getStyleClass().add("BlueButton");
	    signOutButton.getStyleClass().add("buttonSize");

	    signOutButton.setOnAction(new ButtonHandler());
	    
	    
	    Region Bigspacer = new Region();
        HBox.setHgrow(Bigspacer, Priority.ALWAYS);

		 HBox titleBox= new HBox(10);
	     titleBox.setStyle("-fx-background-color: #39C0EA;");
	    // titleBox.getStylesheets().add(getClass().getResource("application.css").toString());

		 titleBox.getChildren().addAll(medFXLabel, patientButton,messageButton, Bigspacer, signOutButton);
		 titleBox.setPadding(new Insets(10));
		 
		 Label patientsLabel= new Label("Patients");
		 Font largeBoldFont = Font.font("Arial", FontWeight.BOLD, 20); 
	     patientsLabel.setFont(largeBoldFont);
		
		 
		 HBox searchBarBox= new HBox(10);
		 //searchBarBox.setPadding(new Insets(10)); 
		 searchBarBox.getChildren().addAll(patientsLabel);
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
		    checkInButton.getStyleClass().add("WhiteButton");
		    checkInButton.setOnAction(event -> checkInScreen(patient));
		    
		    Button viewButton = new Button("View");
		    viewButton.getStyleClass().add("WhiteButton");
	        viewButton.setOnAction(event -> newPatientScreen(patient));


	        HBox individualPatientBox = new HBox(5);
	        individualPatientBox.setPadding(new Insets(10, 10, 10, 10));
	    	nameLabel.setFont(Font.font("Arial", 16)); 

	        individualPatientBox.getChildren().addAll(nameLabel, bigSpace, checkInButton, viewButton);
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
		HBox topBox= new HBox(10);
    	Label medFXLabel= new Label("MedFX");
    	 medFXLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24)); 
    	 medFXLabel.setTextFill(javafx.scene.paint.Color.WHITE);
    	 topBox.getStylesheets().add(getClass().getResource("application.css").toString());

    	 Region sideSpacer = new Region();
         HBox.setHgrow(sideSpacer, Priority.ALWAYS);

        
    	signOutButton = new Button("Sign Out");
 	    signOutButton.setOnAction(new ButtonHandler());
 	    signOutButton.getStyleClass().add("BlueButton");
 	   	signOutButton.getStyleClass().add("buttonSize");
 	    
 	    patientButton= new Button("Patients");
 	    patientButton.setOnAction(new ButtonHandler());
 	    patientButton.getStyleClass().add("BlueButton");
	    patientButton.getStyleClass().add("buttonSize");


        topBox.getChildren().addAll(medFXLabel,patientButton,sideSpacer,signOutButton);
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
	        if (!patient.getPersonalInfo().getEmail().equals(""));
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
	    
	    
        Label visitLabel= new Label("Visits");
        visitLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12)); 
        visitBox.getChildren().addAll(visitLabel);


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
                individualVisitBox.getStylesheets().add(getClass().getResource("application.css").toString());

                individualVisitBox.setStyle("-fx-background-color: #d3d3d3;");
                Label visitDateLabel= new Label("Visit Date: " + visit.getDate());   
    		    visitButton.getStyleClass().add("WhiteButton");
                individualVisitBox.getChildren().addAll(visitDateLabel, spacer, visitButton);
                visitBox.getChildren().add(individualVisitBox);
                visitButton.setOnAction(event -> visitScreen(patient, visitIndex));
            	
            }
        	
        }
	  
	    
	    visitBox.setPadding(new Insets(10,10,10,10));

	   
        GridPane medHistoryGrid= new GridPane();
        Label medHistoryLabel= new Label("Medical History");
        Label patientMedHistoryLabel = new Label("");
       
       medHistoryGrid.add(medHistoryLabel,0,0);
       medHistoryGrid.add(patientMedHistoryLabel,0,1);
        
       GridPane allergyGrid= new GridPane();
        Label allergyLabel= new Label("Allergies");
        Label patientAllergyLabel = new Label();
        allergyGrid.add(allergyLabel,0,0);
        allergyGrid.add(patientAllergyLabel,0,1);

        GridPane medicationGrid= new GridPane();
        Label medicationLabel= new Label("Medication History");
        Label patientMedicationLabel = new Label();
        
        
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
        
       // visitButton.setOnAction(event -> visitScreen());


    }
	
	private void checkInScreen(Patient patient)
	{
		 Region sideSpacer = new Region();
         HBox.setHgrow(sideSpacer, Priority.ALWAYS);

		Stage summaryWindow = new Stage();
		summaryWindow.setWidth(800);
		summaryWindow.setHeight(500);
		
		BorderPane summaryContainer = new BorderPane();
		summaryContainer.setPrefSize(100, 100);
		
		
		Label medFXLabel= new Label("MedFX");
	    medFXLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24)); 
	    medFXLabel.setTextFill(javafx.scene.paint.Color.WHITE);
	    
	    LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = currentDate.format(formatter);
        
	   
		 HBox titleBox= new HBox(10);
	     titleBox.setStyle("-fx-background-color: #39C0EA;");
	     titleBox.getStylesheets().add(getClass().getResource("application.css").toString());
	     
	     patientButton = new Button("Patients");
		 patientButton.getStyleClass().add("BlueButton");
		 patientButton.getStyleClass().add("buttonSize");
		 patientButton.setOnAction(new ButtonHandler());

		    		    
		 signOutButton = new Button("Sign Out");
	     signOutButton.getStyleClass().add("BlueButton");
		 signOutButton.getStyleClass().add("buttonSize");
		 signOutButton.setOnAction(new ButtonHandler());
		 


		 
		 titleBox.getChildren().addAll(medFXLabel, patientButton,sideSpacer, signOutButton);
		 titleBox.setPadding(new Insets(10));
		 

		    String[] usernameParts = patient.getUsername().split("-");
	        String firstName = usernameParts[1];
	        String lastName = usernameParts[0];
	        String DOB = usernameParts[2] + "-" + usernameParts[3] + "-" + usernameParts[4];
	        String patientID= lastName+ "-" + firstName+ "-"+ DOB +"    ";
	        Label ptIDLabel= new Label(patientID);
	        ptIDLabel.setStyle("-fx-font-family: roboto; -fx-font-weight: bold; -fx-font-size: 20; -fx-padding: 0, 0, 0, 20");

		
		Label visitNameLabel = new Label("Last-First-DOB ");
		visitNameLabel.setStyle("-fx-font-family: roboto; -fx-font-weight: bold; -fx-font-size: 20; -fx-padding: 0, 0, 0, 20");
		
		Label dateLabel = new Label(" (MM-DD-YYYY) ");
		dateLabel.setStyle("-fx-font-family: roboto; -fx-font-weight: bold; -fx-font-size: 20; -fx-padding: 0, 0, 0, 20");
		
		Label vitalResultsLabel = new Label("Vitals");
		vitalResultsLabel.setStyle("-fx-font-family: roboto; -fx-font-weight: bold; -fx-font-size: 15; -fx-padding: 0, 5, 0, 5");
						
		Label over12Label = new Label("Is Patient Over 12?");
		TextField over12Area= new TextField();

		Label allergyLabel = new Label("Known Allergies: ");
		TextField allergyArea= new TextField();

		Label resultsLabel = new Label("Physical Results: ");
		TextArea resultsArea= new TextArea();
		
		Label concernLabel = new Label("Concerns/Reasons for Visit: ");
		TextArea concernArea= new TextArea();
		   
		Label medHistoryLabel = new Label("Medical History: ");
		TextArea medHistoryArea= new TextArea();
		//medHistoryArea.setPrefSize(100, 5); 
	
		over12Area.setPrefSize(10, 10); 
		//allergyArea.setPrefSize(1, 1); 
		resultsArea.setPrefSize(300, 20); 
		concernArea.setPrefSize(300, 20); 
		
		HBox medHistoryBox= new HBox(10);
		medHistoryBox.setPadding(new Insets(10));
		HBox physicalResultsBox= new HBox();
		physicalResultsBox.setPadding(new Insets(10));
		HBox concernBox= new HBox();
		concernBox.setPadding(new Insets(10));

		medHistoryBox.getChildren().addAll(medHistoryLabel,medHistoryArea);
		physicalResultsBox.getChildren().addAll(resultsLabel,resultsArea);
		concernBox.getChildren().addAll(concernLabel,concernArea);
	
		Label weightLabel = new Label("Weight");
		Label heightLabel = new Label("Height");
		Label bodyTemperatureLabel = new Label("Temperature");
		Label bloodPressureLabel = new Label("Blood Pressure");
	
		
		TextField patientWeightArea= new TextField();
		TextField patientBPArea= new TextField();
		TextField patientHeightArea= new TextField();
		TextField patientTempArea= new TextField();

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
		saveButton= new Button("Save");
		
		Label currDateLabel= new Label(formattedDate);
		currDateLabel.setStyle("-fx-font-family: roboto; -fx-font-weight: bold; -fx-font-size: 20; -fx-padding: 0, 0, 0, 20");
					
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
		infoBox.getChildren().addAll(ptIDLabel,currDateLabel);
		
		VBox visitSummaryBox= new VBox();
		visitSummaryBox.getStylesheets().add(getClass().getResource("application.css").toString());
		saveButton.getStyleClass().add("BlueButton");
	   	saveButton.getStyleClass().add("buttonSize");
		
		visitSummaryBox.getChildren().addAll(infoBox, vitalResultsLabel, vitalsGridPane, physicalResultsBox, concernBox, medHistoryBox, saveButton);
		visitSummaryBox.setPadding(new Insets(0, 50, 55, 55));

		Medication baseScript= new Medication("","","");

		saveButton.setOnAction(event -> 
		{
			
			try {
		        double weight = Double.parseDouble(patientWeightArea.getText());
		        double height = Double.parseDouble(patientHeightArea.getText());
		        double temp = Double.parseDouble(patientTempArea.getText());

		        Visits patientVisit = new Visits(formattedDate, weight, height, patientBPArea.getText(), temp, resultsArea.getText(), concernArea.getText(),baseScript, allergyArea.getText(),medHistoryArea.getText() );
		        patient.addVisit(patientVisit);
		        Label successLabel=new Label("Successfully saved Visit Information");
		        visitSummaryBox.getChildren().addAll(successLabel);
		        Patient.writePatientToDatabase(patient);
		     
		    } catch (Exception e) {
		    	Label failLabel=new Label("Failed to save Visit Information, Input entered incorrectly");
		        visitSummaryBox.getChildren().addAll(failLabel);
		    }
			
		});
		
		
		summaryContainer.setTop(titleBox);
		summaryContainer.setCenter(visitSummaryBox);
		
	
		Scene scene = new Scene(summaryContainer);
		summaryWindow.setScene(scene);
				
		try 
		{
			Patient.writePatientToDatabase(patient);
		} 
		catch (IOException e) 
		{
		}
		
		summaryWindow.show();
		
		
		
	}
	
	private void visitScreen(Patient patient, int index) {
		
		Stage summaryWindow = new Stage();
		summaryWindow.setWidth(800);
		summaryWindow.setHeight(500);
		
		//	Display Information
		VBox summaryContainer = new VBox();
		//summaryContainer.setPadding(new Insets(0, 0, 15, 15));
		
		Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

		
		HBox topBox= new HBox(20);
    	Label medFXLabel= new Label("MedFX");
    	 medFXLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24)); 
    	 medFXLabel.setTextFill(javafx.scene.paint.Color.WHITE);
        signOutButton = new Button("Sign Out");
        signOutButton.getStyleClass().add("BlueButton");
        signOutButton.getStyleClass().add("buttonSize");
        
	    signOutButton.setOnAction(new ButtonHandler());
	    
        patientButton = new Button("Patients");
        patientButton.getStyleClass().add("BlueButton");
        patientButton.getStyleClass().add("buttonSize");
        patientButton.setOnAction(new ButtonHandler());

        Visits visit = patient.getVisits().get(index);
        


        topBox.getChildren().addAll(medFXLabel, patientButton, spacer, signOutButton);
        topBox.getStylesheets().add(getClass().getResource("application.css").toString());
        topBox.setStyle("-fx-background-color: #39C0EA;");
        topBox.setPadding(new Insets(10)); 
		
		Label visitDateLabel = new Label("Visit " + visit.getDate());
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
		
		 Label XWLabel= new Label("" + visit.getWeight());
		 Label XHLabel= new Label("" + visit.getHeight());
		 Label XTLabel= new Label("" + visit.getBodyTemperature());
		 Label XBPLabel= new Label(visit.getBloodPressure());
		
		
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
		vitalsGridPane.add(XWLabel, 1, 0);
		vitalsGridPane.add(heightLabel, 2, 0);
		vitalsGridPane.add(XHLabel, 3, 0);
		vitalsGridPane.add(bodyTemperatureLabel, 4, 0);
		vitalsGridPane.add(XTLabel, 5, 0);
		vitalsGridPane.add(bloodPressureLabel, 0, 1);
		vitalsGridPane.add(XBPLabel, 1, 1);
		
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
	    Color textColor = Color.rgb(0x39, 0xC0, 0xEA);
	    medFXLabel.setTextFill(textColor);
	    
	    patientButton = new Button("Patients");
	    patientButton.getStyleClass().add("BlueButton");
        patientButton.setOnAction(new ButtonHandler());

        patientButton.getStyleClass().add("buttonSize");
	    Button messageButton = new Button("Messages");
	    messageButton.getStyleClass().add("BlueButton");
	    messageButton.getStyleClass().add("buttonSize");
	    signOutButton = new Button("Sign Out");
	    signOutButton.getStyleClass().add("BlueButton");
	    signOutButton.getStyleClass().add("buttonSize");
	    signOutButton.setOnAction(new ButtonHandler());
	    
	    Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

		HBox titleBox= new HBox(10);
		titleBox.getStylesheets().add(getClass().getResource("application.css").toString());

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

				// Add message input & send message button to textInputContainer
				textInputContainer.getChildren().addAll(textInputArea, sendButton);

				//	Add the messageViewContainer and textInputContainer to the messageContainer
				messageContainer.getChildren().addAll(messageViewContainer, textInputContainer);

				// Adds side container and message container to display container
				displayContainer.getChildren().addAll(contactsContainer, messageContainer);

				// Adds all components together
				mainContainer.getChildren().addAll(titleBox, displayContainer);

//				PATIENT CONTACT CONTAINER ----------------
				//	This will be used to show patient contacts in the contacts container
				//	We could pass in a patient object to get all of this information
				
				//	Displays all conversations with patients
				if(Nurse.getPatientList().size() != 0) {
					for(Patient patient : Nurse.getPatientList()) {
						VBox patientContact = new VBox();
						patientContact.setMaxWidth(220);
						patientContact.setMaxHeight(50);
						patientContact.setStyle("-fx-background-color: white; -fx-spacing: 5; -fx-padding: 8, 8, 8, 8; -fx-background-radius: 10");
						
						//	Contact Labels 
						Button pName = new Button(patient.getPersonalInfo().getFirstName() + " " + patient.getPersonalInfo().getLastName());
						Label messagePreview = new Label(); //	Will be the last send message (either from patient or doctor)

						//	Check for previous message
						if(patient.getMessages().size() != 0) {
							messagePreview.setText(patient.getMessages().get(patient.getMessages().size() - 1).getContents());
						}else {
							messagePreview.setText("...");
						}
						//	Set styling
						pName.setStyle("-fx-text-fill: black; -fx-font-size: 15; -fx-background-color: white");
						messagePreview.setStyle("-fx-text-fill: grey; -fx-font-size: 13;");
						messagePreview.setMaxWidth(220);
						
						//	pName button handling
						pName.setOnAction(new EventHandler<ActionEvent>() {
							public void handle(ActionEvent e) {
								//	Set the send button to the correct patient
								sendButton.setOnAction(new EventHandler<ActionEvent>() {
									public void handle(ActionEvent e) {
										//	Creating a new message
										Message newMessage = new Message("Doctor", "Patient", textInputArea.getText());
										
										//	Add Message to patient arraylist
										patient.getMessages().add(newMessage);
										
										//	Save the patient object
										try {
											//	Write new patient object to the database
											Patient.writePatientToDatabase(patient);
											
											//	Clear the text input
											textInputArea.clear();
											
											//	Show the message view
											updateMessageView(patient);
										}catch(IOException ie) {
											
										}
									}
								});
								
								updateMessageView(patient);								
							}
						});
						
						//	Put the name and preview into the patientContact container
						patientContact.getChildren().addAll(pName, messagePreview);
						
						//	Add the patientContact to the contacts container
						contactsContainer.getChildren().add(patientContact);	
					}
				}	
				
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
			
			if (patientButton.isArmed())
			{
				try
				{
					
					SceneController.switchToNurseView(e, nurse); 
				}
				catch (Exception ex)
				{
					
				}
				
				
			}
			
			
		}
	}
	
	private void updateMessageView(Patient patient) {		
		//	Clear the message view before populating
		messageViewContainer.getChildren().clear();
		
		//	Updates Message View Container with messages
		if(patient.getMessages().size() != 0) {
			for(Message message : patient.getMessages()) {
				if(message.getSender().equals("Doctor")) {
					//	Show the sender message style
					//	~ Message bubble (Sender) ~
					VBox messageBubbleContainerSender = new VBox();
					messageBubbleContainerSender.setMaxWidth(300);
					messageBubbleContainerSender.setStyle("-fx-background-color: #39C0EA;\r\n"
							+ "	-fx-background-radius: 15;\r\n"
							+ "	-fx-font-family: 'Roboto';\r\n"
							+ "	-fx-font-size: 15;\r\n"
							+ "	-fx-alignment: top-left;");
					
					//	Text for Message
					Label messageTextSender = new Label();
					messageTextSender.setText(message.getContents());
					messageTextSender.setStyle("-fx-text-fill: white;\r\n"
							+ "	-fx-padding: 8, 8, 8, 8;\r\n"
							+ "	-fx-border-radius: 20;\r\n"
							+ "	-fx-wrap-text: true;");
							
					//	Puts the text into the message bubble
					messageBubbleContainerSender.getChildren().addAll(messageTextSender);
							
					//	Add to messageViewContainer
					messageViewContainer.getChildren().add(messageBubbleContainerSender);
				}else if(message.getSender().equals("Patient")) {
					//	Show the receiver message style
					//	~ Message bubble (Sender) ~
					VBox messageBubbleContainerReceiver = new VBox();
					messageBubbleContainerReceiver.setMaxWidth(300);
					messageBubbleContainerReceiver.setStyle("-fx-background-color: white;\r\n"
							+ "	-fx-background-radius: 15;\r\n"
							+ "	-fx-font-family: 'Roboto';\r\n"
							+ "	-fx-font-size: 15;\r\n"
							+ "	-fx-alignment: top-left;");
							
					//	Text for Message
					Label messageTextReceive = new Label();
					messageTextReceive.setText(message.getContents());
					messageTextReceive.setStyle("-fx-text-fill: black;\r\n"
							+ "	-fx-padding: 8, 8, 8, 8;\r\n"
							+ "	-fx-border-radius: 20;\r\n"
							+ "	-fx-wrap-text: true;");
					
					//	Puts the text into the message bubble
					messageBubbleContainerReceiver.getChildren().addAll(messageTextReceive);
							
					//	Add to messageViewContainer
					messageViewContainer.getChildren().add(messageBubbleContainerReceiver);
				}
			}
		}
	}
	
}
