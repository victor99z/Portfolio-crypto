package controller;

import com.jfoenix.controls.*;
import core.UserLogin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

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
            ControllerClassType rp_controller = super.switch_cad(event,stackPane,"RootPane",false);
            rp_controller.alignUserPage(login);
        }else{
            messageDialog(stackPane,"Usuário ou senha incorretos","Erro!");
        }
    }

    @FXML
    void switch_cad(ActionEvent event){
        super.switch_cad(event,stackPane,"registerScreen",false);
    }

}
