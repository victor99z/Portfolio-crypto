package MainPackage;

import animatefx.animation.SlideInDown;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{

        this.stage = primaryStage ;//pegar o stage
        primaryStage.setTitle("Portfolio");
        stage.getIcons().add(new Image("/src/img/deltaa.png"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/loginScreen.fxml")); //carregar a primeira tela
        System.out.println("Iniciando programa ...");
        Parent root = (Parent) loader.load();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> {
            System.exit(0);
        });

        new SlideInDown(root).play();

    }


    public static void main(String[] args) {
        launch(args);
    }
}

