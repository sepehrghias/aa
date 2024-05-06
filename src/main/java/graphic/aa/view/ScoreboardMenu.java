package graphic.aa.view;

import graphic.aa.controller.MainController;
import graphic.aa.model.Player;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ScoreboardMenu extends Application {

    ArrayList<Player> topPlayer ;
    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = FXMLLoader.load(ProfileMenu.class.getResource("/graphic/aa/fxml/scoreboard.fxml"));
        topPlayer = MainController.getTopPlayer();
        Label username;
        Label score;
        Label count;
        double y = 97.0;
        for (int i = 0; i < topPlayer.size(); i++) {
            if (i <= 2){
                username = new Label(topPlayer.get(i).getUsername());
                username.setLayoutX(220.0);
                username.setLayoutY(y);
                score = new Label(Integer.toString(topPlayer.get(i).getScore()));
                score.setLayoutX(430.0);
                score.setLayoutY(y);
                y += 44;
                pane.getChildren().addAll(score, username);
            }
            else {
                count = new Label(Integer.toString(i+1));
                count.setLayoutY(y);
                count.setLayoutX(124.0);
                username = new Label(topPlayer.get(i).getUsername());
                username.setLayoutX(220.0);
                username.setLayoutY(y);
                score = new Label(Integer.toString(topPlayer.get(i).getScore()));
                score.setLayoutX(430.0);
                score.setLayoutY(y);
                pane.getChildren().addAll(score, username, count);
                y += 40;
            }

            if (i == 5)
                break;
        }
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    public void back(MouseEvent mouseEvent) throws Exception{
        Menu.getMainMenu().start(HelloApplication.getStageOfRoot());
    }
}
