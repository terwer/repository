package com.terwergreen.repository.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class AppMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = getClass().getResource("layout.fxml");
        Parent root = FXMLLoader.load(url);
        primaryStage.setTitle("自动安装maven到仓库");
        primaryStage.setScene(new Scene(root, 600, 530));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
