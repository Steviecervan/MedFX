package medfx;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class System extends Application{
	
	public void start(Stage primaryStage) throws IOException // IOException because of the fileinputstream in user main page
	{
		StackPane root = new StackPane();
        VBox mainMenu = new UserMainPage(); // this is what the main screen will be called, you can change it to fit your testing purposes
        root.getChildren().add(mainMenu);

        Scene scene = new Scene(root, 800, 500);
        primaryStage.setTitle("MedFX");
        primaryStage.setScene(scene);
        primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
