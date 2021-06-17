package cn.tcp.fp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Doggie {
	@FXML
	public Label _level1;
	@FXML
	public Label _level2;
	@FXML
	public Label _level3;
	@FXML
	public Label _level4;
	@FXML
	public Label _level5;
	@FXML
	public Label _hp1;
	@FXML
	public Label _hp2;
	@FXML
	public Label _hp3;
	@FXML
	public Label _hp4;
	@FXML
	public Label _hp5;
	@FXML
	public Button _choose1;
	@FXML
	public Button _choose2;
	@FXML
	public Button _choose3;
	@FXML
	public Button _choose4;
	@FXML
	public Button _choose5;

	@FXML
	public void initialize() {
		_level1.setText(Integer.toString(level1));
		_level2.setText(Integer.toString(level2));
		_level3.setText(Integer.toString(level3));
		_level4.setText(Integer.toString(level4));
		_level5.setText(Integer.toString(level5));
	}

	public void onBackPressed(ActionEvent e) {
		Client.mainStage.setScene(Client.mainScene);
	}

	static int level1 = 1;
	static int level2 = 1;
	static int level3 = 1;
	static int level4 = 1;
	static int level5 = 1;
	String strlevel = "";
	static String strhp1 = "100";
	static String strhp2 = "120";
	static String strhp3 = "140";
	static String strhp4 = "150";
	static String strhp5 = "160";
	int hp1 = 0, hp2 = 0, hp3 = 0, hp4 = 0, hp5 = 0;
	int pressed = 0;

	public void onLevelUp1Pressed(ActionEvent e) {
		pressed++;
		level1 = Integer.parseInt(_level1.getText());
		if (level1 < 5 && pressed == 1) {
			level1++;
			strlevel = Integer.toString(level1);
			_level1.setText(strlevel);
			hp1 = Integer.parseInt(_hp1.getText());
			hp1 *= 1.1;
			strhp1 = Integer.toString(hp1);
			_hp1.setText(strhp1);
		}
		_level1.setText(Integer.toString(level1));
	}

	public void onLevelUp2Pressed(ActionEvent e) {
		pressed++;
		level2 = Integer.parseInt(_level2.getText());
		if (level2 < 5 && pressed == 1) {
			level2++;
			strlevel = Integer.toString(level2);
			_level2.setText(strlevel);
			hp2 = Integer.parseInt(_hp2.getText());
			hp2 *= 1.15;
			strhp2 = Integer.toString(hp2);
			_hp2.setText(strhp2);
		}
		_level2.setText(Integer.toString(level2));
	}

	public void onLevelUp3Pressed(ActionEvent e) {
		pressed++;
		level3 = Integer.parseInt(_level3.getText());
		if (level3 < 5 && pressed == 1) {
			level3++;
			strlevel = Integer.toString(level3);
			_level3.setText(strlevel);
			hp3 = Integer.parseInt(_hp3.getText());
			hp3 *= 1.2;
			strhp3 = Integer.toString(hp3);
			_hp3.setText(strhp3);
		}
		_level3.setText(Integer.toString(level3));
	}

	public void onLevelUp4Pressed(ActionEvent e) {
		pressed++;
		level4 = Integer.parseInt(_level4.getText());
		if (level4 < 5 && pressed == 1) {
			level4++;
			strlevel = Integer.toString(level4);
			_level4.setText(strlevel);
			hp4 = Integer.parseInt(_hp4.getText());
			hp4 *= 1.25;
			strhp4 = Integer.toString(hp4);
			_hp4.setText(strhp4);
		}
		_level4.setText(Integer.toString(level4));
	}

	public void onLevelUp5Pressed(ActionEvent e) {
		pressed++;
		level5 = Integer.parseInt(_level5.getText());
		if (level5 < 5 && pressed == 1) {
			level5++;
			strlevel = Integer.toString(level5);
			_level5.setText(strlevel);
			hp5 = Integer.parseInt(_hp5.getText());
			hp5 *= 1.3;
			strhp5 = Integer.toString(hp5);
			_hp5.setText(strhp5);
		}
		_level5.setText(Integer.toString(level5));
	}

	public static int choice = 0;

	public void onChoose1Pressed(ActionEvent e) {
		choice = 1;
		Client.mainStage.setScene(Client.mainScene);
	}

	public void onChoose2Pressed(ActionEvent e) {
		choice = 2;
		Client.mainStage.setScene(Client.mainScene);
	}

	public void onChoose3Pressed(ActionEvent e) {
		choice = 3;
		Client.mainStage.setScene(Client.mainScene);
	}

	public void onChoose4Pressed(ActionEvent e) {
		choice = 4;
		Client.mainStage.setScene(Client.mainScene);
	}

	public void onChoose5Pressed(ActionEvent e) {
		choice = 5;
		Client.mainStage.setScene(Client.mainScene);
	}
}
