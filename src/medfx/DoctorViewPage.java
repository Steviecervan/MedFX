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
	private Doctor doctor;
	
	public DoctorViewPage(Doctor doctor) throws IOException {
		
		this.doctor = doctor;
	
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
		 
		 Label patientsLabel= new Label("Patients");
		 Font largeBoldFont = Font.font("Arial", FontWeight.BOLD, 20); 
	     patientsLabel.setFont(largeBoldFont);
		 TextField patientSearch= new TextField();
		 
		 patientSearch.setPromptText("Search Patient");
		 Button searchButton= new Button("Search");
		 Button newPatientButton= new Button("New Patient");
	 
		 HBox searchBarBox= new HBox(10);
		 searchBarBox.getChildren().addAll(patientsLabel, patientSearch, searchButton, newPatientButton);
		 searchBarBox.setPadding(new Insets(10,10,10,10));
		
		Label patientNameLabel= new Label("Patient Name");
        patientNameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16)); 
        
       
        
        HBox individualPatientBox= new HBox(5);
	    Label nameLabel= new Label("last-first-DOB");
	    Button viewButton= new Button("View");
	    Button msgsButton= new Button("Message");
	    
	    individualPatientBox.setPadding(new Insets(10,10,10,10));

	    individualPatientBox.getChildren().addAll(nameLabel,spacer, viewButton,msgsButton);
	    
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
	    viewButton.setOnAction(event -> newPatientScreen());	
	    //viewButton.setOnAction(event -> visitScreen());	     
	    
}
	//need to put Patient p as parameter here !!!!!!!!!!!!!!!!!!!!!!!!!!!!
	private void newPatientScreen() {
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
        medHistoryGrid.getChildren().addAll(medHistoryLabel);

        GridPane allergyGrid= new GridPane();
        Label allergyLabel= new Label("Allergies");
        allergyGrid.getChildren().addAll(allergyLabel);

        GridPane immunizationGrid= new GridPane();
        Label immunizationLabel= new Label("Immunization History");
        immunizationGrid.getChildren().addAll(immunizationLabel);

        GridPane medicationGrid= new GridPane();
        Label medicationLabel= new Label("Medication History");
        medicationGrid.getChildren().addAll(medicationLabel);
        
        
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
		    topBox.setStyle("-fx-background-color: #39C0EA;");
		    Label medFXLabel = new Label("MedFX");
		    medFXLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24)); 
		    medFXLabel.setTextFill(javafx.scene.paint.Color.WHITE);
		    Button patientButton = new Button("Patients");
		    Button messageButton = new Button("Messages");
		    signOutButton = new Button("Sign Out");
		    

		    Label visitTopLabel = new Label("Visit MM-DD-YYYY");
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
		    
		   HBox medNameBox= new HBox();
		   HBox medStrengthBox= new HBox();
		   HBox medDosageBox= new HBox();
		  
		    Label medName=new Label("Medication Name:");
		    Label medStrength=new Label("Strength:");
		    Label medDosage=new Label("Dosage:");
		    
		    TextArea medNameArea= new TextArea();
		    TextArea medStrengthArea= new TextArea();
		    TextArea medDosageArea= new TextArea();
		    medNameArea.setMaxWidth(200);
		    medNameArea.setMaxHeight(2);
		    medStrengthArea.setMaxWidth(200);
		    medStrengthArea.setMaxHeight(2);
		    medDosageArea.setMaxWidth(200);
		    medDosageArea.setMaxHeight(2);

		    medNameBox.getChildren().addAll(medName, medNameArea);
		    medStrengthBox.getChildren().addAll(medStrength, medStrengthArea);
		    medDosageBox.getChildren().addAll(medDosage, medDosageArea);

		    Button orderButton=new Button("Order");
		    
		    TextArea concernArea= new TextArea();
		    TextArea physicalResultArea= new TextArea();
		    
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
		    
		    Label XWLabel= new Label("XXX");
		    Label XHLabel= new Label("XXX");
		    Label XTLabel= new Label("XXX");
		    Label XBPLabel= new Label("XXX");
		    
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
		    prescribeBox.getChildren().addAll(prescribeLabel, medNameBox, medStrengthBox, medDosageBox, orderButton);
		   
		    medNameBox.setAlignment(Pos.CENTER);
		    medStrengthBox.setAlignment(Pos.CENTER);
		    medDosageBox.setAlignment(Pos.CENTER);
		    
		    VBox visitInfoBox = new VBox();
		    visitInfoBox.setAlignment(Pos.CENTER); 
		    visitInfoBox.setSpacing(20); 
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