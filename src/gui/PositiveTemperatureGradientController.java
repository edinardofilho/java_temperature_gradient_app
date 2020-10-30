package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import model.entities.Gradient;

public class PositiveTemperatureGradientController implements Initializable {

	private double initialTemperature = 0.0;
	private double finalTemperature = 0.0;

	@FXML
	private Button btClose;

	@FXML
	private Button btStart;

	@FXML
	private TextField txtInitialTemperature;

	@FXML
	private TextField txtFinalTemperature;

	@FXML
	private TextField txtRate;

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
	private RadioButton radioBtTotalTime;

	@FXML
	private RadioButton radioBtRate;

	@FXML
	public void onBtCloseAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}

	@FXML
	public void onBtStartAction(ActionEvent event) {
		initialTemperature = Utils.tryParseToDouble(txtInitialTemperature.getText());

		finalTemperature = Utils.tryParseToDouble(txtFinalTemperature.getText());

		if (radioBtTotalTime.isSelected() == true) {
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
				Gradient gradient = new Gradient(initialTemperature, finalTemperature, totalTimeHours, totalTimeMinutes,
						totalTimeSeconds, alarmHours, alarmMinutes, alarmSeconds);

				gradient.countingGradient("Temperatura", " °C");
			}
		} else {
			double rate = Utils.tryParseToDouble(txtRate.getText());

			int alarmHours = Utils.tryParseToInt(txtAlarmHours.getText());
			int alarmMinutes = Utils.tryParseToInt(txtAlarmMinutes.getText());
			int alarmSeconds = Utils.tryParseToInt(txtAlarmSeconds.getText());

			if (rate == 0) {
				Alerts.showAlert("Taxa de Aquecimento Zerada", "Tem que haver taxa de aquecimento para a execução do programa",
						"Favor colocar um valor válido na taxa de Aquecimento", AlertType.WARNING);
			} else if (alarmHours == 0 && alarmMinutes == 0 && alarmSeconds == 0) {
				Alerts.showAlert("Tempo de Alarme Zerado", "Tempo de alarme não pode ser igual a zero",
						"Favor colocar um valor válido no tempo", AlertType.WARNING);
			} else {
				Gradient gradient = new Gradient(initialTemperature, finalTemperature, rate, alarmHours, alarmMinutes, alarmSeconds);

				gradient.countingRate("Temperatura", " °C");
			}
		}

	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}

	public void initializeNodes() {
		Constraints.setTextFieldDouble(txtInitialTemperature);
		Constraints.setTextFieldDouble(txtFinalTemperature);
		Constraints.setTextFieldInteger(txtTotalTimeHours);
		Constraints.setTextFieldInteger(txtTotalTimeMinutes);
		Constraints.setTextFieldInteger(txtTotalTimeSeconds);
		Constraints.setTextFieldDouble(txtRate);
		Constraints.setTextFieldInteger(txtAlarmHours);
		Constraints.setTextFieldInteger(txtAlarmMinutes);
		Constraints.setTextFieldInteger(txtAlarmSeconds);
	}

}
