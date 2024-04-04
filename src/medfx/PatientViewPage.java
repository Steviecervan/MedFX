//	Author: Stevie Cervantes (idk if we're doing this)

package medfx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
	
	public PatientViewPage() {		
		this.setPadding(new Insets(15,15,15,15));

		//	General View -----------------------------------------------------------------------------------------
		//	This view is static
		Label welcomeHeaderLabel = new Label("Welcome, " + pName);
		HBox displayContainer = new HBox();
		displayContainer.setPadding(new Insets(15,0,0,0));
		
		VBox navigationContainer = new VBox();
		navigationContainer.setPrefWidth(300);
		navigationContainer.setSpacing(15);
		
		patientInfoContainer = new VBox();
		patientInfoContainer.setMinWidth(600);
		
		Button myInfoButton = new Button("My Info");
		Button myVisitsButton = new Button("My Visits");
		Button messagesButton = new Button("Messages");
		
		navigationContainer.getChildren().addAll(myInfoButton, myVisitsButton, messagesButton);
		
		//	Info View -----------------------------------------------------------------------------------------
		//	This view shows the patients information
		
		//	Labels (does not have user information)
		Label infoHeaderLabel = new Label("My Info");
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
		Button phoneNumberButton = new Button("Change");
		Button emailButton = new Button("Change");
		Button pharmacyButton = new Button("Change");
		
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
		
		patientInfoContainer.getChildren().add(infoGridPane);
		
		//	Visits View -----------------------------------------------------------------------------------------
		//	This view shows the patient's visits
		
		//	Labels
		Label visitsHeaderLabel = new Label("My Visits");
		visitsHeaderLabel.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20)); 
		visitsHeaderLabel.setPadding(new Insets(15, 15, 15, 15));
		
		//	Examination Container (template)
		//	When implementing the database, we can use this template for each visit object
		HBox examinationContainer = new HBox();		
		examinationContainer.setPadding(new Insets(15, 15, 15, 15));
		
		Label examinationDateLabel = new Label("Examination Date: MM/DD/YYYY");
		examinationDateLabel.setMinWidth(400);
		Button summaryButton = new Button("Summary");
				
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
				visitDateLabel.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20)); 
				visitDateLabel.setPadding(new Insets(0, 5, 5, 0));
				
				Label vitalResultsLabel = new Label("Vital Results");
				vitalResultsLabel.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 15));
				vitalResultsLabel.setPadding(new Insets(5, 5, 5, 0));
				
				Label healthConcernsLabel = new Label("Health Concerns");
				healthConcernsLabel.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 15));
				healthConcernsLabel.setPadding(new Insets(5, 5, 5, 0));
				
				Label physicalResultsLabel = new Label("Physical Results");
				physicalResultsLabel.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 15));
				
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
		//	Come back to match up w/ Carly's message view
		
		//	Add everything together
		displayContainer.getChildren().addAll(navigationContainer, patientInfoContainer);
		this.getChildren().addAll(welcomeHeaderLabel, displayContainer);
	}
}
