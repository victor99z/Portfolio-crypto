package controller;

import animatefx.animation.SlideInUp;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerClassType {

    private Stage stage;
    public ControllerClassType(){}
    @FXML
    void switch_cad(ActionEvent event,StackPane stackPane, String filename,boolean fullscreen){
        try{
            Stage stage = (Stage) stackPane.getScene().getWindow();
            SwitchScene switchScene = new SwitchScene(stage);
            ControllerClassType c = switchScene.switch_(filename);
            c.setStage(stage);
            stage.setFullScreen(fullscreen);
        }catch (Exception e){
            e.printStackTrace();
        }
        //stage.close();
    }

    public void setStage(Stage stage){
        this.stage =stage;
    }

    public Stage getStage() {
        return stage;
    }

    void setSceneFullscreen(ActionEvent event, StackPane stackPane, boolean fullscreen){
        try{
            Stage stage = (Stage) stackPane.getScene().getWindow();
            stage.setFullScreen(fullscreen);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ControllerClassType loadFxml (AnchorPane obj, String file){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/"+file+".fxml"));
        StackPane newLoadedPane;
        ControllerClassType controller = new ControllerClassType();

        try {
            newLoadedPane = (StackPane) loader.load();
            controller = (ControllerClassType) loader.getController();

            AnchorPane.setTopAnchor(newLoadedPane, 0.0);
            AnchorPane.setLeftAnchor(newLoadedPane, 0.0);
            AnchorPane.setRightAnchor(newLoadedPane, 0.0);
            AnchorPane.setBottomAnchor(newLoadedPane,0.0);
            obj.getChildren().add(newLoadedPane);
            new SlideInUp(newLoadedPane).play();
            return controller;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


    }

    void MessageDialog(StackPane stackPane,String info,String name){
        //titleLabel.setText(name);
        JFXDialogLayout dl = new JFXDialogLayout();
        dl.setHeading(new Text(name));
        dl.setBody(new Text(info));
        JFXButton bt_dis = new JFXButton("BLZ");

        JFXDialog dialog = new JFXDialog(stackPane,dl,JFXDialog.DialogTransition.CENTER);

        bt_dis.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.close();
            }
        });
        dl.setActions(bt_dis);
        dialog.show(stackPane);
    }

}