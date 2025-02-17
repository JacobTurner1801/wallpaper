module com.wallpaper {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens com.wallpaper to javafx.fxml;
    exports com.wallpaper;
}
