package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
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
        Label feedLabel = new Label("Feedback");
        TextField feedText = new TextField();
        Button feedButton = new Button("Submit");

        feedLabel.setStyle("-fx-background-color: #FFC000");
        feedLabel.setPrefSize(60, 25);
        feedText.setPrefWidth(650);
        feedButton.setStyle("-fx-background-color: #FFC000; "
            + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );");
        feedback.setSpacing(10);
        feedback.getChildren().addAll(feedLabel, feedText, feedButton);
        border.setBottom(feedback);

      

       // border.setLeft(imageView);

        
        VBox vb2 = new VBox();
        // Label L = new Label("CS400 MyFirstJavaFX");

        // Textfield 1 for user
        TextField ld = new TextField();
        ld.setPrefHeight(30);
      
        ld.setPrefWidth(300);
        ld.setPrefColumnCount(10);
        ld.setPadding(new Insets(0, 0, 0, 0));
        

        

//         Empty space
        Region r2 = new Region();
        r2.setPrefHeight(30);
        r2.setPrefWidth(200);

        // Textfield 2
        TextField ex = new TextField();
        ex.setPrefHeight(30);
        ex.setPrefWidth(300);
        ex.setPrefColumnCount(10);
        ex.setPadding(new Insets(0, 0, 0, 0));
        
        Button dis = new Button("Display");
      dis.setMinSize(200, 40);
      dis.setTranslateY(0);
      dis.setTranslateX(0);
      dis.setStyle("-fx-background-color: #FFC000; "
          + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );");

    Button exp = new Button("Export");
    exp.setMinSize(100, 30);
    exp.setTranslateY(0);
    exp.setTranslateX(0);
    exp.setStyle("-fx-background-color: #FFC000; "
        + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );");
    
  Button load = new Button("Load");
  load.setMinSize(100, 30);
  load.setTranslateY(0);
  load.setTranslateX(0);
  load.setStyle("-fx-background-color: #FFC000; "
      + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );");
      
     // Vbox2
        vb2.setSpacing(10);
        vb2.setPadding(new Insets(0, 70, 0, 0));
        vb2.getChildren().addAll(ld,load,ex,exp,r2,dis);
        vb2.setAlignment(Pos.CENTER);

        border.setRight(vb2);


        // Add the vertical box to the center of the root pane
        // root.setCenter(vbox);
        Scene mainScene = new Scene(border, WINDOW_WIDTH, WINDOW_HEIGHT);

        // Add the stuff and set the primary stage
        primaryStage.setTitle(APP_TITLE);
        primaryStage.setScene(mainScene);
        primaryStage.show();

        // set top menu
        Button button1 = new Button("Back");
        Button button2 = new Button("Undo");
        Button button3 = new Button("Redo");
        Button button4 = new Button("Save And Quit");
        Button button5 = new Button("Quit Without Save");

        button1.setStyle("-fx-background-color: #FFC000; "
            + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );");
        button2.setStyle("-fx-background-color: #FFC000; "
            + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );");
        button3.setStyle("-fx-background-color: #FFC000; "
            + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );");
        button4.setStyle("-fx-background-color: #FFC000; "
            + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );");
        button5.setStyle("-fx-background-color: #FFC000; "
            + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );");

        button1.setTranslateX(450);
        button2.setTranslateX(450);
        button3.setTranslateX(450);
        button4.setTranslateX(450);
        button5.setTranslateX(450);

        HBox hbox = new HBox(button1, button2, button3, button4, button5);
        border.setTop(hbox);

        VBox vb = new VBox();
        // Label L = new Label("CS400 MyFirstJavaFX");

        FileInputStream inputImage = null;

        try {
            inputImage = new FileInputStream("ATEAM.png");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Image image = new Image(inputImage);
        ImageView imageView = new ImageView(image);

        imageView.setFitHeight(100);
        imageView.setFitWidth(300);
        
        // Textfield 1 for user
        TextField t1 = new TextField();
        t1.setPrefHeight(40);
        // t1.setMaxHeight(40);
        t1.setPrefWidth(200);
        t1.setPrefColumnCount(10);
        t1.setPadding(new Insets(0, 0, 0, 0));
        String username = t1.getText();

        // HBox containing all buttons about user
        HBox userButtons = new HBox();
        userButtons.setSpacing(5);
        Button addUser = new Button("Add User");
        addUser.setStyle("-fx-background-color: #FFC000; "
            + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );");
        Button removeUser = new Button("Remove User");
        removeUser.setStyle("-fx-background-color: #FFC000; "
            + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );");
        userButtons.getChildren().addAll(addUser, removeUser);
        userButtons.setAlignment(Pos.CENTER_RIGHT);

        // Empty space
        Region r = new Region();
        r.setPrefHeight(30);
        r.setPrefWidth(200);

        // Textfield 2
        TextField t2 = new TextField();
        t2.setPrefHeight(40);
        t2.setPrefWidth(150);
        t2.setPrefColumnCount(10);
        t2.setPadding(new Insets(0, 0, 0, 0));
        String friend1 = t2.getText();

        // HBox for friendship
        HBox friendship = new HBox();
        friendship.setSpacing(5);
        FileInputStream input = new FileInputStream("FriendshipArrow.png");
        Image image2 = new Image(input); // ImageView of an arrow of friendship
        ImageView imageView2 = new ImageView(image2);
        imageView2.setFitHeight(70);
        imageView2.setFitWidth(50);
        VBox friendshipButtons = new VBox(); // Vbox to contain all button about friendship
        friendshipButtons.setSpacing(5);

        Button addFriendship = new Button("Add Friendship");
        addFriendship.setStyle("-fx-background-color: #FFC000; "
            + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );");
        Button removeFriendship = new Button("Remove Friendship");
        removeFriendship.setStyle("-fx-background-color: #FFC000; "
            + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );");

        friendshipButtons.getChildren().addAll(addFriendship, removeFriendship);
        friendshipButtons.setAlignment(Pos.CENTER_LEFT);
        friendship.getChildren().addAll(imageView2, friendshipButtons);
        friendship.setAlignment(Pos.CENTER);

        // Textfield 3
        TextField t3 = new TextField();
        t3.setPrefHeight(40);
        t3.setPrefWidth(150);
        t3.setPrefColumnCount(10);
        t3.setPadding(new Insets(0, 0, 0, 0));
        String friend2 = t3.getText();
        // Remove All Button
        Button removeAll = new Button("Remove All");
        removeAll.setStyle("-fx-background-color: #FFC000; "
            + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );");
        // Vbox
        vb.setSpacing(5);
        vb.setPadding(new Insets(0, 0, 0, 70));
        vb.getChildren().addAll(imageView,t1, userButtons, r, t2, friendship, t3,
            removeAll);
        vb.setAlignment(Pos.CENTER);

        border.setLeft(vb);
    }


    /**
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
