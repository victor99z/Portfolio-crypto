package MainPackage;

import animatefx.animation.FadeIn;
import animatefx.animation.SlideInDown;
import animatefx.animation.SlideInUp;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import com.sun.javafx.scene.control.behavior.*;
public class Main extends Application {

    private Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{

        this.stage = primaryStage ;//pegar o stage
        primaryStage.setTitle("Scenebuilder mito");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/loginScreen.fxml"));//carregar a primeira tela
        System.out.println("AEAEAE");
        Parent root = (Parent) loader.load();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        new SlideInDown(root).play();



        stage.setOnHidden(event -> {
            System.out.println("Sai");
        });
        stage.setOnCloseRequest(event -> {
            System.exit(0);
        });
        stage.setOnShown(event -> {
            System.out.println("Entra");
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}


/*
Rascunho (não eh relevante)

/*Parent root = FXMLLoader.load(getClass().getResource("loginScreen.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

switchScene = new switchScene(this.stage);

primaryStage.setFullScreen(true);
switchScene("register");
*/