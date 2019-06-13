package controller;

import api.DataArray;
import com.google.gson.Gson;
import com.jfoenix.controls.*;
import core.UserLogin;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginController extends ControllerClassType{

    @FXML
    private StackPane stackPane;

    @FXML
    private Label titleLabel;

    @FXML
    private JFXTextField InputUsername;

    @FXML
    private JFXPasswordField InputPassword;

    @FXML
    private JFXButton cadBtn;

    @FXML
    private JFXButton loginBtn;

    @FXML
    private SwitchScene switchScene;

    @FXML
    private Stage stage;

    private UserLogin login;

    @FXML
    public void initialize(){
        login=new UserLogin();
    }

    @FXML
    void display(ActionEvent event) {
        if(login.checkLoginMatches(InputUsername.getText(), InputPassword.getText())){
            super.switch_cad(event,stackPane,"RootPane",false);
        }else{
            MessageDialog(stackPane,"Usuário ou senha incorretos","Erro!");
        }
    }

    @FXML
    void switch_cad(ActionEvent event){
        super.switch_cad(event,stackPane,"registerScreen",false);
    }

}
