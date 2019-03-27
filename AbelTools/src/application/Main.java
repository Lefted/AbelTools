package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	public static Scene scene;

	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("AbelTools.fxml"));
			scene = new Scene(root);
			scene.getStylesheets().add("/application/application.css");
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.setTitle("AbelTools_b3 for JDK8");
			primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
