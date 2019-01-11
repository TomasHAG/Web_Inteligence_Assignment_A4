package application;
	


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		
		final HBox hBox = new HBox();
        hBox.setSpacing(10);

        final TextField TextFieldPath = new TextField("C:\\Users\\TomasHägg\\Desktop\\spiral\\spiral.arff");
        final TextField TextFieldTime = new TextField("500");
        final Label label1 = new Label("Path: ");
        final Label label2 = new Label("Iteration / Time: ");
        Button Button1 = new Button("Logistic");
        Button Button2 = new Button("MultilayerPerceptron");

        EventHandler<ActionEvent> handler1 = event -> {
        	try {
        		int ite = Integer.parseInt(TextFieldTime.getText());
        		newWindow1(TextFieldPath.getText(), ite);
        	}catch(Exception e1) {
	    		
	        }
        };
        Button1.setOnAction(handler1);
        
        EventHandler<ActionEvent> handler2 = event -> {
        	try {
        		int time = Integer.parseInt(TextFieldTime.getText());
        		newWindow2(TextFieldPath.getText(), time);
        	}catch(Exception e1) {
	    		
	        }
        };
        Button2.setOnAction(handler2);

        hBox.getChildren().add(label1);
        hBox.getChildren().add(TextFieldPath);
        hBox.getChildren().add(label2);
        hBox.getChildren().add(TextFieldTime);
        hBox.getChildren().add(Button1);
        hBox.getChildren().add(Button2);
        primaryStage.setScene(new Scene(hBox));

        primaryStage.show();

	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private TextField generateTextfeild(String input,String tt) {
		TextField tf = new TextField();
        tf.setPrefHeight(50);
        tf.setPrefWidth(50);
        tf.setAlignment(Pos.CENTER);
        tf.setEditable(false);
        tf.setText("(" + input + ")");
        Tooltip tooltip = new Tooltip();
        tooltip.setText(tt);
        tf.setTooltip(tooltip);
        return tf;
	}
	
	private void newWindow1(String path, int iterations) {
		try {
    		final HBox hBoxl = new HBox();
    		hBoxl.setSpacing(10);
    		load data = new load(path);
			grhapics gr = new grhapics();
			
			logistic lP = new logistic(data.getData(), iterations);
			gr.generateChart(lP.createList());
			int[][] cMatrix = lP.confusionMatrix();
			 
			Stage newWindow = new Stage();
			 
			hBoxl.getChildren().add(gr.getGhraph());
			hBoxl.getChildren().add(new Label(iterations + " iterations\r"
					 + "Accuracy = " + lP.getAccuracy() + " %\r"
					 + "Confusion Matrix:\r"));
			
			GridPane gPane = new GridPane();
			 for(int y = 0; y < 3; y++){
	                for(int x = 0; x < 3; x++){
	                	TextField tf = generateTextfeild(String.valueOf(cMatrix[x][y]), (((x == 0) ? "A" : ((x == 1) ? "B" : "C"))) + ", " + ((y == 0) ? "A" : ((y == 1) ? "B" : "C")));
	                	gPane.setRowIndex(tf,y);
	                	gPane.setColumnIndex(tf,x);    
	                	gPane.getChildren().add(tf);
	                }
			 }
			 
			 hBoxl.getChildren().add(gPane);
			 
			 Scene scene  = new Scene(hBoxl, 1100, 700);
			 
			 
             newWindow.setTitle("Logistic");
             newWindow.setScene(scene);
             
             newWindow.show();
			 
    	}catch(Exception e1) {
    		
        }
	}
	
	private void newWindow2(String path, int time) {
		try {
    		final HBox hBoxm = new HBox();
    		hBoxm.setSpacing(10);
    		load data = new load(path);
			grhapics gr = new grhapics();
			
			multiPerceptron mP = new multiPerceptron(data.getData(), time);
			gr.generateChart(mP.createList());
			int[][] cMatrix = mP.confusionMatrix();
			 
			 Stage newWindow = new Stage();
			 
			 hBoxm.getChildren().add(gr.getGhraph());
			 hBoxm.getChildren().add(new Label(time + " time\r"
					 + "Accuracy = " + mP.getAccuracy() + " %\r"
					 + "Confusion Matrix:\r"));
			 
			 GridPane gPane = new GridPane();
			 for(int y = 0; y < 3; y++){
	                for(int x = 0; x < 3; x++){
	                	TextField tf = generateTextfeild(String.valueOf(cMatrix[x][y]), (((x == 0) ? "A" : ((x == 1) ? "B" : "C"))) + ", " + ((y == 0) ? "A" : ((y == 1) ? "B" : "C")));
	                	gPane.setRowIndex(tf,y);
	                	gPane.setColumnIndex(tf,x);    
	                	gPane.getChildren().add(tf);
	                }
			 }
			 
			 hBoxm.getChildren().add(gPane);

			 
			 Scene scene  = new Scene(hBoxm, 1100, 700);
			 
             newWindow.setTitle("MultilayerPerceptron");
             newWindow.setScene(scene);
             
             newWindow.show();
			 
    	}catch(Exception e1) {
    		
        }
	}
}
