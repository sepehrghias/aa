package graphic.aa.view;

import graphic.aa.controller.AvatarController;
import graphic.aa.controller.ProfileController;
import graphic.aa.controller.SettingController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AvatarMenu extends Application {
    public Circle mainAvatar;
    public Circle firstAvatar;
    public Circle secondAvatar;
    public Circle thirdAvatar;
    public Circle forthAvatar;
    public Circle sixAvatar;
    public Circle fiveAvatar;
    public BorderPane pane;

    private Image image1 = new Image(AvatarMenu.class.getResource("/graphic/aa/image/avatar1.png").toExternalForm());
    private Image image2 = new Image(AvatarMenu.class.getResource("/graphic/aa/image/avatar2.png").toExternalForm());
    private Image image3 = new Image(AvatarMenu.class.getResource("/graphic/aa/image/avatar3.png").toExternalForm());
    private Image image4 = new Image(AvatarMenu.class.getResource("/graphic/aa/image/avatar4.png").toExternalForm());
    private Image image5 = new Image(AvatarMenu.class.getResource("/graphic/aa/image/avatar5.png").toExternalForm());
    private Image image6 = new Image(AvatarMenu.class.getResource("/graphic/aa/image/avatar6.png").toExternalForm());

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(AvatarMenu.class.getResource("/graphic/aa/fxml/avatar-menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void initialize() throws FileNotFoundException {
        Image image;
        if (AvatarController.getImage().startsWith("/graphic/aa/image/avatar"))
            image = new Image(AvatarMenu.class.getResource(ProfileController.getImage()).toExternalForm());
        else {
            InputStream inputStream = new FileInputStream(AvatarController.getImage());
            image = new Image(inputStream);
        }
        mainAvatar.setFill(new ImagePattern(image));
        firstAvatar.setFill(new ImagePattern(image1));
        secondAvatar.setFill(new ImagePattern(image2));
        thirdAvatar.setFill(new ImagePattern(image3));
        forthAvatar.setFill(new ImagePattern(image4));
        fiveAvatar.setFill(new ImagePattern(image5));
        sixAvatar.setFill(new ImagePattern(image6));
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

    public void back(MouseEvent mouseEvent) throws Exception{
        Menu.getProfileMenu().start(HelloApplication.getStageOfRoot());
    }

    public void firstClicked(MouseEvent mouseEvent) {
        ProfileController.changeAvatar(1);
        mainAvatar.setFill(new ImagePattern(image1));
    }

    public void secondClicked(MouseEvent mouseEvent) {
        ProfileController.changeAvatar(2);
        mainAvatar.setFill(new ImagePattern(image2));
    }

    public void thirdClicked(MouseEvent mouseEvent) {
        ProfileController.changeAvatar(3);
        mainAvatar.setFill(new ImagePattern(image3));

    }

    public void forthClicked(MouseEvent mouseEvent) {
        ProfileController.changeAvatar(4);
        mainAvatar.setFill(new ImagePattern(image4));
    }

    public void fiveClicked(MouseEvent mouseEvent) {
        ProfileController.changeAvatar(5);
        mainAvatar.setFill(new ImagePattern(image5));
    }

    public void sixClicked(MouseEvent mouseEvent) {
        ProfileController.changeAvatar(6);
        mainAvatar.setFill(new ImagePattern(image6));
    }

    public void chooseAvatar(MouseEvent mouseEvent) throws FileNotFoundException {
        FileChooser fileChooser =  new FileChooser();
        File file = fileChooser.showOpenDialog(HelloApplication.getStageOfRoot());
        if (file != null) {
            AvatarController.changeImage(file.getPath());
            InputStream inputStream = new FileInputStream(file.getPath());
            Image image = new Image(inputStream);
            mainAvatar.setFill(new ImagePattern(image));
        }
    }
}
