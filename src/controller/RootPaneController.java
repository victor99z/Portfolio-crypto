package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import core.UserLogin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class RootPaneController  extends ControllerClassType{

    @FXML
    private AnchorPane containerTabela;
    @FXML
    private AnchorPane conteinerHome;
    @FXML
    private StackPane stackPane;

    @FXML
    private JFXTabPane tabPane;

    @FXML
    private FlowPane borderTop;
    @FXML
    private Text userNameID;

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

    @FXML
    private FlowPane settingsPane;

    private ControllerClassType tabelaController;
    private ControllerClassType homeController;

    public void initialize(){
        tabelaController = super.loadFxml(containerTabela,"TableView");
        tabelaController.setParentStackPane(stackPane);
        tabelaController.setParentController(this);

        homeController = super.loadFxml(conteinerHome,"home");
        homeController.setParentStackPane(stackPane);
        homeController.setParentController(this);
        /*super.getStage().setOnHidden(event -> {
            System.exit(0);
        });*/
    }

    public void alignUserPage(UserLogin i){
        super.alignUserPage(i);
        userNameID.setText(super.getUserData().getUserInfo().getUsername());
        if(super.getUserData().getUserInfo().getTheme().equals("darkMode")){
            dtSwitch.setSelected(true);
        }
    }


    void switch_cad(ActionEvent event){
        super.switch_cad(event,stackPane,"registerScreen",false);
    }

    public JFXToggleButton getDtSwitch() {
        return dtSwitch;
    }

    @FXML
    void dtSwitchChange(ActionEvent event) {
        if(dtSwitch.isSelected()){
            super.changeCSS("../src/css/darkMode.css","../src/css/lightMode.css");
            super.getUserData().updateJSON("theme","darkMode");
        }else{
            super.changeCSS("../src/css/lightMode.css","../src/css/darkMode.css");
            super.getUserData().updateJSON("theme","lightMode");
        }
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
        String changes="no data ";
        if(txt.length() > 0)
            if(super.getUserData().updateJSON("username",txt)){
                changes="Username ";
            }else{
                changes="";
            }
        if(pw.length() >0)
            if(super.getUserData().updateJSON("password",pw)){
                if(changes.equals("Username ")) {
                    changes+="and Password ";
                }else{
                    changes="Password ";
                }
            }
        MessageDialog(stackPane,changes+"updated","Update");
    }

}
