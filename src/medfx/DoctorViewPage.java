package medfx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Priority;
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
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileReader;
import javafx.scene.control.TextInputDialog;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class DoctorViewPage extends VBox
{
	private Scene visitScene;
	
	public DoctorViewPage() throws IOException {	
	
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
		 
		 HBox searchBarBox= new HBox(10);
		 searchBarBox.setPadding(new Insets(10)); 
		 searchBarBox.getChildren().addAll(patientsLabel, patientSearch, searchButton, newPatientButton);
		 searchBarBox.setPadding(new Insets(10));
		
		Label patientNameLabel= new Label("Patient Name");
		
		VBox patientBox= new VBox();
		patientBox.getChildren().addAll(patientNameLabel);
		patientBox.setPadding(new Insets(10));

		
		VBox wholeSearchBox= new VBox();
		wholeSearchBox.getChildren().addAll(searchBarBox, patientBox);
	     
	     BorderPane mainPane= new BorderPane();
	     mainPane.setCenter(wholeSearchBox);
	     mainPane.setTop(titleBox);
	     this.getChildren().add(mainPane); 
	    newPatientButton.setOnAction(event -> newPatientScreen());	     	     	     	 
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
	
	private void visitScreen() {
		
		   BorderPane visitPane = new BorderPane();
		    HBox topBox = new HBox(10);
		    topBox.setPadding(new Insets(10));
		    topBox.setStyle("-fx-background-color: #ADD8E6;");
		    Label medFXLabel = new Label("MedFX");
		    medFXLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24)); 
		    medFXLabel.setTextFill(javafx.scene.paint.Color.WHITE);
		    Button patientButton = new Button("Patients");
		    Button messageButton = new Button("Messages");
		    Button signOutButton = new Button("Sign Out");

		    Label visitTopLabel = new Label("Visit");
		    Font largeBoldFont = Font.font("Arial", FontWeight.BOLD, 20); 
		    visitTopLabel.setFont(largeBoldFont);

		    GridPane vitalGrid = new GridPane();
		    VBox concernBox = new VBox();
		    VBox physicalResultBox = new VBox();
		    VBox prescribeBox = new VBox();

		    Label vitalResult = new Label("Vital Results");
		    Label concernLabel = new Label("Health Concerns");
		    Label physicalResultLabel = new Label("Physical Results");
		    Label prescribeLabel = new Label("Prescribe Medication");

		    // Create new label instances for each container
		    Label vitalResultCopy = new Label("Vital Results");
		    Label concernLabelCopy = new Label("Health Concerns");
		    Label physicalResultLabelCopy = new Label("Physical Results");
		    Label prescribeLabelCopy = new Label("Prescribe Medication");

		    vitalGrid.getChildren().addAll(vitalResult);
		    concernBox.getChildren().addAll(concernLabel);
		    physicalResultBox.getChildren().addAll(physicalResultLabel);
		    prescribeBox.getChildren().addAll(prescribeLabel);

		    VBox visitInfoBox = new VBox();
		    visitInfoBox.setAlignment(Pos.CENTER); 
		    visitInfoBox.setSpacing(40); 
		    visitInfoBox.getChildren().addAll(visitTopLabel,vitalGrid, concernBox, physicalResultBox, prescribeBox);

		    vitalGrid.setAlignment(Pos.CENTER);
		    concernBox.setAlignment(Pos.CENTER);
		    physicalResultBox.setAlignment(Pos.CENTER);
		    prescribeBox.setAlignment(Pos.CENTER);

		    topBox.getChildren().addAll(medFXLabel, patientButton, messageButton, signOutButton);
		    topBox.setPadding(new Insets(10));

		    visitPane.setTop(topBox);
		    visitPane.setCenter(visitInfoBox);
		    Scene visitScene = new Scene(visitPane, 800, 500);
		    Stage visitStage = new Stage();
		    visitStage.setScene(visitScene);
		    visitStage.show();
	}
	
	
}