package cn.tcp.fp;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Adventure {
	public void onBackPressed(ActionEvent e) {
		Client.mainStage.setScene(Client.mainScene);
	}

	public void onEasyPressed(ActionEvent e) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Easy.fxml"));
		Parent adventure = loader.load();
		Scene adventureScene = new Scene(adventure);
		Easy adventureCtrl = loader.getController();
		adventureScene.setOnKeyPressed(adventureCtrl.OnKeyPressed);
		Client.mainStage.setOnShown((evt) -> adventureCtrl.startAmination());
		Client.mainStage.setScene(adventureScene);
	}

	public void onMediumPressed(ActionEvent e) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Medium.fxml"));
		Parent adventure2 = loader.load();
		Scene adventureScene2 = new Scene(adventure2);
		Medium adventureCtrl2 = loader.getController();
		adventureScene2.setOnKeyPressed(adventureCtrl2.OnKeyPressed);
		Client.mainStage.setOnShown((evt) -> adventureCtrl2.startAmination());
		Client.mainStage.setScene(adventureScene2);
	}

	public void onHardPressed(ActionEvent e) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Hard.fxml"));
		Parent adventure3 = loader.load();
		Scene adventureScene3 = new Scene(adventure3);
		Hard adventureCtrl3 = loader.getController();
		adventureScene3.setOnKeyPressed(adventureCtrl3.OnKeyPressed);
		Client.mainStage.setOnShown((evt) -> adventureCtrl3.startAmination());
		Client.mainStage.setScene(adventureScene3);
	}

}
