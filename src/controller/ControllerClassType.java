package controller;

import animatefx.animation.SlideInUp;
import api.ApiObject;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import core.UserLogin;
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
import java.util.ArrayList;
import java.util.Stack;

public class ControllerClassType {

    private Stage stage;
    private Scene scene;
    private static UserLogin userInfo;
    private ControllerClassType parentController;
    private ArrayList<StackPane> parentStackPaneList;
    private ApiObject apiResponseData;

    public ControllerClassType(){ }

    public void setParentController(ControllerClassType parentController) { this.parentController = parentController; }
    public ControllerClassType getParentController() { return parentController; }

    public StackPane myStackPane;
    public void setMyStackPane(StackPane e){ this.myStackPane=e; }
    public StackPane getMyStackPane() { return myStackPane; }

    public void setApiData(ApiObject a){ apiResponseData = a;}
    public ApiObject getApiData() { return apiResponseData; }

    public void setUserData(UserLogin info){
        this.userInfo = info;
    }
    public UserLogin getUserData() { return userInfo; }

    public void setScene(){
        try{
            scene = stage.getScene();
        }catch (Exception e){
            System.err.println("Stage not set!");
        }
    }
    public Scene getScene() { return scene; }

    public void setStage(Stage stage){ this.stage =stage;setScene(); }
    public Stage getStage() {
        return stage;
    }

    public void setParentStackPane(StackPane parentStackPane) { parentStackPaneList=new ArrayList<StackPane>();this.parentStackPaneList.add(parentStackPane); }
    public StackPane getParentStackPane() { return parentStackPaneList.get(0); }

    //===========================

    public void alignUserPage(UserLogin i){
        setUserData(i);
        changeCSS("../src/css/"+userInfo.getUserInfo().getTheme()+".css");
    }


    public void changeCSS(String filename,String removal){
        if(removal.length() > 0) scene.getStylesheets().remove(ControllerClassType.class.getResource(removal).toExternalForm());
        if(!scene.getStylesheets().contains(ControllerClassType.class.getResource(filename).toExternalForm()))
            scene.getStylesheets().add(ControllerClassType.class.getResource(filename).toExternalForm());
    }
    public void changeCSS(String filename){
        if(!scene.getStylesheets().contains(ControllerClassType.class.getResource(filename).toExternalForm()))
            scene.getStylesheets().add(ControllerClassType.class.getResource(filename).toExternalForm());
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
        ControllerClassType controller;

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

    ControllerClassType switch_cad(ActionEvent event,StackPane stackPane, String filename,boolean fullscreen){
        try{
            Stage stage = (Stage) stackPane.getScene().getWindow();
            SwitchScene switchScene = new SwitchScene(stage);
            ControllerClassType c = switchScene.switch_(filename);
            c.setStage(stage);
            stage.setFullScreen(fullscreen);
            return c;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        //stage.close();
    }

    public void messageDialog(StackPane stackPane, String info, String name){
        System.out.println("Message("+name+"): "+info);
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