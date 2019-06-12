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
        /*Parent root = FXMLLoader.load(getClass().getResource("loginScreen.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();*/
        this.stage = primaryStage ;
        //switchScene = new switchScene(this.stage);
        primaryStage.setTitle("Scenebuilder mito");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/loginScreen.fxml"));
        System.out.println("AEAEAE");
        Parent root = (Parent) loader.load();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        //primaryStage.setFullScreen(true);
        //switchScene("register");

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
