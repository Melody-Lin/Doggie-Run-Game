package cn.tcp.fp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Medium extends Doggie {
	@FXML
	public ImageView _background1;
	@FXML
	public ImageView _background2;
	@FXML
	public ImageView _dog1;
	@FXML
	public ImageView _dog2;
	@FXML
	public ImageView _dog3;
	@FXML
	public ImageView _dog4;
	@FXML
	public ImageView _dog5;
	@FXML
	public Button _btn;
	@FXML
	public Rectangle _stair;
	@FXML
	public AnchorPane _pane;
	@FXML
	public ImageView _coin1;
	@FXML
	public ImageView _coin2;
	@FXML
	public ImageView _coin3;
	@FXML
	public ImageView _coin4;
	@FXML
	public ImageView _poison;
	@FXML
	public TextField _score;
	@FXML
	public TextField _hp;

	private int BACKGROUND_WIDTH = 1353;
	private ParallelTransition parallelTransition;

	@FXML
	public void initialize() {
		TranslateTransition translateTransition = new TranslateTransition(Duration.millis(8000), _background1);
		translateTransition.setFromX(0);
		translateTransition.setToX(-1 * BACKGROUND_WIDTH);
		translateTransition.setInterpolator(Interpolator.LINEAR);
		TranslateTransition translateTransition2 = new TranslateTransition(Duration.millis(8000), _background2);
		translateTransition2.setFromX(0);
		translateTransition2.setToX(-1 * BACKGROUND_WIDTH);
		translateTransition2.setInterpolator(Interpolator.LINEAR);
		parallelTransition = new ParallelTransition(translateTransition, translateTransition2);
		parallelTransition.setCycleCount(Animation.INDEFINITE);
		parallelTransition.statusProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue == Animation.Status.RUNNING) {
				_btn.setText("||");

			} else {
				_btn.setText(">");
			}
		});
	}

	private Timeline animation1 = new Timeline(new KeyFrame(Duration.millis(1000), (a) -> {
		stair();
	}));
	private Timeline animation2;

	public void startAmination() {
		parallelTransition.play();
		animation1.setCycleCount(Timeline.INDEFINITE); // set cycle count indefinite
		animation1.play(); // play animation
		for (int i = 0; i < arr.size(); i++) {
			arr.get(i).play();
		}
		animation5.setCycleCount(Timeline.INDEFINITE);
		animation5.play();
		animation10.setCycleCount(Timeline.INDEFINITE);
		animation10.play();
		animation11.play();
		for (int i = 0; i < c1.size(); i++) {
			c1.get(i).play();
		}
		for (int i = 0; i < c2.size(); i++) {
			c2.get(i).play();
		}
		for (int i = 0; i < c3.size(); i++) {
			c3.get(i).play();
		}
		for (int i = 0; i < c4.size(); i++) {
			c4.get(i).play();
		}
		for (int i = 0; i < ps.size(); i++) {
			ps.get(i).play();
		}
	}

	public void pauseAnimation() {
		parallelTransition.pause();
		animation1.pause();
		animation5.pause();
		animation10.pause();
		animation11.pause();
		for (int i = 0; i < arr.size(); i++) {
			arr.get(i).pause();
		}
		for (int i = 0; i < c1.size(); i++) {
			c1.get(i).pause();
		}
		for (int i = 0; i < c2.size(); i++) {
			c2.get(i).pause();
		}
		for (int i = 0; i < c3.size(); i++) {
			c3.get(i).pause();
		}
		for (int i = 0; i < c4.size(); i++) {
			c4.get(i).pause();
		}
		for (int i = 0; i < ps.size(); i++) {
			ps.get(i).pause();
		}
	}

	public void onButtonPressed(ActionEvent e) {
		if (parallelTransition.getStatus() == Animation.Status.RUNNING) {
			pauseAnimation();
		} else {
			startAmination();
		}
	}

	ArrayList<Timeline> arr = new ArrayList();
	ArrayList<Double> stairHeight = new ArrayList();
	ArrayList<Rectangle> stair = new ArrayList();

	public void stair() {
		Rectangle newstair = new Rectangle();
		newstair.setWidth(_stair.getWidth());
		newstair.setHeight(_stair.getHeight());
		newstair.setLayoutX(1180);
		newstair.setLayoutY(Math.random() * 100 + 350);
		stair.add(newstair);
		stairHeight.add(newstair.getLayoutY());
		newstair.setFill(randomColor());
		_pane.getChildren().add(newstair);
		animation2 = new Timeline(new KeyFrame(Duration.millis(1000), (a) -> {
			newstair.setLayoutX(newstair.getLayoutX() - 130);
		}));
		arr.add(animation2);
		animation2.setCycleCount(Timeline.INDEFINITE);
		animation2.play();
	}

	public Paint randomColor() {
		Random random = new Random();
		int r = random.nextInt(255);
		int g = random.nextInt(255);
		int b = random.nextInt(255);
		return Color.rgb(r, g, b);
	}

	String str_score = "";
	String present_score = "";
	String doghp = "";
	int score = 0;
	int hp = 0;
	boolean dead = false;
	static int finalscore = 0;

	public void Loop() throws IOException {
		if (dead == false) {
			score = Integer.parseInt(_score.getText());
			hp = Integer.parseInt(_hp.getText());
			velocityY = -12.0;
			Update();
			str_score = Integer.toString(score);
			_score.setText(str_score);
			doghp = Integer.toString(hp);
			_hp.setText(doghp);
		} else {
			pauseAnimation();
			finalscore = score;
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Dead_easy.fxml"));
			Parent dead = loader.load();
			Scene DeadScene = new Scene(dead);
			Client.mainStage.setScene(DeadScene);
		}
	}

	Timeline animation3;
	int i = 0;
	int k = 1;
	double positionY = 450;
	double velocityY = 0.0;
	double gravity = 9.8 / 60;

	public void Update() {
		if (animation3 != null) {
			animation3.stop();
		}
		animation3 = new Timeline(new KeyFrame(Duration.millis(1000 / 60), (a) -> {
			velocityY += gravity;
			positionY += velocityY;
			if (positionY > stairHeight.get(i) - 100) {
				positionY = stairHeight.get(i) - 100;
				velocityY = 0.0;
			}
			if (stair.get(i).getLayoutX() <= 250) {
				i++;
			}
			if (positionY > 450) {
				positionY = 450;
				velocityY = 0.0;
			}
			if (positionY < 30.0) {
				positionY = 30.0;
				velocityY = 0.0;
			}
			if (choice == 1) {
				_dog1.setLayoutY(positionY);
			} else if (choice == 2) {
				_dog2.setLayoutY(positionY + 30);
			} else if (choice == 3) {
				_dog3.setLayoutY(positionY + 25);
			} else if (choice == 4) {
				_dog4.setLayoutY(positionY);
			} else if (choice == 5) {
				_dog5.setLayoutY(positionY + 20);
			}
		}));
		animation3.setCycleCount(Timeline.INDEFINITE);
		animation3.play();
		if (choice == 1) {
			if (i >= 2) {
				if ((_dog1.getLayoutY() - iv_arr1.get(i - 2).getLayoutY()) < 100
						&& (_dog1.getLayoutY() - iv_arr1.get(i - 2).getLayoutY()) > -100) {
					iv_arr1.get(i - 2).setVisible(false);
					score += 500;
				}
			}
			if (i >= 1) {
				if ((_dog1.getLayoutY() - iv_arr2.get(i - 1).getLayoutY()) < 100
						&& (_dog1.getLayoutY() - iv_arr2.get(i - 1).getLayoutY()) > -100) {
					iv_arr2.get(i - 1).setVisible(false);
					score += 300;
				}
			}
			if ((_dog1.getLayoutY() - iv_arr3.get(i).getLayoutY()) < 100
					&& (_dog1.getLayoutY() - iv_arr3.get(i).getLayoutY()) > -100) {
				iv_arr3.get(i).setVisible(false);
				score += 250;
			}
			if ((_dog1.getLayoutY() - iv_arr4.get(i).getLayoutY()) < 100
					&& (_dog1.getLayoutY() - iv_arr4.get(i).getLayoutY()) > -100) {
				iv_arr4.get(i).setVisible(false);
				score += 250;
			}
			if (i >= 2 && i == 2 * k + (k - 1)) {
				if ((_dog1.getLayoutY() - iv_arr5.get(i - 2 * k).getLayoutY()) < 90
						&& (_dog1.getLayoutY() - iv_arr5.get(i - 2 * k).getLayoutY()) > -90) {
					iv_arr5.get(i - 2 * k).setVisible(false);
					if (hp - 10 <= 0) {
						hp = 0;
					} else {
						hp -= 10;
					}
				}
				k++;
			}
		} else if (choice == 2) {
			if (i >= 2) {
				if ((_dog2.getLayoutY() - iv_arr1.get(i - 2).getLayoutY()) < 100
						&& (_dog2.getLayoutY() - iv_arr1.get(i - 2).getLayoutY()) > -100) {
					iv_arr1.get(i - 2).setVisible(false);
					score += 500;
				}
			}
			if (i >= 1) {
				if ((_dog2.getLayoutY() - iv_arr2.get(i - 1).getLayoutY()) < 100
						&& (_dog2.getLayoutY() - iv_arr2.get(i - 1).getLayoutY()) > -100) {
					iv_arr2.get(i - 1).setVisible(false);
					score += 300;
				}
			}
			if ((_dog2.getLayoutY() - iv_arr3.get(i).getLayoutY()) < 100
					&& (_dog2.getLayoutY() - iv_arr3.get(i).getLayoutY()) > -100) {
				iv_arr3.get(i).setVisible(false);
				score += 250;
			}
			if ((_dog2.getLayoutY() - iv_arr4.get(i).getLayoutY()) < 100
					&& (_dog2.getLayoutY() - iv_arr4.get(i).getLayoutY()) > -100) {
				iv_arr4.get(i).setVisible(false);
				score += 250;
			}
			if (i >= 2 && i == 2 * k + (k - 1)) {
				if ((_dog2.getLayoutY() - iv_arr5.get(i - 2 * k).getLayoutY()) < 100
						&& (_dog2.getLayoutY() - iv_arr5.get(i - 2 * k).getLayoutY()) > -100) {
					iv_arr5.get(i - 2 * k).setVisible(false);
					if (hp - 15 <= 0) {
						hp = 0;
					} else {
						hp -= 10;
					}
				}
				k++;
			}
		} else if (choice == 3) {
			if (i >= 2) {
				if ((_dog3.getLayoutY() - iv_arr1.get(i - 2).getLayoutY()) < 100
						&& (_dog3.getLayoutY() - iv_arr1.get(i - 2).getLayoutY()) > -100) {
					iv_arr1.get(i - 2).setVisible(false);
					score += 500;
				}
			}
			if (i >= 1) {
				if ((_dog3.getLayoutY() - iv_arr2.get(i - 1).getLayoutY()) < 100
						&& (_dog3.getLayoutY() - iv_arr2.get(i - 1).getLayoutY()) > -100) {
					iv_arr2.get(i - 1).setVisible(false);
					score += 300;
				}
			}
			if ((_dog3.getLayoutY() - iv_arr3.get(i).getLayoutY()) < 100
					&& (_dog3.getLayoutY() - iv_arr3.get(i).getLayoutY()) > -100) {
				iv_arr3.get(i).setVisible(false);
				score += 250;
			}
			if ((_dog3.getLayoutY() - iv_arr4.get(i).getLayoutY()) < 100
					&& (_dog3.getLayoutY() - iv_arr4.get(i).getLayoutY()) > -100) {
				iv_arr4.get(i).setVisible(false);
				score += 250;
			}
			if (i >= 2 && i == 2 * k + (k - 1)) {
				if ((_dog3.getLayoutY() - iv_arr5.get(i - 2 * k).getLayoutY()) < 100
						&& (_dog3.getLayoutY() - iv_arr5.get(i - 2 * k).getLayoutY()) > -100) {
					iv_arr5.get(i - 2 * k).setVisible(false);
					if (hp - 20 <= 0) {
						hp = 0;
					} else {
						hp -= 10;
					}
				}
				k++;
			}
		} else if (choice == 4) {
			if (i >= 2) {
				if ((_dog4.getLayoutY() - iv_arr1.get(i - 2).getLayoutY()) < 100
						&& (_dog4.getLayoutY() - iv_arr1.get(i - 2).getLayoutY()) > -100) {
					iv_arr1.get(i - 2).setVisible(false);
					score += 500;
				}
			}
			if (i >= 1) {
				if ((_dog4.getLayoutY() - iv_arr2.get(i - 1).getLayoutY()) < 100
						&& (_dog4.getLayoutY() - iv_arr2.get(i - 1).getLayoutY()) > -100) {
					iv_arr2.get(i - 1).setVisible(false);
					score += 300;
				}
			}
			if ((_dog4.getLayoutY() - iv_arr3.get(i).getLayoutY()) < 100
					&& (_dog4.getLayoutY() - iv_arr3.get(i).getLayoutY()) > -100) {
				iv_arr3.get(i).setVisible(false);
				score += 250;
			}
			if ((_dog4.getLayoutY() - iv_arr4.get(i).getLayoutY()) < 100
					&& (_dog4.getLayoutY() - iv_arr4.get(i).getLayoutY()) > -100) {
				iv_arr4.get(i).setVisible(false);
				score += 250;
			}
			if (i >= 2 && i == 2 * k + (k - 1)) {
				if ((_dog4.getLayoutY() - iv_arr5.get(i - 2 * k).getLayoutY()) < 100
						&& (_dog4.getLayoutY() - iv_arr5.get(i - 2 * k).getLayoutY()) > -100) {
					iv_arr5.get(i - 2 * k).setVisible(false);
					if (hp - 15 <= 0) {
						hp = 0;
					} else {
						hp -= 15;
					}
				}
				k++;
			}
		} else if (choice == 5) {
			if (i >= 2) {
				if ((_dog5.getLayoutY() - iv_arr1.get(i - 2).getLayoutY()) < 100
						&& (_dog5.getLayoutY() - iv_arr1.get(i - 2).getLayoutY()) > -100) {
					iv_arr1.get(i - 2).setVisible(false);
					score += 500;
				}
			}
			if (i >= 1) {
				if ((_dog5.getLayoutY() - iv_arr2.get(i - 1).getLayoutY()) < 100
						&& (_dog5.getLayoutY() - iv_arr2.get(i - 1).getLayoutY()) > -100) {
					iv_arr2.get(i - 1).setVisible(false);
					score += 300;
				}
			}
			if ((_dog5.getLayoutY() - iv_arr3.get(i).getLayoutY()) < 100
					&& (_dog5.getLayoutY() - iv_arr3.get(i).getLayoutY()) > -100) {
				iv_arr3.get(i).setVisible(false);
				score += 250;
			}
			if ((_dog5.getLayoutY() - iv_arr4.get(i).getLayoutY()) < 100
					&& (_dog5.getLayoutY() - iv_arr4.get(i).getLayoutY()) > -100) {
				iv_arr4.get(i).setVisible(false);
				score += 250;
			}
			if (i >= 2 && i == 2 * k + (k - 1)) {
				if ((_dog5.getLayoutY() - iv_arr5.get(i - 2 * k).getLayoutY()) < 100
						&& (_dog5.getLayoutY() - iv_arr5.get(i - 2 * k).getLayoutY()) > -100) {
					iv_arr5.get(i - 2 * k).setVisible(false);
					if (hp - 30 <= 0) {
						hp = 0;
					} else {
						hp -= 10;
					}
				}
				k++;
			}
		}
		if (hp == 0) {
			animation11.pause();
			dead = true;
		}
	}

	Timeline animation10 = new Timeline(new KeyFrame(Duration.millis(3000), (a) -> {
		poison();
	}));

	Timeline animation4;
	Timeline animation6;
	Timeline animation7;
	Timeline animation8;
	Timeline animation9;
	Timeline animation5 = new Timeline(new KeyFrame(Duration.millis(1000), (a) -> {
		coin1();
		coin2();
		coin3();
		coin4();
	}));

	ArrayList<Timeline> c1 = new ArrayList();
	ArrayList<Timeline> c2 = new ArrayList();
	ArrayList<Timeline> c3 = new ArrayList();
	ArrayList<Timeline> c4 = new ArrayList();
	ArrayList<Timeline> ps = new ArrayList();
	ArrayList<ImageView> iv_arr1 = new ArrayList();
	ArrayList<ImageView> iv_arr2 = new ArrayList();
	ArrayList<ImageView> iv_arr3 = new ArrayList();
	ArrayList<ImageView> iv_arr4 = new ArrayList();
	ArrayList<ImageView> iv_arr5 = new ArrayList();

	public void coin1() {
		ImageView iv1 = new ImageView();
		iv1.setImage(_coin1.getImage());
		iv1.setFitHeight(_coin1.getFitHeight());
		iv1.setFitWidth(_coin1.getFitWidth());
		iv1.setLayoutX(1485);
		iv1.setLayoutY(155);
		_pane.getChildren().add(iv1);
		animation4 = new Timeline(new KeyFrame(Duration.millis(1000), (a) -> {
			iv1.setLayoutX(iv1.getLayoutX() - 130);
		}));
		animation4.setCycleCount(Timeline.INDEFINITE);
		animation4.play();
		c1.add(animation4);
		iv_arr1.add(iv1);
	}

	public void coin2() {
		ImageView iv2 = new ImageView();
		iv2.setImage(_coin2.getImage());
		iv2.setFitHeight(_coin2.getFitHeight());
		iv2.setFitWidth(_coin2.getFitWidth());
		iv2.setLayoutX(1300);
		iv2.setLayoutY(170);
		_pane.getChildren().add(iv2);
		animation6 = new Timeline(new KeyFrame(Duration.millis(1000), (a) -> {
			iv2.setLayoutX(iv2.getLayoutX() - 130);
		}));
		animation6.setCycleCount(Timeline.INDEFINITE);
		animation6.play();
		c2.add(animation6);
		iv_arr2.add(iv2);
	}

	public void coin3() {
		ImageView iv3 = new ImageView();
		iv3.setImage(_coin3.getImage());
		iv3.setFitHeight(_coin3.getFitHeight());
		iv3.setFitWidth(_coin3.getFitWidth());
		iv3.setLayoutX(1200);
		iv3.setLayoutY(100);
		_pane.getChildren().add(iv3);
		animation7 = new Timeline(new KeyFrame(Duration.millis(1000), (a) -> {
			iv3.setLayoutX(iv3.getLayoutX() - 130);
		}));
		animation7.setCycleCount(Timeline.INDEFINITE);
		animation7.play();
		c3.add(animation7);
		iv_arr3.add(iv3);
	}

	public void coin4() {
		ImageView iv4 = new ImageView();
		iv4.setImage(_coin4.getImage());
		iv4.setFitHeight(_coin4.getFitHeight());
		iv4.setFitWidth(_coin4.getFitWidth());
		iv4.setLayoutX(1265);
		iv4.setLayoutY(120);
		_pane.getChildren().add(iv4);
		animation8 = new Timeline(new KeyFrame(Duration.millis(1000), (a) -> {
			iv4.setLayoutX(iv4.getLayoutX() - 130);
		}));
		animation8.setCycleCount(Timeline.INDEFINITE);
		animation8.play();
		c4.add(animation8);
		iv_arr4.add(iv4);
	}

	public void poison() {
		ImageView iv5 = new ImageView();
		iv5.setImage(_poison.getImage());
		iv5.setFitHeight(_poison.getFitHeight());
		iv5.setFitWidth(_poison.getFitWidth());
		iv5.setLayoutX(1200);
		iv5.setLayoutY(Math.random() * 100 + 215);
		_pane.getChildren().add(iv5);
		animation9 = new Timeline(new KeyFrame(Duration.millis(1000), (a) -> {
			iv5.setLayoutX(iv5.getLayoutX() - 130);
		}));
		animation9.setCycleCount(Timeline.INDEFINITE);
		animation9.play();
		ps.add(animation9);
		iv_arr5.add(iv5);
	}

	Timeline animation11;
	public EventHandler<KeyEvent> OnKeyPressed = (e) -> {
		if (e.getCode() == KeyCode.ENTER) {
			if (choice == 1) {
				_dog1.setLayoutX(255);
				_dog1.setLayoutY(450);
				_hp.setText(strhp1);
			} else if (choice == 2) {
				_dog2.setLayoutX(255);
				_dog2.setLayoutY(470);
				_hp.setText(strhp2);
			} else if (choice == 3) {
				_dog3.setLayoutX(255);
				_dog3.setLayoutY(460);
				_hp.setText(strhp3);
			} else if (choice == 4) {
				_dog4.setLayoutX(255);
				_dog4.setLayoutY(450);
				_hp.setText(strhp4);
			} else if (choice == 5) {
				_dog5.setLayoutX(255);
				_dog5.setLayoutY(460);
				_hp.setText(strhp5);
			}
			hp = Integer.parseInt(_hp.getText());
			animation11 = new Timeline(new KeyFrame(Duration.millis(1000), (a) -> {
				if (hp - 1 <= 0) {
					hp = 0;
				} else {
					hp -= 1;
				}
				doghp = Integer.toString(hp);
				_hp.setText(doghp);
			}));

			animation11.setCycleCount(Timeline.INDEFINITE);
			animation11.play();
			if (hp == 0) {
				animation11.pause();
				dead = true;
			}
		}
		if (e.getCode() == KeyCode.A) {
			try {
				Loop();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	};

	public void onBackPressed(ActionEvent e) {
		Client.mainStage.setScene(Client.mainScene);
	}
	
}
