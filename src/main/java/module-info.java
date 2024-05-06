module graphic.aa {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires json.simple;


    opens graphic.aa.view to javafx.fxml;
    exports graphic.aa.view;
}