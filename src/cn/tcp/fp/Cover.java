package cn.tcp.fp;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Cover {
	public void onDoggiePressed(ActionEvent e) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Doggie.fxml"));
		Parent doggie = loader.load();
		Scene dogScene = new Scene(doggie);
		Client.mainStage.setScene(dogScene);
	}

	public void onAdventurePressed(ActionEvent e) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Level.fxml"));
		Parent level = loader.load();
		Scene LevelScene = new Scene(level);
		Client.mainStage.setScene(LevelScene);
	}
}
