//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: HelloFX
// Course: CS 400
// Author: Zonglin Yang, Kunlun Wang, Yuanxi Xie
// Email: zyang439@wisc.edu yxie85@wisc.edu
//
//////////////////////////////////////////////////////////////////////////////

package application;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    // store any command-line arguments that were entered.
    // NOTE: this.getParameters().getRaw() will get these also

    @FXML
    public Button close = new Button("Done");
    // Launch the application
    public void start(Stage stage) {
        BorderPane border = new BorderPane();
        
        // set background color for the pane
        border.setStyle("-fx-background-color: darkgray");
        
        // set feedback
        HBox feedback = new HBox();
		Label feedLabel  = new Label("Feedback");
        TextField feedText = new TextField();
        Button feedButton =  new Button("Submit");
        
        feedLabel.setStyle("-fx-background-color: orange");
        feedLabel.setPrefSize(60, 25);
        feedButton.setStyle("-fx-background-color: orange");
        feedback.setSpacing(10);
        feedback.getChildren().addAll(feedLabel, feedText, feedButton);
        

        // set title for the stage
        stage.setTitle("the title for stage");

        // create a label jarvis
        VBox vb = new VBox();
        Label L = new Label("CS400 MyFirstJavaFX");
        vb.getChildren().addAll(L);
        vb.setAlignment(Pos.CENTER);

        String car_types[] =
            {"seden", "coupe", "hatchback", "van", "pickup truck",};

        // Create a combo box
        HBox hb = new HBox();
        ComboBox<String> cb =
            new ComboBox<String>(FXCollections.observableArrayList(car_types));
        hb.getChildren().addAll(cb);
        hb.setAlignment(Pos.CENTER);

        // create a image
        Image i = new Image("pic.jpg");

        ImageView iw = new ImageView(i);
        iw.setFitHeight(400);
        iw.setFitWidth(400);

        // Bottom button
        VBox vb2 = new VBox();
        vb2.getChildren().addAll(close);
        vb2.setAlignment(Pos.CENTER);

        // create a color picker
        HBox hb2 = new HBox();
        ColorPicker cp = new ColorPicker(Color.BLUE);
        hb2.getChildren().addAll(cp);
        hb2.setAlignment(Pos.CENTER);

        border.setTop(vb);
        border.setBottom(vb2);
        border.setLeft(hb);
        border.setRight(hb2);
        border.setCenter(iw);

        // Create a scene
        Scene scene = new Scene(border, 800, 600);

        // Set the scene
        stage.setScene(scene);

        stage.show();

    }
//    @FXML
//    public void handleCloseButtonAction(ActionEvent event) {
//        
//        Stage stage = (Stage)close.getScene().getWindow();
//        stage.close();
//    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
