package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.entities.Gradient;

public class PositiveValvePositionGradientController implements Initializable {

	private double initialPosition = 0.0;
	private double finalPosition = 0.0;

	@FXML
	private Button btClose;

	@FXML
	private Button btStart;

	@FXML
	private TextField txtInitialPosition;

	@FXML
	private TextField txtFinalPosition;

	@FXML
	private TextField txtTotalTimeHours;

	@FXML
	private TextField txtTotalTimeMinutes;

	@FXML
	private TextField txtTotalTimeSeconds;

	@FXML
	private TextField txtAlarmHours;

	@FXML
	private TextField txtAlarmMinutes;

	@FXML
	private TextField txtAlarmSeconds;

	@FXML
	public void onBtCloseAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}

	@FXML
	public void onBtStartAction(ActionEvent event) {
		if ((Utils.tryParseToDouble(txtInitialPosition.getText()) >= 0.0)
				&& (Utils.tryParseToDouble(txtInitialPosition.getText()) <= 100.0)) {
			initialPosition = Utils.tryParseToDouble(txtInitialPosition.getText());
		}

		if ((Utils.tryParseToDouble(txtFinalPosition.getText()) >= 0.0)
				&& (Utils.tryParseToDouble(txtFinalPosition.getText()) <= 100.0)) {
			finalPosition = Utils.tryParseToDouble(txtFinalPosition.getText());
		}

		int totalTimeHours = Utils.tryParseToInt(txtTotalTimeHours.getText());
		int totalTimeMinutes = Utils.tryParseToInt(txtTotalTimeMinutes.getText());
		int totalTimeSeconds = Utils.tryParseToInt(txtTotalTimeSeconds.getText());

		int alarmHours = Utils.tryParseToInt(txtAlarmHours.getText());
		int alarmMinutes = Utils.tryParseToInt(txtAlarmMinutes.getText());
		int alarmSeconds = Utils.tryParseToInt(txtAlarmSeconds.getText());

		if (totalTimeHours == 0 && totalTimeMinutes == 0 && totalTimeSeconds == 0) {
			Alerts.showAlert("Tempo de Gradiente Zerado", "Tempo de gradiente não pode ser igual a zero",
					"Favor colocar um valor válido no tempo", AlertType.WARNING);
		} else if (alarmHours == 0 && alarmMinutes == 0 && alarmSeconds == 0) {
			Alerts.showAlert("Tempo de Alarme Zerado", "Tempo de alarme não pode ser igual a zero",
					"Favor colocar um valor válido no tempo", AlertType.WARNING);
		} else {
			Gradient gradient = new Gradient(initialPosition, finalPosition, totalTimeHours, totalTimeMinutes, totalTimeSeconds, alarmHours, alarmMinutes, alarmSeconds);

			gradient.countingGradient("Posição da Válvula");
		}

	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}

	public void initializeNodes() {
		Constraints.setTextFieldDouble(txtInitialPosition);
		Constraints.setTextFieldDouble(txtFinalPosition);
		Constraints.setTextFieldInteger(txtTotalTimeHours);
		Constraints.setTextFieldInteger(txtTotalTimeMinutes);
		Constraints.setTextFieldInteger(txtTotalTimeSeconds);
		Constraints.setTextFieldInteger(txtAlarmHours);
		Constraints.setTextFieldInteger(txtAlarmMinutes);
		Constraints.setTextFieldInteger(txtAlarmSeconds);
	}

}
