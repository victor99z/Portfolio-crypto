package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private Stage stage;
    //private switchScene switchScene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        /*Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("Scenebuilder mito");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
        primaryStage.setResizable(true);
        */

        this.stage = primaryStage;
        //switchScene = new switchScene(this.stage);
        primaryStage.setTitle("Scenebuilder mito");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = (Parent) loader.load();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        //switchScene("register");

        stage.setOnHidden(event -> {
            System.out.println("Sai");
        });
        stage.setOnShown(event -> {
            System.out.println("Entra");
        });
    }
}