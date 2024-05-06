package graphic.aa.view;

import graphic.aa.controller.SettingController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Setting extends Application {

    public BorderPane pane;
    public ChoiceBox map;
    private Scene scene;
    public ChoiceBox language;
    public ChoiceBox shootKey;
    public Spinner ball;
    @FXML
    private ChoiceBox hardDegree;

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane borderPane = FXMLLoader.load(Setting.class.getResource("/graphic/aa/fxml/setting.fxml"));
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void initialize() {
        String[] languages = new String[] {"English" , "Persian"};
        String[] numberOfDegree = new String[] {"1","2","3"};
        String[] shoot = new String[]{"Space", "Up", "Enter"};
        hardDegree.setItems(FXCollections.observableArrayList(numberOfDegree));
        hardDegree.setValue(SettingController.getHardDegree());
        language.setItems(FXCollections.observableArrayList(languages));
        language.setValue("English");
        shootKey.setItems(FXCollections.observableArrayList(shoot));
        shootKey.setValue(SettingController.getShootKey());
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,20);
        ball.setValueFactory(valueFactory);
        setModeOfPage();
        map.setItems(FXCollections.observableArrayList(numberOfDegree));
        map.setValue(SettingController.getMap());
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
        SettingController.setBalls(ball.getValue().toString());
        SettingController.setShootKey(shootKey.getValue().toString());
        SettingController.setHardDegree(hardDegree.getValue().toString());
        SettingController.setMap(Integer.parseInt(map.getValue().toString()));
        Menu.getMainMenu().start(HelloApplication.getStageOfRoot());
    }

    public void changeMode(MouseEvent mouseEvent) {
        if (SettingController.isLight()) {
            BackgroundFill backgroundFill = new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY);
            Background background = new Background(backgroundFill);
            pane.setBackground(background);
            pane.getStylesheets().add(getClass().getResource("/graphic/aa/css/dark-mode.css").toExternalForm());
            SettingController.setLight(false);
        }
        else {
            pane.getStylesheets().remove(getClass().getResource("/graphic/aa/css/dark-mode.css").toExternalForm());
            BackgroundFill backgroundFill = new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY );
            Background background = new Background(backgroundFill);
            pane.setBackground(background);
            SettingController.setLight(true);
        }
    }

}
