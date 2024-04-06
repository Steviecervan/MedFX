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
	     
	     
	     
	     	     	     	 
}
	
}