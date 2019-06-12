package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class RootPaneController  extends ControllerClassType{

    @FXML
    private AnchorPane containerTabela;
    @FXML
    private StackPane stackPane;

    @FXML
    private JFXTabPane tabPane;

    @FXML
    private JFXToggleButton dtSwitch;

    @FXML
    private JFXToggleButton fsSwitch;

    @FXML
    private JFXTextField newUsername;

    @FXML
    private JFXTextField newPassword;

    @FXML
    private JFXButton updateUserInfo;

    private ControllerClassType tabelaController;

    public void initialize(){
        tabelaController = super.loadFxml(containerTabela,"TableView");
        /*super.getStage().setOnHidden(event -> {
            System.exit(0);
        });*/
    }


    void switch_cad(ActionEvent event){
        super.switch_cad(event,stackPane,"registerScreen",false);
    }

    public JFXToggleButton getDtSwitch() {
        return dtSwitch;
    }

    @FXML
    void dtSwitchChange(ActionEvent event) {

    }

    @FXML
    void fsSwitchChange(ActionEvent event) {
        if(fsSwitch.isSelected()){
            super.setSceneFullscreen(event,stackPane,true);
        }else{
            super.setSceneFullscreen(event,stackPane,false);
        }
    }

    @FXML
    void updateUserLogin(ActionEvent event) {
        String txt = newUsername.getText();
        String pw = newPassword.getText();

    }

}
