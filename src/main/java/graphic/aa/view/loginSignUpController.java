package graphic.aa.view;

import graphic.aa.controller.LoginController;
import graphic.aa.view.message.LoginMessage;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.concurrent.TimeUnit;

public class loginSignUpController {
    public Label loginLabel;
    public TextField username;
    public PasswordField password;

    public void login(MouseEvent mouseEvent) throws Exception{
        LoginMessage message = LoginController.login(username.getText(), password.getText());
        if (message.equals(LoginMessage.NOT_EXIST)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("can't find user with this username and password");
            alert.showAndWait();
        }
        else if (message.equals(LoginMessage.USERNAME_IS_NULL)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("username is empty");
            alert.show();
        }
        else if (message.equals(LoginMessage.PASSWORD_IS_NULL)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("password is empty");
            alert.showAndWait();
        }
        else {
            Menu.getMainMenu().start(HelloApplication.getStageOfRoot());
        }

    }

    public void back(MouseEvent mouseEvent) throws Exception{
        Menu.getHelloApplication().start(HelloApplication.getStageOfRoot());
    }
}
