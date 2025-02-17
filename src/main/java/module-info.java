module com.wallpaper {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.wallpaper to javafx.fxml;
    exports com.wallpaper;
}
