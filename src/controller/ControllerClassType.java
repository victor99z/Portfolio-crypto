package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ControllerClassType {

    @FXML
    void switch_cad(ActionEvent event,StackPane stackPane, String filename,boolean fullscreen){
        try{
            Stage stage = (Stage) stackPane.getScene().getWindow();
            SwitchScene switchScene = new SwitchScene(stage);
            switchScene.switch_(filename);
            stage.setFullScreen(fullscreen);
        }catch (Exception e){
            e.printStackTrace();
        }
        //stage.close();
    }
}
