package graphic.aa.view;

import graphic.aa.controller.GameController;
import graphic.aa.controller.MainController;
import graphic.aa.controller.SettingController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainMenu extends Application {

    public Label label;
    public Circle circle;
    public BorderPane pane;

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane borderPane = FXMLLoader.load(MainMenu.class.getResource("/graphic/aa/fxml/main-menu.fxml"));
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void initialize() throws FileNotFoundException {
        Image image;
        if (MainController.getImage().startsWith("/graphic/aa/image/avatar"))
            image = new Image(MainMenu.class.getResource(MainController.getImage()).toExternalForm());
        else {
            InputStream inputStream = new FileInputStream(MainController.getImage());
            image = new Image(inputStream);
        }
        circle.setFill(new ImagePattern(image));
        setModeOfPage();
    }

    private void setModeOfPage() {
        if (!SettingController.isLight()) {
            BackgroundFill backgroundFill = new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY);
            Background background = new Background(backgroundFill);
            pane.setBackground(background);
            pane.getStylesheets().add(getClass().getResource("/graphic/aa/css/dark-mode.css").toExternalForm());
        }
        else {
            pane.getStylesheets().remove(getClass().getResource("/graphic/aa/css/dark-mode.css").toExternalForm());
            BackgroundFill backgroundFill = new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY );
            Background background = new Background(backgroundFill);
            pane.setBackground(background);
        }
    }

    public void goProfileMenu(MouseEvent mouseEvent) throws Exception{
        Menu.getProfileMenu().start(HelloApplication.getStageOfRoot());
    }

    public void goSetting(MouseEvent mouseEvent) throws Exception {
        Menu.getSetting().start(HelloApplication.getStageOfRoot());
    }

    public void startGame(MouseEvent mouseEvent) throws Exception{
        GameMenu gameMenu = new GameMenu();
        GameController.setGame(gameMenu);
        GameMenu.setFinish(false);
        gameMenu.start(HelloApplication.getStageOfRoot());
    }

    public void showScoreBoard(MouseEvent mouseEvent) throws Exception{
        MainController.sortForScoreboard();
        Menu.getScoreboardMenu().start(HelloApplication.getStageOfRoot());
    }

    public void logout(MouseEvent mouseEvent) throws Exception{
        MainController.logout();
        Menu.getHelloApplication().start(HelloApplication.getStageOfRoot());

    }

}
