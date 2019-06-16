package controller;

import animatefx.animation.SlideInDown;
import animatefx.animation.SlideInUp;
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
    public ControllerClassType switch_(String fxmlFile) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/"+fxmlFile+".fxml"));
        Parent root;
        ControllerClassType controller = new ControllerClassType();
        try {
            root = (Parent) loader.load();
            controller = (ControllerClassType) loader.getController();
            this.stage.setScene(new Scene(root));
            new SlideInDown(root).play();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return controller;
    }

}
