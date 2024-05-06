package graphic.aa.view;

import graphic.aa.controller.FirstController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private static Stage stageOfRoot;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/graphic/aa/fxml/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setScene(scene);
        stageOfRoot = stage;
        stage.show();
    }

    public static void main(String[] args)
    {
        FirstController.initializeUser();
        launch();
    }

    public static Stage getStageOfRoot() {
        return stageOfRoot;
    }
}