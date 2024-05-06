package graphic.aa.view;

import graphic.aa.controller.GameController;
import graphic.aa.controller.SettingController;
import graphic.aa.controller.SongController;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.Collections;


public class GameMenu extends Application {
    public Label guid;
    public ImageView mute;

    private Pane pane;

    private Circle circle;

    private Label scoreView;

    private Circle chooseCircle;

    private Label wind;
    private ArrayList <Circle> circles = new ArrayList<>();

    private Text text;

    private int score = 0;
    private ArrayList <Line> lines = new ArrayList<>();

    private int rate = 1;

    private boolean ps = true;

    private AudioClip song ;

    private AudioClip media = new AudioClip(getClass().getResource("/graphic/aa/media/shoot.wav").toString());
    private final int fixedBalls = SettingController.getBalls();

    private boolean songOn = true;
    private int hard = SettingController.getHardDegree();
    private int balls  = fixedBalls;

    private boolean visibleOrNot = true;

    private static boolean finish = false;

    private int numbers = 0;

    private int pause = 0;

    private int degree = 0;

    private int ice = 1;
    private ArrayList <Timeline> timelines = new ArrayList<>();
    @Override
    public void start(Stage stage) throws Exception {
        pane = FXMLLoader.load(GameMenu.class.getResource("/graphic/aa/fxml/game.fxml"));
        circle = createCircle();
        addCircleAndLine(pane, circle);
        pane.getChildren().add(circle);
        playTimelines();
        scoreView = new Label();
        scoreView.setText(Integer.toString((fixedBalls - balls) * hard));
        scoreView.setLayoutX(744.0);
        scoreView.setLayoutY(686.0);
        wind = new Label();
        wind.setText(Integer.toString(degree));
        wind.setLayoutX(746.0);
        wind.setLayoutY(652.0);
        pane.getChildren().addAll(scoreView, wind);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        pane.requestFocus();
        makeCircle(pane);
        startMove(pane);
        if (pause == 0)
            setTimeLine();
        stage.show();
    }

    private void playTimelines() {
        for (Timeline timeline : timelines) {
            timeline.play();
        }
    }

    private void leaveGame() throws Exception{
        Menu.getMainMenu().start(HelloApplication.getStageOfRoot());
    }
    @FXML
    public void initialize () {
        guid.setText(GameController.getShootKey());
        song = new AudioClip(getClass().getResource("/graphic/aa/media/song"  + SongController.getSong() + ".mp3").toString());
        song.play();
    }
    private void makeCircle(Pane pane) {
            pane.requestFocus();
            if (balls == 0) {
                setScore((fixedBalls - balls) * hard);
                scoreView.setText(Integer.toString(getScore()));
                if (GameController.hasLoggedInPlayer())
                    GameController.setScore(getScore());
                for (int i = 0; i < timelines.size(); i++)
                    timelines.get(i).stop();
                finish = true;
                finishGame();
            }
            if (balls > 0 && !finish && ps) {
                Circle circle2 = new Circle(400, 700, 15);
                text = new Text(circle2.getCenterX() - 4, circle2.getCenterY() + 4, "" + balls);
                if (balls >= fixedBalls * 3 / 4) {
                    text.setFill(Paint.valueOf("white"));
                } else if (balls < fixedBalls * 3 / 4 && balls >= fixedBalls / 2) {
                    text.setFill(Paint.valueOf("yellow"));
                } else text.setFill(Paint.valueOf("blue"));
                scoreView.setText("0");
                setScore((fixedBalls - balls) * hard);
                pane.getChildren().addAll(circle2, text);
                setChooseCircle(circle2);
            }
    }

    private void startMove(Pane pane) {
            pane.requestFocus();
            Circle circle2 = getChooseCircle();
        if (finish == true) {
            if (GameController.hasLoggedInPlayer())
                GameController.setScore(getScore());
            finishGame();
        }
        else {
            pane.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    String keyName = keyEvent.getCode().getName();
                    if (keyName.equals(GameController.getShootKey())) {
                        shoot(circle2, pane);
                        scoreView.setText(Integer.toString(getScore()));
                    }
                    if (keyName.equals("Tab")) {
                        keyEvent.consume();
                        changeIce(pane);
                        Timeline timelineIce = new Timeline(new KeyFrame(Duration.millis((hard * 2 + 1) * 500), actionEvent -> changeIce(pane)));
                        timelineIce.setCycleCount(1);
                        timelineIce.play();
                        FadeTransition fadeTransition = new FadeTransition();
                        fadeTransition.setNode(circle);
                        fadeTransition.setDuration(Duration.millis((hard * 2 + 1 ) * 500));
                        fadeTransition.setFromValue(1);
                        fadeTransition.setToValue(0.5);
                        fadeTransition.play();
                        fadeTransition.setOnFinished(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                circle.setOpacity(1);
                                pane.requestFocus();
                            }
                        });
                    }
                    if (balls < fixedBalls / 4) {
                        if (keyName.equals("Left")) {
                            if (circle2.getCenterX() > 18)
                                circle2.setCenterX(circle2.getCenterX() - 10);
                        }
                        if (keyName.equals("Right")) {
                            if (circle2.getCenterX() < 782)
                                circle2.setCenterX(circle2.getCenterX() + 10);
                        }
                    }
                }
            });
        }
    }
    private void changeIce(Pane pane) {
        if (ice == 1)
            ice = 2;
        else ice = 1;
    }
    private void shoot(Circle circle, Pane pane) {
        if (finish == true) {
            if (GameController.hasLoggedInPlayer())
                GameController.setScore(getScore());
            finishGame();
        }
        balls--;
        if (balls >= 0 && !finish && ps) {
           media.play();
            ShootingAnimation shootingAnimation = new ShootingAnimation(circles, lines, timelines, circle, pane, degree);
            shootingAnimation.play();
            if (balls == 3 * fixedBalls / 4 ) {
                phase2();
            }
            if (balls == fixedBalls / 2 ) {
                phase3();
            } if (balls == fixedBalls /4 ) {
                phase4();
            }
            makeCircle(pane);
            startMove(pane);
        }
    }

    private void finishGame() {
        if (finish == true && balls == 0) {
            Background background = new Background(new BackgroundFill(Color.GREEN, null,null));
            pane.setBackground(background);
        }
        else if (finish == true && balls != 0) {
            Background background = new Background(new BackgroundFill(Color.RED, null,null));
            pane.setBackground(background);
        }
    }

    private void phase4() {
        changeDegree();
        Timeline timeline4 = new Timeline(new KeyFrame(Duration.millis(300),actionEvent -> changeDegree()));
        timelines.add(timeline4);
        timeline4.setCycleCount(-1);
        timeline4.play();
    }

    private void changeDegree() {
        if (hard == 1) {
            if (degree == -12)
                degree = 12;
            else {
                degree--;
            }
        }
        else if (hard == 2) {
            if (degree == -15)
                degree = 15;
            else degree--;
        }
        else {
            if (degree == -18)
                degree = 18;
            else degree--;
        }
        wind.setText(Integer.toString(degree));
    }

    private void phase3() {
        Timeline timeline3 = new Timeline(new KeyFrame(Duration.millis(1000), actionEvent -> visibility()));
        timelines.add(timeline3);
        timeline3.setCycleCount(-1);
        timeline3.play();
    }

    private void visibility() {
        visibleOrNot = !visibleOrNot;
        for (int i = 0 ; i < circles.size(); i++) {
            circles.get(i).setVisible(visibleOrNot);
            lines.get(i).setVisible(visibleOrNot);
        }
    }

    private void phase2() {
        changeRate();
        Timeline timeline1=new Timeline(new KeyFrame(Duration.millis(3500), actionEvent -> changeRate()));
        timelines.add(timeline1);
        timeline1.setCycleCount(-1);
        timeline1.play();
        changeRadius();
        Timeline timeline2 = new Timeline(new KeyFrame(Duration.millis(100), actionEvent -> changeRadius()));
        timelines.add(timeline2);
        timeline2.setCycleCount(-1);
        timeline2.play();
    }

    private void changeRadius() {
        numbers += 1;
        int mode = numbers % 10;
        for (int i = 0; i < circles.size(); i++) {
            circles.get(i).setRadius((15.0 * mode / 100) + 15.0);
        }
    }

    private void setTimeLine() {
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), actionEvent -> rotateTransition()));
            timelines.add(timeline);
            timeline.setCycleCount(-1);
            timeline.play();
    }

    private void rotateTransition() {
        if (ps) {
            if (hard == 1)
                ice = 1;
            Rotate rotate = new Rotate(1 * rate * hard / ice, 400, 300);
            for (int i = 0; i < circles.size(); i++) {
                circles.get(i).getTransforms().add(rotate);
                lines.get(i).getTransforms().add(rotate);
            }
        }
    }

    private void changeRate() {
        rate *= -1;
    }

    private void addCircleAndLine(Pane pane, Circle mainCircle) {
        Circle circle1 = new Circle(400 + 220 * Math.cos(GameController.getCircleMap(0)), 300 + 220 * Math.sin(GameController.getCircleMap(0)), 15);
        Line line1 = new Line(mainCircle.getCenterX(), mainCircle.getCenterY(), circle1.getCenterX(), circle1.getCenterY());
        Circle circle2 = new Circle(400 + 220 * Math.cos(GameController.getCircleMap(1)), 300 + 220 *  Math.sin(GameController.getCircleMap(1)), 15);
        Line line2 = new Line(mainCircle.getCenterX(), mainCircle.getCenterY(), circle2.getCenterX(), circle2.getCenterY());
        Circle circle3 = new Circle(400 + 220 * Math.cos(GameController.getCircleMap(2)), 300 + 220 * Math.sin(GameController.getCircleMap(2)), 15);
        Line line3 = new Line(mainCircle.getCenterX(), mainCircle.getCenterY(), circle3.getCenterX(), circle3.getCenterY());
        Circle circle4 = new Circle(400 + 220 * Math.cos(GameController.getCircleMap(3)), 300 + 220 * Math.sin(GameController.getCircleMap(3)), 15);
        Line line4 = new Line(mainCircle.getCenterX(), mainCircle.getCenterY(), circle4.getCenterX(), circle4.getCenterY());
        Circle circle5 = new Circle(400 + 220 * Math.cos(GameController.getCircleMap(4)), 300 + 220 * Math.sin(GameController.getCircleMap(4)), 15);
        Line line5 = new Line(mainCircle.getCenterX(), mainCircle.getCenterY(), circle5.getCenterX(), circle5.getCenterY());
        Collections.addAll(circles, circle1,circle2,circle3,circle4,circle5);
        Collections.addAll(lines, line1, line2, line3, line4, line5);
        pane.getChildren().addAll(line1, circle1, line2, circle2, line3, circle3, line4, circle4, line5, circle5);
    }

    private Circle createCircle() {
        Circle circle = new Circle(400, 300, 120);
        circle.setFill(Color.BLACK);
        return circle;
    }

    public void mute(MouseEvent mouseEvent) {
        Image imageMute = new Image(getClass().getResource("/graphic/aa/image/mute.png").toString());
        Image imageUnMute = new Image(getClass().getResource("/graphic/aa/image/unmute.png").toString());
        if (songOn == true) {
            songOn = !songOn;
            song.stop();
            mute.setImage(imageMute);
        }
        else {
            songOn = !songOn;
            song.play();
            mute.setImage(imageUnMute);
        }
    }

    public static boolean isFinish() {
        return finish;
    }

    public static void setFinish(boolean finish) {
        GameMenu.finish = finish;
    }

    public void leave(MouseEvent mouseEvent) throws Exception{
        song.stop();
        if (GameController.hasLoggedInPlayer())
            Menu.getMainMenu().start(HelloApplication.getStageOfRoot());
        else Menu.getHelloApplication().start(HelloApplication.getStageOfRoot());
    }

    public void chooseSong(MouseEvent mouseEvent) throws Exception{
        pause = 1;
        for (Timeline timeline : timelines) {
            timeline.pause();
        }
        song.stop();
        Menu.getSongMenu().start(HelloApplication.getStageOfRoot());
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void restart(MouseEvent mouseEvent) throws Exception{
        song.stop();
        GameMenu gameMenu = new GameMenu();
        GameController.setGame(gameMenu);
        GameMenu.setFinish(false);
        gameMenu.start(HelloApplication.getStageOfRoot());
    }

    public Circle getChooseCircle() {
        return chooseCircle;
    }

    public void setChooseCircle(Circle chooseCircle) {
        this.chooseCircle = chooseCircle;
    }
}
