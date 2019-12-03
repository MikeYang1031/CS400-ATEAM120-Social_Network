package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
	// store any command-line arguments that were entered.
	// NOTE: this.getParameters().getRaw() will get these also
	private List<String> args;

	private static final int WINDOW_WIDTH = 800;
	private static final int WINDOW_HEIGHT = 600;
	private static final String APP_TITLE = "Social Network";

	@Override
	public void start(Stage primaryStage) throws Exception {

	    // Main layout is Border Pane example (top,left,center,right,bottom)
	    BorderPane border = new BorderPane();
		
	    // set background color for the pane
            border.setStyle("-fx-background-color: #BFBFBF");
        
            // set feedback
            HBox feedback = new HBox();
	    Label feedLabel  = new Label("Feedback");
            TextField feedText = new TextField();
            Button feedButton =  new Button("Submit");
        
            feedLabel.setStyle("-fx-background-color: orange");
            feedLabel.setPrefSize(60, 25);
            feedText.setPrefWidth(650);
            feedButton.setStyle("-fx-background-color: orange");
            feedback.setSpacing(10);
            feedback.getChildren().addAll(feedLabel, feedText, feedButton);
            border.setBottom(feedback);
        
            FileInputStream inputImage = null;
	    
	    // set image
            try {
                inputImage = new FileInputStream("/Users/yuanxixie/ATEAM.png");
            } catch (FileNotFoundException e) {
                   // TODO Auto-generated catch block
                   e.printStackTrace();
            }
           
            Image image = new Image(inputImage);
            ImageView imageView = new ImageView(image);

            imageView.setFitHeight(100);
            imageView.setFitWidth(300);
           
            border.setLeft(imageView);
           
		
           // Add the vertical box to the center of the root pane
     	   //root.setCenter(vbox);
     	   Scene mainScene = new Scene(border, WINDOW_WIDTH, WINDOW_HEIGHT);

     	   // Add the stuff and set the primary stage
     	   primaryStage.setTitle(APP_TITLE);
     	   primaryStage.setScene(mainScene);
     	   primaryStage.show();
	   }
		               
		
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
