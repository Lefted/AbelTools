package application;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class FontController implements Initializable {

	public ChoiceBox<String> sizeChoiceBox;
	public ListView<String> fontList;

	private int currentSize = 14;
	@SuppressWarnings("unused")
	private Font currentFont = Font.font("Arial", 14D);
	public Label sampleLabel;

	void setPreviousOptions(String fontName, int size) {
		if (fontName.isEmpty() && (size == 0)) {
			sizeChoiceBox.getSelectionModel().select(currentSize - 1);
			fontList.getSelectionModel().select(214);
		} else {
			currentSize = size;
			sizeChoiceBox.getSelectionModel().clearAndSelect(currentSize -1);
			fontList.getSelectionModel().select(fontName);
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		sizeChoiceBox.getItems().addAll(sizeArray());

		fontList.getItems().addAll(getFonts());

		sizeChoiceBox.setOnAction(event -> updateFont());
		fontList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				updateFont();
			}

		});
	}

	public void onConfirmButtonPressed() {
		closeProperly();
	}

	void closeProperly() {
		Controller.outputFont = currentFont;
		Stage stage = (Stage) fontList.getScene().getWindow();
		stage.close();
	}

	private void updateFont() {
		currentSize = Integer.parseInt(sizeChoiceBox.getSelectionModel().getSelectedItem());
		String selectedFont = fontList.getSelectionModel().getSelectedItem();

		currentFont = Font.font(selectedFont, currentSize);
		sampleLabel.setFont(currentFont);
	}

	private String[] sizeArray() {
		String[] array = new String[100];

		for (int i = 0; i < 100; i++) {
			array[i] = Integer.toString(i + 1);
		}
		return array;
	}

	private List<String> getFonts() {
		return javafx.scene.text.Font.getFontNames();
	}

	public Font getReturn() {
		return currentFont;
	}
}
