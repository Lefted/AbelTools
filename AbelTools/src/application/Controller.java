package application;

import java.net.URL;
import java.util.ResourceBundle;

import code.Code;
import code.Options;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.SplitPane.Divider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import programs.codewandler.Codewandler;

public class Controller implements Initializable {

	public TextArea outputArea;
	public CheckBox codeLeft;
	public CheckBox codeRight;
	public CheckBox fillLeft;
	public CheckBox addComments;

	public TextField numberColumnsIn;
	public TextField numberColumnsOut;
	public TextField numberRowsIn;
	public TextField numberRowsOut;

	public Button generateButton;
	public ComboBox<String> programType;

	public Label errorLabel1;
	public Label errorLabel2;
	
	public SplitPane splitPane;

	public static Font outputFont = Font.font("System", 14D);

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		programType.getItems().addAll("Normal", "4bit Codewandler");
		programType.getSelectionModel().selectFirst();
		programType.setOnAction(e -> disableOnProgramChange());

		codeLeft.setOnAction(e -> disableOnLeftUpdate());
		codeRight.setOnAction(e -> disableOnRightUpdate());
		codeLeft.setSelected(true);
		
		fillLeft.setOnAction(e -> disableOnFillLeft());
		
		numberRowsOut.textProperty().bindBidirectional(numberRowsIn.textProperty());
		numberColumnsIn.textProperty().addListener(e -> onColumnsInChanged());
		
		Divider divider = splitPane.getDividers().get(0);
	    divider.positionProperty().addListener(new ChangeListener<Number>()
	    {
	        @Override
	        public void changed( ObservableValue<? extends Number> observable, Number oldvalue, Number newvalue )
	        {
	            divider.setPosition(0.17);
	        }
	    });
	}

	private void onColumnsInChanged() {
		try {
			if (fillLeft.isSelected()) {
				numberRowsIn.setText("" + (int) (Math.pow(2, Integer.parseInt(numberColumnsIn.getText()))));
				errorLabel1.setVisible(false);
			}
		} catch (Exception e) {
			errorLabel1.setVisible(true);
		}
		
	}
	
	private void disableOnLeftUpdate() {
		if (codeLeft.isSelected()) {
			numberColumnsIn.setDisable(false);
			if (fillLeft.isSelected()) {
				numberRowsIn.setDisable(true);
			} else {
				numberRowsIn.setDisable(false);
			}
			fillLeft.setDisable(false);
		} else {
			numberColumnsIn.setDisable(true);
			numberRowsIn.setDisable(true);
			fillLeft.setDisable(true);
		}
		if (codeLeft.isSelected() && codeRight.isSelected()) {
			numberRowsOut.textProperty().bindBidirectional(numberRowsIn.textProperty());
		} else {
			numberRowsOut.textProperty().unbindBidirectional(numberRowsIn.textProperty());
		}
		disableRowsOut();
	}

	private void disableOnFillLeft() {
		if (codeLeft.isSelected() && fillLeft.isSelected()) {
			numberRowsIn.setDisable(true);
		} else {
			numberRowsIn.setDisable(false);
		}
		disableRowsOut();
	}
	
	private void disableRowsOut() {
		if (fillLeft.isSelected() && codeLeft.isSelected() && codeRight.isSelected()) {
			numberRowsOut.setDisable(true);
		} else {
			if (codeRight.isSelected()) {
				numberRowsOut.setDisable(false);
			}
		}
	}

	private void disableOnRightUpdate() {
		if (codeRight.isSelected()) {
			numberColumnsOut.setDisable(false);
			numberRowsOut.setDisable(false);
		} else {
			numberColumnsOut.setDisable(true);
			numberRowsOut.setDisable(true);
		}
		if (codeLeft.isSelected() && codeRight.isSelected()) {
			numberRowsOut.textProperty().bindBidirectional(numberRowsIn.textProperty());
		} else {
			numberRowsOut.textProperty().unbindBidirectional(numberRowsIn.textProperty());
		}
		disableRowsOut();
	}

	private void disableOnProgramChange() {
		if (programType.getSelectionModel().getSelectedItem().equalsIgnoreCase("Normal")) {
			numberColumnsIn.setDisable(false);
			numberColumnsOut.setDisable(false);
			numberRowsIn.setDisable(false);
			numberRowsOut.setDisable(false);
			fillLeft.setDisable(false);
			codeLeft.setDisable(false);
			codeRight.setDisable(false);
		} else {
			numberColumnsIn.setDisable(true);
			numberColumnsOut.setDisable(true);
			numberRowsIn.setDisable(true);
			numberRowsOut.setDisable(true);
			fillLeft.setDisable(true);
			codeLeft.setDisable(true);
			codeRight.setDisable(true);
		}
	}

	public void onFontButtonPressed() {
		FontStage fontStage = new FontStage(outputFont.getName(), (int) outputFont.getSize());
		fontStage.showAndWait();
		outputArea.setFont(outputFont);
	}

	public void updateOutputFont() {
		outputArea.setFont(outputFont);
	}

	public void onGenerateButtonPressed() {
		if (selectedProgram() == "Normal") {
			if (!validNumbers() || !validChoises()) {
				validChoises();
				return;
			}

			int codeStyle = 0;
			if ((codeLeft.isSelected()) && (!codeRight.isSelected())) {
				codeStyle = Options.CODE_LEFT;
			}
			if ((!codeLeft.isSelected()) && (codeRight.isSelected())) {
				codeStyle = Options.CODE_RIGHT;
			}
			if (codeLeft.isSelected() && codeRight.isSelected()) {
				codeStyle = Options.CODE_BOTH;
			}
			Options options = new Options(codeStyle, addComments.isSelected());
			options.setColumnsIn(Integer.parseInt(numberColumnsIn.getText()));
			options.setColumnsOut(Integer.parseInt(numberColumnsOut.getText()));
			options.setRowsIn(Integer.parseInt(numberRowsIn.getText()));
			options.setRowsOut(Integer.parseInt(numberRowsOut.getText()));
			options.setCompilingLeft(fillLeft.isSelected());

			StringBuffer result = Code.generateCode(options);
			outputArea.setText(result.toString());

		}
		if (selectedProgram() == "4bit Codewandler") {
			Options options = new Options(Options.CODE_BOTH, addComments.isSelected());

			StringBuffer result = Codewandler.getStringBuffer(options);
			outputArea.setText(result.toString());
		}
	}

	private String selectedProgram() {
		return programType.getSelectionModel().getSelectedItem();
	}

	private boolean validNumbers() {
		try {
			int value1 = Integer.parseInt(numberRowsIn.getText());
			int value2 = Integer.parseInt(numberColumnsIn.getText());
			int value3 = Integer.parseInt(numberColumnsOut.getText());
			int value4 = Integer.parseInt(numberRowsOut.getText());
			if ((value1 >= 0) && (value2 >= 0) && (value3 >= 0) && (value4 >= 0)) {
				errorLabel1.setVisible(false);
				return true;
			}
			return false;
		} catch (Exception e) {
			errorLabel1.setVisible(true);
			return false;
		}
	}

	private boolean validChoises() {
		if (codeLeft.isSelected() || codeRight.isSelected()) {
			errorLabel2.setVisible(false);
			return true;
		}
		errorLabel2.setVisible(true);
		return false;
	}

}