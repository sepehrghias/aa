package graphic.aa.view;

import graphic.aa.controller.GameController;
import graphic.aa.controller.LoginController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class HelloController {

    public Label label;

    public void login(MouseEvent mouseEvent) throws Exception{
        Menu.getLoginAndSignUpMenu().start(HelloApplication.getStageOfRoot());
    }

    public void signUp(MouseEvent mouseEvent) throws Exception{
        Menu.getSignUpMenu().start(HelloApplication.getStageOfRoot());
    }

    public void skip(MouseEvent mouseEvent) throws Exception{
        GameMenu gameMenu = new GameMenu();
        GameController.setGame(gameMenu);
        GameMenu.setFinish(false);
        gameMenu.start(HelloApplication.getStageOfRoot());
    }
}