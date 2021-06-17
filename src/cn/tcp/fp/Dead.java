package cn.tcp.fp;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Dead extends Easy {
	@FXML
	public Label _levelup;
	@FXML
	public Button _tolevelup;

	int i = 0;

	public void onExitPressed(ActionEvent e) {
		i++;
		if (i == 1 && finalscore >= 100000) {
			_levelup.setVisible(true);
			_tolevelup.setVisible(true);
		} else {
			Client.mainStage.setScene(Client.mainScene);
		}
	}

	public void onGoToLevelUpPressed(ActionEvent e) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Doggie.fxml"));
		Parent doggie = loader.load();
		Scene dogScene = new Scene(doggie);
		Client.mainStage.setScene(dogScene);
	}

}
