package graphic.aa.view;

import graphic.aa.controller.AvatarController;
import graphic.aa.controller.ProfileController;
import graphic.aa.controller.SettingController;
import graphic.aa.model.DataBase;
import graphic.aa.view.message.ProfileMenuMessage;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ProfileMenu extends Application {

    public Label label;
    public TextField oldUsername;
    public TextField oldPassword;
    public TextField newUsername;
    public TextField newPassword;
    public Circle circle;
    public BorderPane pane;

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane borderPane = FXMLLoader.load(ProfileMenu.class.getResource("/graphic/aa/fxml/profile-menu.fxml"));
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void initialize() throws FileNotFoundException {
        Image image;
        if (ProfileController.getImage().startsWith("/graphic/aa/image/avatar"))
            image = new Image(ProfileMenu.class.getResource(ProfileController.getImage()).toExternalForm());
        else {
            InputStream inputStream = new FileInputStream(ProfileController.getImage());
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

    public void changePassword(MouseEvent mouseEvent) {
        ProfileMenuMessage message = ProfileController.changePassword(oldPassword.getText(), newPassword.getText());
        switch (message) {
            case WRONG_PASSWORD :
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("you enter wrong password");
                alert.showAndWait();
                break;
            case PASSWORD_IS_SAME:
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setContentText("your old password and new password are same");
                alert2.showAndWait();
                break;
            default:
                break;
        }
    }

    public void changeUsername(MouseEvent mouseEvent) {
        ProfileMenuMessage message = ProfileController.changeUsername(oldUsername.getText(), newUsername.getText());
        switch (message) {
            case WRONG_USERNAME:
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("you enter wrong username");
                alert.showAndWait();
                break;
            case USERNAME_IS_SAME :
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setContentText("your old username and new username are same");
                alert2.showAndWait();
                break;
            default:
                break;
        }
    }

    public void logout(MouseEvent mouseEvent) throws Exception{
        ProfileController.logout();
        Menu.getHelloApplication().start(HelloApplication.getStageOfRoot());
    }

    public void removeAccount(MouseEvent mouseEvent) throws Exception{
        ProfileController.removeAccount();
        Menu.getHelloApplication().start(HelloApplication.getStageOfRoot());
    }

    public void avatarMenu(MouseEvent mouseEvent) throws Exception{
        Menu.getAvatarMenu().start(HelloApplication.getStageOfRoot());
    }

    public void back(MouseEvent mouseEvent) throws Exception{
        Menu.getMainMenu().start(HelloApplication.getStageOfRoot());
    }
}
