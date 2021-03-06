package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Alerts;
import gui.util.Utils;
import javafx.event.ActionEvent;
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
	public void onBtPositiveTemperatureGradientAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		loadView("/gui/PositiveTemperatureGradient.fxml", "Gradiente de Temperatura Positivo", parentStage);
	}

	@FXML
	public void onBtNegativeTemperatureGradientAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		loadView("/gui/NegativeTemperatureGradient.fxml", "Gradiente de Temperatura Negativo", parentStage);
	}

	@FXML
	public void onBtPositiveValvePositionGradientAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		loadView("/gui/PositiveValvePositionGradient.fxml", "Gradiente de Posição de Válvula Positivo", parentStage);
	}

	@FXML
	public void onBtNegativeValvePositionGradientAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		loadView("/gui/NegativeValvePositionGradient.fxml", "Gradiente de Posição de Válvula Negativo", parentStage);
	}

	@FXML
	public void onBtAboutAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		loadView("/gui/About.fxml", "Informações", parentStage);
	}

	@Override
	public void initialize(URL uri, ResourceBundle rb) {

	}

	private synchronized void loadView(String absoluteName, String title, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle(title);
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
			Alerts.showAlert("IO Exception", "Erro carregando página", e.getMessage(), AlertType.ERROR);
		}
	}
}