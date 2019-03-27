package application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FontStage extends Stage {

	public FontStage(String prevFontName, int prevSize) {
		Parent root = null;
		FontController controller = null;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Font.fxml"));
			root = (Parent)loader.load();
			controller = (FontController)loader.getController();
			display(root, controller);
			setPrevious(prevFontName, prevSize, controller);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void setPrevious(String name, int size, FontController controller)  {
		controller.setPreviousOptions(name, size);
	}
	
	private void display(Parent root, FontController controller) {
		Scene scene = new Scene(root);

		setScene(scene);
		initModality(Modality.APPLICATION_MODAL);
		setTitle("Select Font");
		setResizable(false);
		setOnCloseRequest(event -> {
			controller.closeProperly();
		});
		
	}
}
