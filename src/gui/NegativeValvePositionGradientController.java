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

public class NegativeValvePositionGradientController implements Initializable {

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
	private TextField txtHours;

	@FXML
	private TextField txtMinutes;

	@FXML
	private TextField txtSeconds;

	@FXML
	public void onBtCloseAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}

	@FXML
	public void onBtStartAction(ActionEvent event) {
		System.out.println("Iniciar contagem");

		if ((Utils.tryParseToDouble(txtInitialPosition.getText()) >= 0.0)
				&& (Utils.tryParseToDouble(txtInitialPosition.getText()) <= 100.0)) {
			initialPosition = Utils.tryParseToDouble(txtInitialPosition.getText());
		}

		if ((Utils.tryParseToDouble(txtFinalPosition.getText()) >= 0.0)
				&& (Utils.tryParseToDouble(txtFinalPosition.getText()) <= 100.0)) {
			finalPosition = Utils.tryParseToDouble(txtFinalPosition.getText());
		}

		int hours = Utils.tryParseToInt(txtHours.getText());
		int minutes = Utils.tryParseToInt(txtMinutes.getText());
		int seconds = Utils.tryParseToInt(txtSeconds.getText());

		if (hours == 0 && minutes == 0 && seconds == 0) {
			Alerts.showAlert("Tempo Zerado", "Tempo não pode ser igual a zero",
					"Favor colocar um valor válido no tempo", AlertType.WARNING);
		} else {
			System.out.println("Inicio: " + initialPosition + " Fim: " + finalPosition + " Diferença: "
					+ (initialPosition - finalPosition) + "\n" + "Horas: " + hours + " Minutos: " + minutes
					+ " Segundos: " + seconds);

			Gradient gradient = new Gradient(initialPosition, finalPosition, hours, minutes, seconds);

			gradient.countingGradient();
		}

	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}

	public void initializeNodes() {
		Constraints.setTextFieldDouble(txtInitialPosition);
		Constraints.setTextFieldDouble(txtFinalPosition);
		Constraints.setTextFieldInteger(txtHours);
		Constraints.setTextFieldInteger(txtMinutes);
		Constraints.setTextFieldInteger(txtSeconds);
	}

}
