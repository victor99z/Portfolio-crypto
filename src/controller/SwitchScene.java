package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SwitchScene {

    private final Stage stage;

    public SwitchScene(Stage targ){
        this.stage = targ;
    }
    public void switch_(String fxmlFile) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/"+fxmlFile+".fxml"));
        Parent root;
        try {
            root = (Parent) loader.load();
            this.stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
