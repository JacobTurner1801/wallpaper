package com.wallpaper;

import java.io.File;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
// import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public class WallpaperController {
    // @FXML private BorderPane layout;
    // Top bar
    @FXML private MenuBar menuBar;
    @FXML private Menu fileMenu;
    @FXML private MenuItem chooseDirectoryMenuItem;
    @FXML private Menu aboutMenu;
    @FXML private MenuItem aboutMenuItem;
    // Center
    @FXML private TilePane imageTilePane; // Images
    @FXML private ImageView previewImages; // Previews
    // Right
    @FXML private VBox previewPane;
    @FXML private ImageView previewImageView;
    @FXML private Label imageNameLabel;
    @FXML private Label imageDirectoryLabel;
    @FXML private Label imageSizeLabel;
    @FXML private Button setWallpaperButton;

    private String defaultPath = "C:\\wallpaper_chooser\\wallpapers";

    public void initialize() {

        loadWallpapers(new File(defaultPath)); // initialise with default path
        setWallpaperButton.setVisible(false);

        chooseDirectoryMenuItem.setOnAction(event -> {
            // FIXME: add in dialog for the user to pick the folders?
            loadWallpapers(new File("C:\\wallpaper_chooser\\wallpapers"));
        });

        setWallpaperButton.setOnAction(event -> {
            // TODO: Set Backgroubnd method for the image currently selected
            System.out.println("Set Wallpaper Button Clicked");
        });
    }

    private void loadWallpapers(File directory) {
        imageTilePane.getChildren().clear(); // reset
        // Get all jpg
        File[] images = directory.listFiles((dir, name) -> {
            return name.toLowerCase().endsWith(".jpg") || name.toLowerCase().endsWith(".jpeg");
        });
        if (images != null) {
            for (File img : images) {
                // create thumbnail
                createThumbnail(img);
            }
        }
    }

    private void createThumbnail(File img) {
        // url, width, height, preserveRatio, smooth
        Image image = new Image(img.toURI().toString(), 150, 150, true, true);
        ImageView thumbnail = new ImageView(image);

        thumbnail.setOnMouseClicked(event -> {
            displayPreview(img);
        });

        imageTilePane.getChildren().add(thumbnail);
    }

    private void displayPreview(File img) {
        Image previewImage = new Image(img.toURI().toString());
        previewImageView.setImage(previewImage);
        imageNameLabel.setText("Name: " + img.getName());
        imageDirectoryLabel.setText("Directory: " + img.getParent());
        imageSizeLabel.setText("Size: " + formatSizeInMB(img.length()));
        setWallpaperButton.setVisible(true);
    }

    private String formatSizeInMB(long bytes) {
        return String.format("%.2f MB", bytes / (1024.0 * 1024.0));
    }
}
