package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
    // store any command-line arguments that were entered.
    // NOTE: this.getParameters().getRaw() will get these also
    private List<String> args;

    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 800;
    private static final String APP_TITLE = "Social Network";

    private SocialNetwork network = new SocialNetwork();
    // Main layout is Border Pane example (top,left,center,right,bottom)
    BorderPane border = new BorderPane();
    Text info = new Text();


    Button buttonClose;


    private void setBackgroundColor() {
        border.setStyle("-fx-background-color: #BFBFBF");
    }

    private void setUpBottomBox() {
        HBox feedback = new HBox();
        Label feedLabel = new Label("Feedback");
        TextField feedText = new TextField();

        feedLabel.setStyle("-fx-background-color: #FFC000");
        feedLabel.setPrefSize(60, 25);
        feedText.setPrefWidth(750);
        feedback.setSpacing(10);
        feedback.getChildren().addAll(feedLabel, feedText);
        border.setBottom(feedback);
    }

    private void setUpTopBox() {
		Button dis = new Button("Display");
		dis.setMinSize(200, 40);
		dis.setTranslateY(25);
		dis.setTranslateX(580);
		dis.setStyle("-fx-background-color: #FFC000; "
				+ "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );");

		// pop up new window
		dis.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				GridPane gridPane = new GridPane();
				if(network.getCentralUser() == null) {
					  infoMessage("Please set a central user first."); 
					  return;
				  }else {
				   reflesh(gridPane);
				  }
				ArrayList<Button> buttons = new ArrayList<Button>();
				// Label secondLabel = new Label("Friend relationship");
				
				BorderPane secondaryLayout = new BorderPane();
				Scene secondScene = new Scene(gridPane, 800, 600);
				
				buttonClose = new Button("Quit");
				buttonClose.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						Stage stage = (Stage) buttonClose.getScene().getWindow();// set the button
						stage.close();// to close the window
					}

				});
				
				secondaryLayout.setStyle("-fx-background-color: #BFBFBF");
				secondaryLayout.setBottom(buttonClose);
				// New window
				Stage newWindow = new Stage();
				newWindow.setTitle("Friend Relationship");
				newWindow.setScene(secondScene);

				// Set position of second window, related to primary window.
//				newWindow.setX(primaryStage.getX());
//				newWindow.setY(primaryStage.getY());

				newWindow.show();

			}

		});
        
        border.setTop(dis);
    }
    
    private void reflesh(  GridPane gridPane) {
    	
    	ArrayList<Button> buttons = new ArrayList<Button>();
    	Button center = new Button("Center user: " + network.getCentralUser());
		for (int i = 0; i < network.getCenterFriend(network.getCentralUser()).size(); i++) {
			Button button = new Button(network.getCenterFriend(network.getCentralUser()).get(i));
			buttons.add(button);
		}

		
		
		gridPane.add(center, 0, 0, 1, 1);
		for (int i = 0; i < buttons.size(); i++) {
			gridPane.add(buttons.get(i), 1, i, 1, 1);
		}

		
        gridPane.setStyle("-fx-background-color: #BFBFBF");
        Button central = new Button("Set Central User");
        central.setStyle("-fx-background-color: #FFC000; "
            + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );");
        TextField centralUser = new TextField();
        centralUser.setMaxWidth(100);
        central.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                String central = centralUser.getText();
                if( network.setCentral(central) == false) {
             	   infoMessage("User Does not Exist!");
                }else {
                 
                 infoMessage("Set " + central + " as central user");
                }
                gridPane.getChildren().clear();
                reflesh(gridPane);
            }
        });
        HBox hb = new HBox();
        hb.getChildren().addAll(centralUser, central);
        hb.setSpacing(5);
        hb.setTranslateX(580);
        gridPane.getChildren().addAll(hb);
		
		
		for (int  i = 0; i < buttons.size(); i++) {
			final Integer innerMi = new Integer(i);
			buttons.get(i).setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                	
              network.setCentral(buttons.get(innerMi).getText());
              gridPane.getChildren().clear();
              
              reflesh(gridPane);
                	
                	
            	}

			});
		}
		
    }

    private void infoMessage(String message) {
        Alert info = new Alert(AlertType.INFORMATION);
        info.setTitle("Message");
        info.setContentText(message);
        info.showAndWait();
    }

    private void infoMessage(String title,String header, String message) {
        Alert info = new Alert(AlertType.INFORMATION);
        info.setTitle(title);
        info.setHeaderText(header);
        info.setContentText(message);
        info.showAndWait();
    }
    private void warningMessage(String header, String message) { 
    	 Alert info = new Alert(AlertType.WARNING); 
    	 info.setTitle("WARNING"); 
    	 info.setHeaderText(header); 
    	 info.setContentText(message); 
    	 info.showAndWait(); 
    	 } 
    
    private void setUpLeftBox() {

        try {
            VBox left = new VBox();
            VBox vb = new VBox();

//            Text topT = new Text("Network consists of: " + socialNetwork.getGraph().order()
//                + " Users, " + socialNetwork.getGraph().size() + " friendships, and " 
//                + socialNetwork.getGraph().numberOfGroups()+" connected groups.\n");
//            
            FileInputStream inputImage = null;

            inputImage = new FileInputStream("ATEAM.png");

            Image image = new Image(inputImage);
            ImageView imageView = new ImageView(image);

            imageView.setFitHeight(120);
            imageView.setFitWidth(400);
            Label user = new Label("User: ");

            // Empty space
            Region r = new Region();
            r.setPrefHeight(10);
            r.setPrefWidth(200);
            //info TextField
            //Text info = new Text();
            info.setText(network.updateInfo());
            // Textfield 1 for user
            TextField username = new TextField();
            username.setPrefHeight(40);
            // t1.setMaxHeight(40);
            username.setPrefWidth(200);
            username.setPrefColumnCount(10);
            username.setPadding(new Insets(0, 0, 0, 0));
            VBox vt1 = new VBox();
            vt1.getChildren().addAll(user, username);

            // HBox containing all buttons about user
            HBox userButtons = new HBox();
            userButtons.setSpacing(5);
            Button addUser = new Button("Add User");
            addUser.setStyle("-fx-background-color: #FFC000; "
                + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );");
            addUser.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                    String name = username.getText();
                    if (name != null && !name.isEmpty()) {
                        if (network.addUser(name) == true) {
                            
                        } else {
                            warningMessage("Warning","can not add duplicate name");
                        }
                    } else {
                        warningMessage("Warning","Add User not working");
                    }
                    info.setText(network.updateInfo());
                }
            });
            Button removeUser = new Button("Remove User");
            removeUser.setStyle("-fx-background-color: #FFC000; "
                + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );");
            removeUser.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                    String name = username.getText();
                    if (name != null && !name.isEmpty()) {
                        if (network.removeUser(name) == true) {
                            infoMessage("removed user " + name);
                        } else {
                            warningMessage("Remove User failed", name + " does not in the network");

                        }
                    } else {
                        warningMessage("Warning", "Remove user not working");
                    }
                }
            });
            userButtons.getChildren().addAll(addUser, removeUser);
            userButtons.setAlignment(Pos.CENTER_RIGHT);

            // Empty space
            Region r3 = new Region();
            r3.setPrefHeight(30);
            r3.setPrefWidth(200);

            // Textfield 2
            TextField friend1 = new TextField();
            friend1.setPrefHeight(40);
            friend1.setPrefWidth(150);
            friend1.setPrefColumnCount(10);
            friend1.setPadding(new Insets(0, 0, 0, 0));
            // Label user1 = new Label("user1: ");
            // HBox u1 = new HBox();
            // u1.getChildren().addAll(user1, t2);
            Label rela = new Label("Relationship: ");
            VBox vt2 = new VBox();
            vt2.getChildren().addAll(rela, friend1);

            // HBox for friendship
            HBox friendship = new HBox();
            friendship.setSpacing(5);
            FileInputStream input;
            input = new FileInputStream("FriendshipArrow.png");
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

            friendshipButtons.getChildren().addAll(addFriendship,
                removeFriendship);
            friendshipButtons.setAlignment(Pos.CENTER_LEFT);
            friendship.getChildren().addAll(imageView2, friendshipButtons);
            friendship.setAlignment(Pos.CENTER);

            // Textfield 3
            TextField friend2 = new TextField();
            friend2.setPrefHeight(40);
            friend2.setPrefWidth(150);
            friend2.setPrefColumnCount(10);
            friend2.setPadding(new Insets(0, 0, 0, 0));
            // Remove All Button
            Button removeAll = new Button("Remove All");
            removeAll.setStyle("-fx-background-color: #FFC000; "
                + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );");

            addFriendship.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                    String f1 = friend1.getText();
                    String f2 = friend2.getText();
                    if (f1 != null && !f1.isEmpty() && f2 != null
                        && !f2.isEmpty()) {
                        network.addFriends(f1, f2);
                        infoMessage(
                            "added a friendship between " + f1 + " and " + f2);
                    }
                    info.setText(network.updateInfo());
                }
            });
            removeFriendship.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                    String f1 = friend1.getText();
                    String f2 = friend2.getText();
                    if (f1 != null && !f1.isEmpty() && f2 != null
                        && !f2.isEmpty()) {
                        network.removeFriends(f1, f2);
                        infoMessage("removed a friendship between " + f1
                            + " and " + f2);
                    }
                    info.setText(network.updateInfo());
                }
            });
            removeAll.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					network.removeAll();
					infoMessage("Remove all the information!");
					info.setText(network.updateInfo());
					
				}
            	
            });
            
            // Vbox
            vb.setSpacing(20);
            vb.setPadding(new Insets(0, 0, 0, 50));
            vb.getChildren().addAll(r, vt1, userButtons, r3, vt2, friendship,
                friend2, removeAll);
            vb.setAlignment(Pos.CENTER);

            // bigger left VBox
            left.setSpacing(5);
            left.getChildren().addAll(imageView,info, vb);
            left.setAlignment(Pos.TOP_CENTER);

            border.setLeft(left);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void setUpRightBox(Stage primaryStage) {
        try {
            VBox vb2 = new VBox();
            
            HBox muser = new HBox();
            muser.setSpacing(5);
            TextField muser1 = new TextField();
            TextField muser2 = new TextField();
            //user1.setMaxWidth(100);
            //user2.setMaxWidth(100);
            muser.getChildren().addAll(muser1, muser2);

            Button mutual = new Button("List Mutual Friends");

            mutual.setStyle("-fx-background-color: #FFC000; "
                + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );");
            mutual.setAlignment(Pos.CENTER_RIGHT);
            mutual.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                    String f1 = muser1.getText();
                    String f2 = muser2.getText();
                    Set<String> mFriend = new HashSet<String>();
                    mFriend = network.getMutualFriends(f1, f2);
                    infoMessage("Mutual Friends","Mutual Friends:",mFriend.toString());
                }
            });
            
            // Textfield 1 for user
            TextField ld = new TextField();
            ld.setPrefHeight(30);

            ld.setPrefWidth(300);
            ld.setPrefColumnCount(10);
            ld.setPadding(new Insets(0, 0, 0, 0));

            // Empty space
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
            dis.setTranslateY(25);
            dis.setTranslateX(0);
            dis.setStyle("-fx-background-color: #FFC000; "
                + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );");

            Button exp = new Button("Export File");
            exp.setMinSize(100, 30);
            exp.setTranslateY(0);
            exp.setTranslateX(0);
            exp.setStyle("-fx-background-color: #FFC000; "
                + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );");
            exp.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                    File file = new File(ex.getText());
                    if (file.exists()) {
                        try {
                            network.saveToFile(file);
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        infoMessage("saved");
                    }
                }
            });

            Button load = new Button("Load File");
            load.setMinSize(100, 30);
            load.setTranslateY(0);
            load.setTranslateX(0);
            load.setStyle("-fx-background-color: #FFC000; "
                + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );");
            load.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                    File file = new File(ld.getText());
                    if (file.exists()) {
                        network.loadFromFile(file);
                        infoMessage("loaded");
                        info.setText(network.updateInfo());
                    } else {
                        warningMessage("Error", "File doesn't exist: " + ld.getText());
                    }
                }
            });
            Button shortestPath = new Button("Show Shortest Path");

            shortestPath.setStyle("-fx-background-color: #FFC000; "
                + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );");;
            shortestPath.setAlignment(Pos.TOP_RIGHT);
            HBox shortest = new HBox();
            shortest.setSpacing(5);
            TextField user1 = new TextField();
            TextField user2 = new TextField();
            //user1.setMaxWidth(100);
            //user2.setMaxWidth(100);
            shortest.getChildren().addAll(user1, user2);
            shortestPath.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                    String f1 = user1.getText();
                    String f2 = user2.getText();
                    List<String> path = new ArrayList<String>();
                    path = network.getShortestPath(f1, f2);
                    String header = "Shortest Path between "+f1+" and "+f2+":";
                    infoMessage("Shortest Path",header,path.toString());
                }
            });
            Button central = new Button("Set Central User");
            central.setStyle("-fx-background-color: #FFC000; "
                + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );");
            TextField centralUser = new TextField();
            centralUser.setMaxWidth(100);
            central.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                    String central = centralUser.getText();
                    if (central != null && !central.isEmpty()) {
                    	warningMessage("Error", "Please write the name of the central person");
                    }else if( network.setCentral(central) == false) {
                 	    warningMessage("Error", "User Does not Exist!");
                    }else {
                    	infoMessage("Set " + central + " as central user");
                   }
                }
            });
            // Vbox2
            vb2.setSpacing(20);
            vb2.setPadding(new Insets(190, 40, 0, 0));
            vb2.getChildren().addAll(muser,mutual,shortest,
                shortestPath, centralUser, central, ld, load, ex, exp, r2);
            vb2.setTranslateY(25);
            vb2.setAlignment(Pos.TOP_CENTER);

            border.setRight(vb2);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        setBackgroundColor();
        setUpLeftBox();
        setUpRightBox(primaryStage);
        setUpTopBox();
        // Add the vertical box to the center of the root pane
        // root.setCenter(vbox);
        Scene mainScene = new Scene(border, WINDOW_WIDTH, WINDOW_HEIGHT);

        // Add the stuff and set the primary stage
        primaryStage.setTitle(APP_TITLE);
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    @Override
    public void stop() {
        try {
            // infoMessage("Stage is closing");
            ButtonType saveButtonType = new ButtonType("Save", ButtonData.YES);
            ButtonType ENSButtonType =
                new ButtonType("Exit without Save", ButtonData.NO);
            TextInputDialog td = new TextInputDialog("File name or path");
            td.setHeaderText("Save the progress?");
           
	    td.getDialogPane().getButtonTypes().set(0, saveButtonType);
            td.getDialogPane().getButtonTypes().set(1, ENSButtonType);

            Button save =
                (Button) td.getDialogPane().lookupButton(saveButtonType);
            save.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                    // ok was pressed
                    // System.out.print("save pressed");
                    File file = new File(td.getEditor().getText());
                    if (file.exists()) {
                        try {
                            network.saveToFile(file);
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        infoMessage("Successfully saved, goodbye!");
                        // td.showAndWait();
                    } else {
                        infoMessage("File Doesn't exists, save failed!");
                    }
                    // Save file
                }
            });
            Button ENS =
                (Button) td.getDialogPane().lookupButton(ENSButtonType);
            ENS.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                    // System.out.println("cancel might have been pressed");
                    infoMessage("Quitting, goodbye!");
                }
            });
            td.showAndWait();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
