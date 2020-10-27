package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainViewController implements Initializable {

	@FXML
	private Button btPositiveTemperatureGradient;

	@FXML
	private Button btNegativeTemperatureGradient;

	@FXML
	private Button btPositiveValvePositionGradient;

	@FXML
	private Button btNegativeValvePositionGradient;

	@FXML
	private Button btAbout;

	@FXML
	public void onBtPositiveTemperatureGradient() {
		System.out.println("Temperatura - Positiva");
	}

	@FXML
	public void onBtNegativeTemperatureGradient() {
		System.out.println("Temperatura - Negativa");
	}

	@FXML
	public void onBtPositiveValvePositionGradient() {
		System.out.println("Válvula - Positiva");
	}

	@FXML
	public void onBtNegativeValvePositionGradient() {
		System.out.println("Válvula - Negativa");
	}

	@FXML
	public void onBtAbout() {
		System.out.println("Sobre");
	}

	@Override
	public void initialize(URL uri, ResourceBundle rb) {

	}

	private synchronized <T> void loadView(String absoluteName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Enter Department data");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();

		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Erro carregando página", e.getMessage(), AlertType.ERROR);
		}
	}
}