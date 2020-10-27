package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class NegativeValvePositionGradientController implements Initializable {

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
		double initialPosition = Utils.tryParseToDouble(txtInitialPosition.getText());
		double finalPosition = Utils.tryParseToDouble(txtFinalPosition.getText());
		int hours = Utils.tryParseToInt(txtHours.getText());
		int minutes = Utils.tryParseToInt(txtMinutes.getText());
		int seconds = Utils.tryParseToInt(txtSeconds.getText());
		
		System.out.println("Inicio: " + initialPosition + "Fim: " + finalPosition + "Diferença: " + (initialPosition - finalPosition)
				+ "\n" + "Horas: " + hours + "Minutos: " + minutes + "Segundos: " + seconds);
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
