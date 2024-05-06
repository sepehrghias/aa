package graphic.aa.view;

import graphic.aa.controller.SongController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class SongMenu extends Application {
    public Circle firstCircle;
    public Circle mainCircle;
    public Circle secondCircle;
    public Circle thirdCircle;

    private AudioClip song;

    Image image1 = new Image(SongMenu.class.getResource("/graphic/aa/image/song1.jpeg").toExternalForm());
    Image image2 = new Image(SongMenu.class.getResource("/graphic/aa/image/song2.jpeg").toExternalForm());
    Image image3 = new Image(SongMenu.class.getResource("/graphic/aa/image/song3.jpeg").toExternalForm());
    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = FXMLLoader.load(SongMenu.class.getResource("/graphic/aa/fxml/song.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void initialize() {
        int song = SongController.getSong();
        Image mainImage = new Image(SongMenu.class.getResource("/graphic/aa/image/song" + song +".jpeg").toExternalForm());
        mainCircle.setFill(new ImagePattern(mainImage));
        firstCircle.setFill(new ImagePattern(image1));
        secondCircle.setFill(new ImagePattern(image2));
        thirdCircle.setFill(new ImagePattern(image3));
    }
    public void back(MouseEvent mouseEvent) throws Exception{
        if (song != null)
            song.stop();
        SongController.getGame().start(HelloApplication.getStageOfRoot());
    }

    public void firstClicked(MouseEvent mouseEvent) {
        mainCircle.setFill(new ImagePattern(image1));
        SongController.setSong(1);
        if (song != null)
            song.stop();
        song = new AudioClip(getClass().getResource("/graphic/aa/media/song1.mp3").toString());
        song.play();
    }

    public void secondClicked(MouseEvent mouseEvent) {
        mainCircle.setFill(new ImagePattern(image2));
        SongController.setSong(2);
        if (song != null)
            song.stop();
        song = new AudioClip(getClass().getResource("/graphic/aa/media/song2.mp3").toString());
        song.play();
    }

    public void thirdClicked(MouseEvent mouseEvent) {
        mainCircle.setFill(new ImagePattern(image3));
        SongController.setSong(3);
        if (song != null)
            song.stop();
        song = new AudioClip(getClass().getResource("/graphic/aa/media/song3.mp3").toString());
        song.play();
    }
}
