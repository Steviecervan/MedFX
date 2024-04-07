package medfx;

import java.io.IOException;

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
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class NurseViewPage extends VBox{
private Scene visitScene;
	
	public NurseViewPage() throws IOException {	
	
	Label medFXLabel= new Label("MedFX");
	    medFXLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24)); 
	    medFXLabel.setTextFill(javafx.scene.paint.Color.WHITE);
	    
	    Button patientButton = new Button("Patients");
	    Button messageButton = new Button("Messages");
	    Button signOutButton = new Button("Sign Out");

		 HBox titleBox= new HBox(10);
	     titleBox.setStyle("-fx-background-color: #ADD8E6;");
		 
		 titleBox.getChildren().addAll(medFXLabel, patientButton,messageButton,signOutButton);
		 titleBox.setPadding(new Insets(10));
		 
		 
		 Label patientsLabel= new Label("Patients");
		 Font largeBoldFont = Font.font("Arial", FontWeight.BOLD, 20); 
	     patientsLabel.setFont(largeBoldFont);
		 TextField patientSearch= new TextField();
		 patientSearch.setPromptText("Search Patient");
		 Button searchButton= new Button("Search");
		 Button newPatientButton= new Button("New Patient");
		 Button checkInButton= new Button("Check In");
		 
		 HBox searchBarBox= new HBox(10);
		 searchBarBox.setPadding(new Insets(10)); 
		 searchBarBox.getChildren().addAll(patientsLabel, patientSearch, searchButton, newPatientButton);
		 searchBarBox.setPadding(new Insets(10));
		
		Label patientNameLabel= new Label("Patient Name");
		
		VBox patientBox= new VBox();
		patientBox.getChildren().addAll(patientNameLabel, checkInButton);
		patientBox.setPadding(new Insets(10));
		
		 		
		VBox wholeSearchBox= new VBox();
		wholeSearchBox.getChildren().addAll(searchBarBox, patientBox);
	     
	     BorderPane mainPane= new BorderPane();
	     mainPane.setCenter(wholeSearchBox);
	     mainPane.setTop(titleBox);
	     this.getChildren().add(mainPane); 
	     
	    newPatientButton.setOnAction(event -> newPatientScreen());	     
	    checkInButton.setOnAction(event -> checkInScreen());	     

}
	private void newPatientScreen() {
		HBox topBox= new HBox(500);
    	Label medFXLabel= new Label("MedFX");
    	 medFXLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24)); 
    	 medFXLabel.setTextFill(javafx.scene.paint.Color.WHITE);
        Button signOutButton = new Button("Sign Out");
        topBox.getChildren().addAll(medFXLabel, signOutButton);
        topBox.setStyle("-fx-background-color: #ADD8E6;");
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
		VBox summaryContainer = new VBox();
		//summaryContainer.setPadding(new Insets(0, 0, 15, 15));
		
		HBox topBox= new HBox(500);
    	Label medFXLabel= new Label("MedFX");
    	 medFXLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24)); 
    	 medFXLabel.setTextFill(javafx.scene.paint.Color.WHITE);
        Button signOutButton = new Button("Sign Out");
        topBox.getChildren().addAll(medFXLabel, signOutButton);
        topBox.setStyle("-fx-background-color: #ADD8E6;");
        topBox.setPadding(new Insets(10)); 
		
		Label visitNameLabel = new Label("Last-First-DOB");
		visitNameLabel.setStyle("-fx-font-family: roboto; -fx-font-weight: bold; -fx-font-size: 20; -fx-padding: 0, 0, 0, 20");
		
		Label dateLabel = new Label("MM/DD/YYYY");
		dateLabel.setStyle("-fx-font-family: roboto; -fx-font-weight: bold; -fx-font-size: 20; -fx-padding: 0, 0, 0, 20");
		
	
		
		Label vitalResultsLabel = new Label("Vitals");
		vitalResultsLabel.setStyle("-fx-font-family: roboto; -fx-font-weight: bold; -fx-font-size: 15; -fx-padding: 0, 5, 0, 5");
						
		Label over12Label = new Label("Is Patient Over 12?");
		over12Label.setStyle("-fx-font-family: roboto; -fx-font-weight: bold; -fx-font-size: 15; -fx-padding: 0, 5, 0, 5");
		
		Label allergyLabel = new Label("Known Allergies");
		allergyLabel.setStyle("-fx-font-family: roboto; -fx-font-weight: bold; -fx-font-size: 15; -fx-padding: 0, 5, 0, 5");
		
		Label concernLabel = new Label("Concerns/Reasons for Visit");
		concernLabel.setStyle("-fx-font-family: roboto; -fx-font-weight: bold; -fx-font-size: 15; -fx-padding: 0, 5, 0, 5");
		
		HBox over12Box= new HBox();
		over12Box.getChildren().addAll(over12Label);
		
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

		 patientWeightArea.setPrefSize(2, 2); 
	        patientBPArea.setPrefSize(2, 2);
	        patientHeightArea.setPrefSize(2, 2);
	        patientTempArea.setPrefSize(2, 2);

		
		
		TextArea healthConcernTextArea = new TextArea();				
		healthConcernTextArea.setEditable(false);
		
		TextArea physicalResultsTextArea = new TextArea();
		physicalResultsTextArea.setEditable(false);
		
		GridPane vitalsGridPane = new GridPane();
		vitalsGridPane.setAlignment(Pos.CENTER_LEFT);
		
		vitalsGridPane.setVgap(5);
		vitalsGridPane.setHgap(5);
		
		
		vitalsGridPane.add(heightLabel, 0, 0);
		vitalsGridPane.add(patientHeightArea, 1, 0);
		vitalsGridPane.add(weightLabel, 0, 1);
		vitalsGridPane.add(patientWeightArea, 1, 1);
		vitalsGridPane.add(bodyTemperatureLabel, 0, 2);
		vitalsGridPane.add(patientTempArea, 1, 2);
		vitalsGridPane.add(bloodPressureLabel, 0, 3);
		vitalsGridPane.add(patientBPArea, 1, 3);
		
		HBox infoBox= new HBox(50);
		infoBox.getChildren().addAll(visitNameLabel,dateLabel);
		
		VBox visitSummaryBox= new VBox();
		visitSummaryBox.getChildren().addAll(infoBox, vitalResultsLabel, vitalsGridPane);
		visitSummaryBox.setPadding(new Insets(30));
		
		summaryContainer.getChildren().addAll(topBox, visitSummaryBox);
		
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
        topBox.setStyle("-fx-background-color: #ADD8E6;");
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

}
